package com.fsse2401.project_backend.data.product.entity;

import com.fsse2401.project_backend.data.transactionProduct.entity.TransactionProductEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@Entity(name = "product")
@Getter
@Setter
@NoArgsConstructor
public class ProductEntity{
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Integer pid;

    @Column(nullable = false)
    private String name;

    private String description;

    private String imageUrl;
    @Column(nullable = false)
    private BigDecimal price;
    @Column(nullable = false)
    private Integer stock;

    private String stripe_price_id;

    public ProductEntity(TransactionProductEntity entity) {
        setPid(entity.getPid());
        this.name = entity.getName();
        this.description = entity.getDescription();
        this.imageUrl = entity.getImageUrl();
        this.price = entity.getPrice();
        this.stock = entity.getStock();
    }

}
