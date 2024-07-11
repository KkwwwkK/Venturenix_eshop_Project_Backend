package com.fsse2401.project_backend.data.transaction.entity;

import com.fsse2401.project_backend.constant.TransactionStatus;
import com.fsse2401.project_backend.data.user.entity.UserEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity(name = "transaction")
@Getter
@Setter
@NoArgsConstructor
public class TransactionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer tid;
    @ManyToOne
    @JoinColumn(name = "uid", nullable = false)
    private UserEntity buyer;
    @Column(nullable = false)
    private LocalDateTime datetime;
    @Column(nullable = false)
    private String status;
    @Column(nullable = false)
    private BigDecimal total;

    public TransactionEntity(UserEntity user) {
        this.buyer = user;
        this.datetime = LocalDateTime.now();
        this.status = TransactionStatus.PREPARE.toString();
        total = BigDecimal.ZERO;
    }

}