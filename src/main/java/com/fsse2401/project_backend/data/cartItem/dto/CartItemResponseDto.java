package com.fsse2401.project_backend.data.cartItem.dto;

import com.fsse2401.project_backend.constant.CartItemResult;
import com.fsse2401.project_backend.data.cartItem.domainObject.response.CartItemResponseData;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartItemResponseDto {
    private CartItemResult result;

    public CartItemResponseDto(CartItemResponseData data){
        this.result = data.getResult();
    }

}
