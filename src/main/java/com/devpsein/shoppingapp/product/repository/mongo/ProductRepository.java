package com.devpsein.shoppingapp.product.repository.mongo;

import com.devpsein.shoppingapp.product.domain.Product;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ProductRepository extends ReactiveMongoRepository <Product,String> {
}
