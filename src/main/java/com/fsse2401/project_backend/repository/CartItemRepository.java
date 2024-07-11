package com.fsse2401.project_backend.repository;

import com.fsse2401.project_backend.data.cartItem.entity.CartItemEntity;
import com.fsse2401.project_backend.data.product.entity.ProductEntity;
import com.fsse2401.project_backend.data.user.entity.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CartItemRepository extends CrudRepository<CartItemEntity, Integer> {
    @Query(value = "SELECT * FROM cart_item ci WHERE ci.uid = ?1 AND ci.pid = ?2",
            nativeQuery = true)
    Optional<CartItemEntity> findByUserUidAndProductPid(Integer userId, Integer pid);
    @Query(value = "SELECT * FROM cart_item ci WHERE ci.uid = ?1",
            nativeQuery = true)
    List<CartItemEntity> findAllByUserUid(Integer uid);

//    @Query(value = "SELECT EXISTS(SELECT 1 FROM cart_item ci WHERE ci.uid = ?1 AND ci.pid = ?2)",
//            nativeQuery = true) -- didnt work
    boolean existsByUserUidAndProductPid(Integer userId, Integer pid);

    boolean existsAllByUser(UserEntity user);

    void deleteAllByUser(UserEntity user);

    void deleteByUserAndProduct(UserEntity user, ProductEntity product);

}
