package com.devpsein.shoppingapp.product.service;

import com.devpsein.shoppingapp.product.domain.MoneyTypes;
import com.devpsein.shoppingapp.product.domain.Product;
import com.devpsein.shoppingapp.product.domain.ProductImage;
import com.devpsein.shoppingapp.product.domain.es.ProductEs;
import com.devpsein.shoppingapp.product.model.product.ProductDetailResponse;
import com.devpsein.shoppingapp.product.model.product.ProductResponse;
import com.devpsein.shoppingapp.product.model.product.ProductSaveRequest;
import com.devpsein.shoppingapp.product.model.ProductSellerResponse;
import com.devpsein.shoppingapp.product.repository.mongo.ProductRepository;
import com.devpsein.shoppingapp.product.repository.es.ProductEsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class ProductService {

    //Kullanacağım
    private final ProductRepository productRepository;
    private final ProductDeliveryService productDeliveryService;
    private final ProductAmountService productAmountService;
    private final ProductEsService productEsService;

    public Flux<ProductResponse> getAll() {
        return productEsService.findAll().map(this::mapToDto);

    }
    public ProductResponse save (ProductSaveRequest request){
        Product product = Product.builder()
                .active(Boolean.TRUE)
                .code("PR0001")
                .categoryId(request.getCategoryId())
                .company_Id(request.getSellerId())
                .descrption(request.getDescription())
                .features(request.getFeatures())
                .name(request.getName())
                .price(request.getPrice())
                .productImage(request.getImages().stream().map(it -> new ProductImage(ProductImage.ImageType.FEATURE,it)).collect(Collectors.toList()))
                .build();
        product = productRepository.save(product).block();
        // Mongoya yaz
        // ES güncelle
        // Redis Güncelle
        // ES den cevap dön
        // responese nesnesine dönüştür
        return this.mapToDto(productEsService.saveNewProduct(product).block());

    }

        // 2-Calc fieldları işle
        // redisten ihtiyaç alanlarını getir
        // response nesnesine donuştür

        /*
        Builder design pattern kullanımı
        Response sınıfından almamız gereken değerlerin constructora ihtiyaç olmadan tanımlanmasını sağladık.
         */
    private ProductResponse mapToDto(ProductEs item) {
        if (item == null){
            return null;
        }
       //BigDecimal productPrice = productPriceService.getByMoneyType(item.getId(), MoneyTypes.USD);


        return ProductResponse.builder()
                .price(item.getPrice().get("USD"))
                .moneySymbol(MoneyTypes.USD.getSymbol())
                .name(item.getName())
                .features(item.getFeatures())
                .id(item.getId())
                .description(item.getDescrption())
                .deliveryIn(productDeliveryService.getDeliveryInfo(item.getId()))
                .categoryId(item.getCategory().getId())
                .available(productAmountService.getByProductId(item.getId()))
                .freeDelivery(productDeliveryService.freeDeliveryCheck(item.getId(), item.getPrice().get("USD"),MoneyTypes.USD ))
                .moneyType(MoneyTypes.USD)
                .image(item.getImages().get(0))
                .seller(ProductSellerResponse.builder()
                        .id(item.getSeller().getId())
                        .name(item.getSeller().getName())
                        .build())
                .build();

    }

    public Mono<Long> count() {
        return productRepository.count();
    }

    public Mono<ProductDetailResponse> getProductDetail(String id) {
        return this.mapToDto(productEsService.findById(id));
    }
    private Mono<ProductDetailResponse> mapToDto(Mono<ProductEs> product) {
        return product.map(item ->ProductDetailResponse.builder()
                .price(item.getPrice().get("USD"))
                .moneySymbol(MoneyTypes.USD.getSymbol())
                .features(item.getFeatures())
                .id(item.getId())
                .description(item.getDescrption())
                .deliveryIn(productDeliveryService.getDeliveryInfo(item.getId()))
                .categoryId(item.getCategory().getId())
                .available(productAmountService.getByProductId(item.getId()))
                .freeDelivery(productDeliveryService.freeDeliveryCheck(item.getId(),item.getPrice().get("USD"),MoneyTypes.USD))
                .moneyType(MoneyTypes.USD)
                .images(item.getImages())
                .seller(ProductSellerResponse.builder()
                        .id(item.getSeller().getId())
                        .name(item.getSeller().getName())
                        .build())
                .build());

    }
}
