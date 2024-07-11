package com.fsse2401.project_backend.data.transactionProduct.entity;

import com.fsse2401.project_backend.data.cartItem.entity.CartItemEntity;
import com.fsse2401.project_backend.data.transaction.entity.TransactionEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity(name = "transaction_product")
@Getter
@Setter
@NoArgsConstructor
public class TransactionProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer tpid;
    @ManyToOne
    @JoinColumn(name = "tid", nullable = false)
    private TransactionEntity tansaction;
    @Column(nullable = false)
    private Integer pid;
    @Column(nullable = false)
    private String name;
    private String description;
    private String imageUrl;
    @Column(nullable = false)
    private BigDecimal price;
    @Column(nullable = false)
    private Integer stock;
    @Column(nullable = false)
    private Integer quantity;


    public TransactionProductEntity(TransactionEntity transactionEntity, CartItemEntity cartItemEntityentity) {
        this.tansaction = transactionEntity;
        this.pid = cartItemEntityentity.getProduct().getPid();
        this.name = cartItemEntityentity.getProduct().getName();
        this.description = cartItemEntityentity.getProduct().getDescription();
        this.imageUrl = cartItemEntityentity.getProduct().getImageUrl();
        this.price = cartItemEntityentity.getProduct().getPrice();
        this.stock = cartItemEntityentity.getProduct().getStock();
        this.quantity = cartItemEntityentity.getQuantity();
    }

}
