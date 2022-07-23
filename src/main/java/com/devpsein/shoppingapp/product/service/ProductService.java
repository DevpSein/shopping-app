package com.devpsein.shoppingapp.product.service;

import com.devpsein.shoppingapp.product.domain.MoneyTypes;
import com.devpsein.shoppingapp.product.domain.es.ProductEs;
import com.devpsein.shoppingapp.product.model.ProductResponse;
import com.devpsein.shoppingapp.product.model.ProductSaveRequest;
import com.devpsein.shoppingapp.product.model.ProductSellerResponse;
import com.devpsein.shoppingapp.product.repository.mongo.ProductRepository;
import com.devpsein.shoppingapp.product.repository.es.ProductEsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor

public class ProductService {
    private final ProductEsRepository productEsRepository;
    private final ProductRepository productRepository;
    private final ProductPriceService productPriceService;
    private final ProductDeliveryService productDeliveryService;
    private final ProductAmountService productAmountService;
    private final ProductImageService productImageService;

    public Flux<ProductResponse> getAll() {
        return productEsRepository.findAll().map(this::mapToDto);

    }

    ProductResponse save (ProductSaveRequest productSaveRequest){
        // Mongoya yaz
        // ES güncelle
        // Redis Güncelle
        // ES den cevap dön
        // responese nesnesine dönüştür
        return null;
    }

        // 2-Calc fieldları işle
        // redisten ihtiyaç alanlarını getir
        // response nesnesine donuştür
    private ProductResponse mapToDto(ProductEs item) {
        BigDecimal productPrice = productPriceService.getByMoneyType(item.getId(), MoneyTypes.USD);

        return ProductResponse.builder()
                .price(productPrice)
                .name(item.getName())
                .features(item.getFeatures())
                .id(item.getId())
                .description(item.getDescrption())
                .deliveryIn(productDeliveryService.getDeliveryInfo(item.getId()))
                .categoryId(item.getCategory().getId())
                .available(productAmountService.getByProductId(item.getId()))
                .freeDelivery(productDeliveryService.freeDeliveryCheck(item.getId(),productPrice))
                .moneyType(MoneyTypes.USD)
                .image(productImageService.getProductMainImage(item.getId()))
                .seller(ProductSellerResponse.builder().id(item.getSeller().getId()).name(item.getSeller().getName()).build())
                .build();
    }

}
