package com.fsse2401.project_backend.data.cartItem.entity;

import com.fsse2401.project_backend.data.product.entity.ProductEntity;
import com.fsse2401.project_backend.data.user.entity.UserEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "cart_item")
@Getter
@Setter
@NoArgsConstructor
public class CartItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cid;
    @ManyToOne
    @JoinColumn(name = "pid", nullable = false)
    private ProductEntity product;
    @ManyToOne
    @JoinColumn(name = "uid", nullable = false)
    private UserEntity user;

    @Column(nullable = false)
    private Integer quantity = 0;

    public CartItemEntity(Integer quantity, ProductEntity productEntity, UserEntity userEntity) {
        this.product = productEntity;
        this.user = userEntity;
        this.quantity += quantity;
    }

}
