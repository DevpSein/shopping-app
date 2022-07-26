package com.devpsein.shoppingapp.product.api;

import com.devpsein.shoppingapp.product.domain.es.CategoryEs;
import com.devpsein.shoppingapp.product.model.category.CategoryResponse;
import com.devpsein.shoppingapp.product.service.category.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
@CrossOrigin("*")
@RestController
@RequestMapping("/category")
public class CategoryApi {

    private CategoryService categoryService;

    @GetMapping
    public Flux<CategoryResponse> getAll(){
        return categoryService.getAll();

    }
}
