package com.devpsein.shoppingapp.product.domain.es;


import com.devpsein.shoppingapp.product.domain.MoneyTypes;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.Document;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

@Document(indexName = "product")
@Getter
@Setter
@Builder
@EqualsAndHashCode(of = "id")
public class ProductEs {
    private String id ;
    private String name;
    private Boolean active;
    private String descrption;
    private CompanyEs seller;
    private String features;
    private HashMap<MoneyTypes, BigDecimal> price;
    private CategoryEs category;
    private String code;
    private List<String> images;
}
