package com.fsse2401.project_backend.api;

import com.fsse2401.project_backend.config.EnvConfig;
import com.fsse2401.project_backend.data.cartItem.domainObject.response.GetUserCartResponseData;
import com.fsse2401.project_backend.data.cartItem.dto.CartItemResponseDto;
import com.fsse2401.project_backend.data.cartItem.dto.GetUserCartResponseDto;
import com.fsse2401.project_backend.data.cartItem.dto.UpdateUserCartResponseDto;
import com.fsse2401.project_backend.service.CartItemService;
import com.fsse2401.project_backend.util.CartItemDataUtil;
import com.fsse2401.project_backend.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/cart")
@CrossOrigin({
        EnvConfig.DEV_BASE_URL,
        EnvConfig.PROD_BASE_URL,
        EnvConfig.PROD_S3_BASE_URL
})
public class CartItemApi {
    private final CartItemService cartItemService;
    @Autowired
    public CartItemApi(CartItemService cartItemService){
        this.cartItemService = cartItemService;
    }
    @PutMapping("/{pid}/{quantity}")
    public CartItemResponseDto putCartItem(JwtAuthenticationToken jwtToken,
                                           @PathVariable Integer pid,
                                           @PathVariable Integer quantity){

        return new CartItemResponseDto(
                cartItemService.putCartItem(
                        JwtUtil.getFirebaseUserData(jwtToken),
                        pid, quantity
                )
        );
    }

    @GetMapping
    public List<GetUserCartResponseDto> getUserCart(JwtAuthenticationToken jwtToken){

        List<GetUserCartResponseData> getUserCartResponseDataList
                = cartItemService.getUserCartItemList(JwtUtil.getFirebaseUserData(jwtToken));
        return CartItemDataUtil.toResponseDtoList(getUserCartResponseDataList);
    }

    @PatchMapping("/{pid}/{quantity}")
    public UpdateUserCartResponseDto updateUserCartQuantityByPid(JwtAuthenticationToken jwtToken,
                                                            @PathVariable Integer pid,
                                                            @PathVariable Integer quantity){
        return new UpdateUserCartResponseDto(
                cartItemService.UpdateCartQuantityByPid(
                        JwtUtil.getFirebaseUserData(jwtToken),
                        pid, quantity
                )
        );
    }

    @DeleteMapping("/{pid}")
    public CartItemResponseDto removeUserCartByPid(JwtAuthenticationToken jwtToken,
                                                   @PathVariable Integer pid){
        return new CartItemResponseDto(
                cartItemService.removeCartItemByPid(
                        JwtUtil.getFirebaseUserData(jwtToken), pid)
        );
    }



}
