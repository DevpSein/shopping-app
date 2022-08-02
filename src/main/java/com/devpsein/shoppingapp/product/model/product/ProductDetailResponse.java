package com.devpsein.shoppingapp.product.model.product;

import com.devpsein.shoppingapp.product.domain.MoneyTypes;
import com.devpsein.shoppingapp.product.model.ProductSellerResponse;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
public class ProductDetailResponse {
    private String id ;
    private List<String> images;
    private String name;
    private String description;
    private ProductSellerResponse seller ; // Producy seller response daki fieldlari seller altında çağırıyorum.
    private String features;
    private int available;
    private boolean freeDelivery;
    private String deliveryIn;
    private BigDecimal price;
    private String categoryId;
    private String moneySymbol;
    private MoneyTypes moneyType;
}
