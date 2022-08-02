package com.devpsein.shoppingapp.product.api;

import com.devpsein.shoppingapp.product.model.product.ProductDetailResponse;
import com.devpsein.shoppingapp.product.model.product.ProductResponse;

import com.devpsein.shoppingapp.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@CrossOrigin("*")
@RestController
@RequestMapping("/products")
public class ProductApi {
    private final ProductService productService;

    @GetMapping
    public Flux<ProductResponse> getAllProducts() {
        return productService.getAll();
    }

    @GetMapping("/{id}")
    public Mono<ProductDetailResponse> getProductDetail(@PathVariable("id") String id) throws ResponseStatusException {
            return productService.getProductDetail(id);
    }

}
