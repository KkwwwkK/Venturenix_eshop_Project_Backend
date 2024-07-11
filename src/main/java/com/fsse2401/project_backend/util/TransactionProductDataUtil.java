package com.fsse2401.project_backend.util;

import com.fsse2401.project_backend.data.transactionProduct.entity.TransactionProductEntity;

import java.math.BigDecimal;

public class TransactionProductDataUtil {

//    public static BigDecimal getSubtotal(CartItemEntity cartItemEntity){
//        BigDecimal subtotal
//                = cartItemEntity.getProduct().getPrice().
//                multiply(BigDecimal.valueOf(cartItemEntity.getQuantity()));
//        return subtotal;
//    }

    public static BigDecimal getSubtotalByTransactionProductEntity(TransactionProductEntity transactionProductEntity){
        BigDecimal subtotal
                = transactionProductEntity.getPrice().
                multiply(BigDecimal.valueOf(transactionProductEntity.getQuantity()));
        return subtotal;
    }

}
