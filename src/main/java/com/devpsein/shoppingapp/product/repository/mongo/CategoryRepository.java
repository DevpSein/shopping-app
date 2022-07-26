package com.devpsein.shoppingapp.product.repository.mongo;

import com.devpsein.shoppingapp.product.domain.es.category.Category;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends ReactiveMongoRepository<Category, String> {


}

