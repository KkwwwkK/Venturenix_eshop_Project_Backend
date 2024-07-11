package com.fsse2401.project_backend.repository;

import com.fsse2401.project_backend.data.product.entity.ProductEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends CrudRepository<ProductEntity, Integer> {
//    @Query(
//            value = "SELECT * FROM Users u WHERE u.status = ?1",
//            nativeQuery = true)
    @Query(value = "SELECT * FROM product p WHERE p.pid = ?1",
            nativeQuery = true)
    Optional<ProductEntity> findByPid(Integer pid);


    @Query(value = "SElECT * FROM product",
            nativeQuery = true)
    Iterable<ProductEntity> getAllBy();

//    boolean existsByPid(Integer pid);
    @Query(value = "SELECT pid,description,image_url,name,price,stock FROM product WHERE product.name LIKE %?1%",
            nativeQuery = true)
    List<ProductEntity> findByNameContaining(String userInput);

//    List<ProductEntity> findByDescriptionLike(String userInput);
}
