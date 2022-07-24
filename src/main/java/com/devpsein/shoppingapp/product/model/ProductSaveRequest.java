package com.devpsein.shoppingapp.product.model;

import com.devpsein.shoppingapp.product.domain.MoneyTypes;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
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
    private BigDecimal price;
    private MoneyTypes money;
    private List<String> images;
    private String sellerId;
    private String categoryId;
}
