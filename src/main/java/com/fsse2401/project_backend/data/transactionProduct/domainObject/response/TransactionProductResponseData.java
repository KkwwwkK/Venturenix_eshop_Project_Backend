package com.fsse2401.project_backend.data.transactionProduct.domainObject.response;

import com.fsse2401.project_backend.data.product.domainObject.response.ProductResponseData;
import com.fsse2401.project_backend.data.product.entity.ProductEntity;
import com.fsse2401.project_backend.data.transactionProduct.entity.TransactionProductEntity;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter

public class TransactionProductResponseData {
    private Integer tpid;
    private ProductResponseData product;
    private Integer quantity;
    private BigDecimal subtotal;

    public TransactionProductResponseData(TransactionProductEntity entity) {
        this.tpid = entity.getTpid();
        this.quantity = entity.getQuantity();
        setProduct(entity);
        this.subtotal = entity.getPrice().
                multiply(BigDecimal.valueOf(entity.getQuantity()));
    }


    public void setProduct(TransactionProductEntity entity) {
        this.product = new ProductResponseData(
                new ProductEntity(
                        entity
                )
        );
    }

}
