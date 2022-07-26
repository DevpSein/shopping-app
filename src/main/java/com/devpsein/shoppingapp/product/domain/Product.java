package com.devpsein.shoppingapp.product.domain;


import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

@Document(collection = "product")
@Getter
@Setter
@Builder
@EqualsAndHashCode(of = "id")
public class Product {
    @Id
    private String id ;
    private String name;
    private Boolean active;
    private String descrption;
    private String company_Id;
    private String features;
    private String categoryId;
    private List<ProductImage> productImage;
    private HashMap<MoneyTypes, BigDecimal> price;
    private String code;
}
