package com.devpsein.shoppingapp.product.repository.es;

import com.devpsein.shoppingapp.product.domain.es.ProductEs;
import org.springframework.data.elasticsearch.repository.ReactiveElasticsearchRepository;

public interface ProductEsRepository  extends ReactiveElasticsearchRepository<ProductEs,String> {
}
