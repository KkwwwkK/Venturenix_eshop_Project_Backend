package com.fsse2401.project_backend.service.impl;

import com.fsse2401.project_backend.constant.TransactionResult;
import com.fsse2401.project_backend.constant.TransactionStatus;
import com.fsse2401.project_backend.data.cartItem.entity.CartItemEntity;
import com.fsse2401.project_backend.data.product.entity.ProductEntity;
import com.fsse2401.project_backend.data.transaction.domainObject.response.TransactionResponseData;
import com.fsse2401.project_backend.data.transaction.domainObject.response.TransactionStatusSuccessData;
import com.fsse2401.project_backend.data.transaction.entity.TransactionEntity;
import com.fsse2401.project_backend.data.transactionProduct.domainObject.response.TransactionProductResponseData;
import com.fsse2401.project_backend.data.transactionProduct.entity.TransactionProductEntity;
import com.fsse2401.project_backend.data.user.domainObject.FirebaseUserData;
import com.fsse2401.project_backend.data.user.entity.UserEntity;
import com.fsse2401.project_backend.exception.product.DataMissingException;
import com.fsse2401.project_backend.exception.transaction.PayTransactionException;
import com.fsse2401.project_backend.exception.transaction.UpdateStockException;
import com.fsse2401.project_backend.exception.transactionProduct.TransactionNotFoundException;
import com.fsse2401.project_backend.repository.TransactionRepository;
import com.fsse2401.project_backend.service.*;
import com.fsse2401.project_backend.util.TransactionProductDataUtil;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {
    private final UserService userService;
    private final ProductService productService;
    private final CartItemService cartItemService;
    private final TransactionProductService transactionProductService;
    private final TransactionRepository transactionRepository;

    @Autowired
    public TransactionServiceImpl(UserService userService,
                                  ProductService productService,
                                  TransactionProductService transactionProductService,
                                  CartItemService cartItemService,
                                  TransactionRepository transactionRepository) {
        this.userService = userService;
        this.productService = productService;
        this.transactionProductService = transactionProductService;
        this.transactionRepository = transactionRepository;
        this.cartItemService = cartItemService;
    }
    @Override
    public TransactionResponseData putTransaction(FirebaseUserData firebaseUserData){
        // Get user entity
        UserEntity userEntity = userService.getEntityByFirebaseUserData(firebaseUserData);
        // Get cart item entity list and check if empty in cartItemService
        List<CartItemEntity> cartItemEntityList = cartItemService.getCartItemEntityList(userEntity.getUid());
        // Create transaction entity
        TransactionEntity transactionEntity = new TransactionEntity(userEntity);
        transactionRepository.save(transactionEntity);

        // To return and set total
        TransactionResponseData transactionResponseData = new TransactionResponseData(transactionEntity);
        for(TransactionProductEntity transactionProductEntity: transactionProductService.putTransactionProduct(
//                firebaseUserData,
                transactionEntity,
                cartItemEntityList
        )){
            // Calculation of total and set total
            transactionEntity.setTotal(
                    transactionEntity.getTotal().add(
                            TransactionProductDataUtil.getSubtotalByTransactionProductEntity(transactionProductEntity))
                    );
            transactionResponseData.setTotal(transactionEntity.getTotal());
            // To domainObject
            transactionResponseData.getItems().add(new TransactionProductResponseData(transactionProductEntity));
        }
        transactionRepository.save(transactionEntity);
        return transactionResponseData;
    }
    @Override
    public TransactionResponseData getTransactionByTid(FirebaseUserData firebaseUserData, Integer tid){
        // Get user entity
        UserEntity userEntity = userService.getEntityByFirebaseUserData(firebaseUserData);
        // Check if tid is null
        if (tid == null){
            throw new DataMissingException("tid is needed!");
        }
        // Get and Check if TransactionEntity exists
        TransactionEntity transactionEntity = getTransactionByTid(tid, userEntity.getUid());
        // Get TransactionProductEntity list
        List<TransactionProductEntity> transactionProductEntityList
                = transactionProductService.getTransactionProductEntityLsitByTransaction(transactionEntity.getTid());

        // Return TransactionResponseData
        TransactionResponseData transactionResponseData = new TransactionResponseData(transactionEntity);
        for(TransactionProductEntity transactionProductEntity: transactionProductEntityList){
            transactionResponseData.getItems().add(new TransactionProductResponseData(transactionProductEntity));
        }
        return transactionResponseData;
    }

    @Override
    public TransactionStatusSuccessData UpdateTransactionStatusByTid(FirebaseUserData firebaseUserData, Integer tid){
        // Get user entity
        UserEntity userEntity = userService.getEntityByFirebaseUserData(firebaseUserData);
        // Check if tid is null
        if (tid == null){
            throw new DataMissingException("tid is needed!");
        }
        // Get and Check if TransactionEntity exists
        TransactionEntity transactionEntity = getTransactionByTid(tid, userEntity.getUid());
        // Check if Transaction Status is Prepare
        if (!transactionEntity.getStatus().equals(TransactionStatus.PREPARE.toString())){
            throw new PayTransactionException();
        }
        // Get list of TransactionProductEntity
        List<TransactionProductEntity> transactionProductEntityList
                = transactionProductService.getTransactionProductEntityLsitByTransaction(transactionEntity.getTid());
        // Update product stock
        for(TransactionProductEntity transactionProductEntity: transactionProductEntityList){
           ProductEntity productEntity = productService.getEntityByPid(transactionProductEntity.getPid());
           // Check if quantity is bigger than stock
            if(transactionProductEntity.getQuantity() > productEntity.getStock()){
                throw new UpdateStockException(productEntity.getStock(), transactionProductEntity.getQuantity());
            }
           productEntity.setStock(productEntity.getStock() - transactionProductEntity.getQuantity());
           productService.updateProductToDatabase(productEntity);
        }
        // Update transaction status to Processing
        transactionEntity.setStatus(TransactionStatus.PROCESSING.toString());
        transactionRepository.save(transactionEntity);
        // Return
        return new TransactionStatusSuccessData(TransactionResult.SUCCESS);
    }
    @Transactional
    @Override
    public TransactionResponseData UpdateTransactionFinishedByTid(FirebaseUserData firebaseUserData, Integer tid){
        // Get user entity
        UserEntity userEntity = userService.getEntityByFirebaseUserData(firebaseUserData);
        // Check if tid is null
        if (tid == null){
            throw new DataMissingException("tid is needed!");
        }
        // Get and Check if TransactionEntity exists
        TransactionEntity transactionEntity = getTransactionByTid(tid, userEntity.getUid());
        //Check if transaction status is processing
        if (!transactionEntity.getStatus().equals(TransactionStatus.PROCESSING.toString())){
            throw new PayTransactionException();
        }
        // Update transaction status to Processing
        transactionEntity.setStatus(TransactionStatus.SUCCESS.toString());
        transactionRepository.save(transactionEntity);
        // Get TransactionProductEntity list
        List<TransactionProductEntity> transactionProductEntityList
                = transactionProductService.getTransactionProductEntityLsitByTransaction(transactionEntity.getTid());
        // Return TransactionResponseData
        TransactionResponseData transactionResponseData = new TransactionResponseData(transactionEntity);
        for(TransactionProductEntity transactionProductEntity: transactionProductEntityList){
            transactionResponseData.getItems().add(new TransactionProductResponseData(transactionProductEntity));
        }
        // Empty cart
        cartItemService.deleteCartItemListByUser(userEntity);
        // Return status
        return transactionResponseData;
    }

    @Override
    public TransactionEntity getTransactionByTid(Integer tid, Integer uid){
        return transactionRepository.findByTidAndBuyerUid(tid, uid).orElseThrow(TransactionNotFoundException::new);
    }
}
