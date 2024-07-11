package com.fsse2401.project_backend.data.transactionProduct.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fsse2401.project_backend.data.product.dto.response.ProductResponseDto;
import com.fsse2401.project_backend.data.transactionProduct.domainObject.response.TransactionProductResponseData;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class TransactionProductResponseDto {
    @JsonProperty("tpid")
    private Integer tpid;
    @JsonProperty("product")
    private ProductResponseDto product;
    @JsonProperty("quantity")
    private Integer quantity;
    @JsonProperty("subtotal")
    private BigDecimal subtotal;

    public TransactionProductResponseDto(TransactionProductResponseData data) {
        this.tpid = data.getTpid();
        this.quantity = data.getQuantity();
        this.subtotal = data.getSubtotal();
        this.product = new ProductResponseDto(data.getProduct());
    }

}
