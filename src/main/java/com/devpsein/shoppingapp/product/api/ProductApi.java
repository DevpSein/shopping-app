package com.devpsein.shoppingapp.product.api;

import com.devpsein.shoppingapp.product.model.product.ProductResponse;

import com.devpsein.shoppingapp.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
@CrossOrigin("*")
@RestController
@RequestMapping("/products")
public class ProductApi {
    private final ProductService productService;
    @GetMapping
    public Flux<ProductResponse> getAllProducts(){
        return productService.getAll();
    }
}
