package com.fsse2401.project_backend.service.impl;

import com.fsse2401.project_backend.data.cartItem.entity.CartItemEntity;
import com.fsse2401.project_backend.data.transaction.entity.TransactionEntity;
import com.fsse2401.project_backend.data.transactionProduct.entity.TransactionProductEntity;
import com.fsse2401.project_backend.repository.TransactionProductRepository;
import com.fsse2401.project_backend.service.TransactionProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionProductServiceImpl implements TransactionProductService {
//    private final UserService userService;
    private final TransactionProductRepository transactionProductRepository;
    @Autowired
    public TransactionProductServiceImpl(
                                         TransactionProductRepository transactionProductRepository) {
        this.transactionProductRepository = transactionProductRepository;
    }

    @Override
    public List<TransactionProductEntity> putTransactionProduct(
                                            TransactionEntity transactionEntity,
                                            List<CartItemEntity> cartItemEntityList){

        // Create transaction product and store into database, add subtotal to total
        for(CartItemEntity cartItemEntity: cartItemEntityList){
            transactionProductRepository.save(new TransactionProductEntity(
                    transactionEntity, cartItemEntity
            ));
        }
        // return list
        List<TransactionProductEntity> transactionProductEntityList = new ArrayList<>();
        for (TransactionProductEntity transactionProductEntity: transactionProductRepository.findAllByTansactionTid(transactionEntity.getTid())){
            transactionProductEntityList.add(transactionProductEntity);
        }
        return transactionProductEntityList;
    }

    @Override
    public List<TransactionProductEntity> getTransactionProductEntityLsitByTransaction(Integer tid){
        return transactionProductRepository.findAllByTansactionTid(tid);
    }


}
