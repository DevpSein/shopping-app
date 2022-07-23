package com.devpsein.shoppingapp.product.domain;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import java.math.BigDecimal;
import java.util.List;

@Document(collation = "product")
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Product {

    private String id ;
    private String name;
    private Boolean active;
    private String descrption;
    private String company_Id;
    private String features;
    private String categoryId;
    private List<ProductImage> productImage;
    private String code;
}
