package com.fsse2401.project_backend.repository;

import com.fsse2401.project_backend.data.transaction.entity.TransactionEntity;
import com.fsse2401.project_backend.data.user.entity.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TransactionRepository extends CrudRepository<TransactionEntity, Integer> {
    @Query(value = "SELECT * FROM transaction t WHERE t.tid = ?1 AND t.uid = ?2",
            nativeQuery = true)
    Optional<TransactionEntity> findByTidAndBuyerUid(Integer tid, Integer uid);

    boolean existsByTid(Integer tid);
}
