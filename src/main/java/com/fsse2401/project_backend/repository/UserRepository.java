package com.fsse2401.project_backend.repository;

import com.fsse2401.project_backend.data.user.entity.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<UserEntity, Integer> {
    @Query(value = "SELECT * FROM user u WHERE u.firebase_uid = ?1",
            nativeQuery = true)
    Optional<UserEntity> findByFirebaseUid(String firebaseUid);
}
