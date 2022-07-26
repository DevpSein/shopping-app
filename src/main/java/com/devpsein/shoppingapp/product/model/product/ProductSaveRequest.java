package com.devpsein.shoppingapp.product.model.product;

import com.devpsein.shoppingapp.product.domain.MoneyTypes;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
@Builder
@Data
public class ProductSaveRequest {
    private String id ;
    private String name;
    private String description;
    // Sellerdan bir linke tıklayıp gitmek isteyebiliriz bu sebeple bir nesne olmalı
    private String features;
    private BigDecimal available;
    private HashMap<MoneyTypes,BigDecimal> price ;
    private List<String> images;
    private String sellerId;
    private String categoryId;
}
