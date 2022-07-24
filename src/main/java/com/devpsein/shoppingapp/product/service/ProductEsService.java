package com.devpsein.shoppingapp.product.service;

import com.devpsein.shoppingapp.product.domain.Product;
import com.devpsein.shoppingapp.product.domain.es.CategoryEs;
import com.devpsein.shoppingapp.product.domain.es.CompanyEs;
import com.devpsein.shoppingapp.product.domain.es.ProductEs;
import com.devpsein.shoppingapp.product.repository.es.ProductEsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.stream.DoubleStream;

@Service
@RequiredArgsConstructor
public class ProductEsService {

    private final ProductEsRepository productEsRepository;

    public Mono<ProductEs> saveNewProduct(Product product){
       return productEsRepository.save(
        ProductEs.builder()
                .active(product.getActive())
                .code(product.getCode())
                .descrption(product.getDescrption())
                .features(product.getFeatures())
                .id(product.getId())
                .name(product.getName())
                // TODO get company name and code
                .seller(CompanyEs.builder()
                        .id(product.getCompany_Id())
                        .name("Test")
                        .build())
                .category(CategoryEs.builder()
                        .id(product.getCategoryId())
                        .name("Test")
                        .build())
                .build());

    }

    public Flux<ProductEs> findAll() {
        return productEsRepository.findAll();
    }
}
