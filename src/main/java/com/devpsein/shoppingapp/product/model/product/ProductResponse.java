package com.devpsein.shoppingapp.product.model.product;

import com.devpsein.shoppingapp.product.domain.MoneyTypes;
import com.devpsein.shoppingapp.product.model.ProductSellerResponse;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class ProductResponse {
    private String id ;
    private String image;
    private String name;
    private String description;
    private ProductSellerResponse seller ; // Producy seller response daki fieldlari seller altında çağırıyorum.
    private String features;
    private int available;
    private boolean freeDelivery;
    private String deliveryIn;
    private BigDecimal price;
    private String categoryId;
    private MoneyTypes moneyType;


}
