package com.fsse2401.project_backend.service;

import com.fsse2401.project_backend.data.cartItem.entity.CartItemEntity;
import com.fsse2401.project_backend.data.transaction.entity.TransactionEntity;
import com.fsse2401.project_backend.data.transactionProduct.entity.TransactionProductEntity;
import java.util.List;

public interface TransactionProductService {
    List<TransactionProductEntity> putTransactionProduct (
                                                           TransactionEntity transactionEntity,
                                                           List<CartItemEntity> cartItemEntityList);


    List<TransactionProductEntity> getTransactionProductEntityLsitByTransaction(Integer tid);
}
