package com.fsse2401.project_backend.service;

import com.fsse2401.project_backend.data.transaction.domainObject.response.TransactionResponseData;
import com.fsse2401.project_backend.data.transaction.domainObject.response.TransactionStatusSuccessData;
import com.fsse2401.project_backend.data.transaction.entity.TransactionEntity;
import com.fsse2401.project_backend.data.user.domainObject.FirebaseUserData;

public interface TransactionService {
    TransactionResponseData putTransaction(FirebaseUserData firebaseUserData);

    TransactionResponseData getTransactionByTid(FirebaseUserData firebaseUserData, Integer tid);

    TransactionStatusSuccessData UpdateTransactionStatusByTid(FirebaseUserData firebaseUserData, Integer tid);

    TransactionResponseData UpdateTransactionFinishedByTid(FirebaseUserData firebaseUserData, Integer tid);

    TransactionEntity getTransactionByTid(Integer tid, Integer uid);
}
