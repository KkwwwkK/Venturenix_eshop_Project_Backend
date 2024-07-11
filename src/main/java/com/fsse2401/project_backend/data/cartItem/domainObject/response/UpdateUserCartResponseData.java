package com.fsse2401.project_backend.data.cartItem.domainObject.response;

import com.fsse2401.project_backend.data.cartItem.entity.CartItemEntity;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;


@Getter
@Setter
public class UpdateUserCartResponseData {
    private Integer pid;
    private String name;
    private String imageUrl;
    private BigDecimal price;
    private Integer quantity;
    private Integer stock;

    public UpdateUserCartResponseData(CartItemEntity entity) {
        this.pid = entity.getProduct().getPid();
        this.name = entity.getProduct().getName();
        this.imageUrl = entity.getProduct().getImageUrl();
        this.price = entity.getProduct().getPrice();
        this.quantity = entity.getQuantity();
        this.stock = entity.getProduct().getStock();
    }

}
