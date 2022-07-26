package com.devpsein.shoppingapp.product.service;

import com.devpsein.shoppingapp.product.domain.MoneyTypes;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ProductDeliveryService {
    public String getDeliveryInfo(String productId){
        //TODO
        return "Tomorrow";

    }
    public boolean freeDeliveryCheck(String productId, BigDecimal price , MoneyTypes moneyTypes){
        //TODO

        return true;
    }
}
