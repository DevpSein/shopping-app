package com.devpsein.shoppingapp.product.service;

import com.devpsein.shoppingapp.product.domain.Product;
import com.devpsein.shoppingapp.product.domain.ProductImage;
import com.devpsein.shoppingapp.product.domain.es.category.Category;
import com.devpsein.shoppingapp.product.domain.es.CategoryEs;
import com.devpsein.shoppingapp.product.domain.es.CompanyEs;
import com.devpsein.shoppingapp.product.domain.es.ProductEs;
import com.devpsein.shoppingapp.product.repository.es.ProductEsRepository;
import com.devpsein.shoppingapp.product.service.category.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductEsService {

    private final ProductEsRepository productEsRepository;
    private final CategoryService categoryService;
    public Mono<ProductEs> saveNewProduct(Product product){
       return productEsRepository.save(
        ProductEs.builder()
                .active(product.getActive())
                .code(product.getCode())
                .descrption(product.getDescrption())
                .features(product.getFeatures())
                .id(product.getId())
                .price(product.getPrice())
                .name(product.getName())
                // TODO get company name and code (şirket isimleri ve şirket numarasını al)
                .seller(CompanyEs.builder()
                        .id(product.getCompany_Id())
                        .name("Test")
                        .build())
                .category(getProductCategory(product.getCategoryId()))
                .images(product.getProductImage().stream()
                .map(ProductImage::getUrl)
                .collect(Collectors.toList()))
                .build());

    }

    private CategoryEs getProductCategory(String categoryId) {
        Category category = categoryService.getById(categoryId);
        return CategoryEs.builder()
                .name(category.getName())
                .id(category.getId())
                .code(category.getCode())
                .build();
    }

    public Flux<ProductEs> findAll() {
        return productEsRepository.findAll();
    }

    public Mono<ProductEs> findById(String id) {
        return productEsRepository.findById(id);
    }
}
