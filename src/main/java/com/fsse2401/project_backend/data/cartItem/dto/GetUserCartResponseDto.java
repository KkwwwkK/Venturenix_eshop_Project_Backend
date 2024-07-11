package com.fsse2401.project_backend.data.cartItem.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fsse2401.project_backend.data.cartItem.domainObject.response.GetUserCartResponseData;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class GetUserCartResponseDto {
    @JsonProperty("pid")
    private Integer pid;
    @JsonProperty("name")
    private String name;
    @JsonProperty("image_url")
    private String imageUrl;
    @JsonProperty("price")
    private BigDecimal price;
    @JsonProperty("cart_quantity")
    private Integer quantity;
    @JsonProperty("stock")
    private Integer stock;

    public GetUserCartResponseDto(GetUserCartResponseData data) {
        this.pid = data.getPid();
        this.name = data.getName();
        this.imageUrl = data.getImageUrl();
        this.price = data.getPrice();
        this.quantity = data.getQuantity();
        this.stock = data.getStock();
    }

}
