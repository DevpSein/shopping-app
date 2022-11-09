package com.devpsein.shoppingapp.product.domain.es.category;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collation = "category")
@Getter
@Setter
@Builder
@EqualsAndHashCode(of = "id")

public class Category {
    @Id
    private String id;
    private String name;
    private String code;
    private String deneme;
}
