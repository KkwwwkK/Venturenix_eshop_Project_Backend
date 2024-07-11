package com.fsse2401.project_backend.data.cartItem.domainObject.response;

import com.fsse2401.project_backend.constant.CartItemResult;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartItemResponseData {
    private CartItemResult result;

    public CartItemResponseData(CartItemResult result) {
        this.result = result;
    }

}
