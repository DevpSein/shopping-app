package com.devpsein.shoppingapp.product.repository.mongo;

import com.devpsein.shoppingapp.product.domain.ProductPrice;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ProductPriceRepository extends ReactiveMongoRepository<ProductPrice,String> {
}
