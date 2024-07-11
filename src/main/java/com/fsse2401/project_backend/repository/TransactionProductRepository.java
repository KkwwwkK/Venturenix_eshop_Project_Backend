package com.fsse2401.project_backend.repository;

import com.fsse2401.project_backend.data.transactionProduct.entity.TransactionProductEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TransactionProductRepository extends CrudRepository<TransactionProductEntity, Integer> {
    @Query(value = "SELECT * FROM transaction_product tp WHERE tp.tid = ?1",
            nativeQuery = true)
    List<TransactionProductEntity> findAllByTansactionTid(Integer tid);
}
