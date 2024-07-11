package com.fsse2401.project_backend.util;

import com.fsse2401.project_backend.data.cartItem.domainObject.response.GetUserCartResponseData;
import com.fsse2401.project_backend.data.cartItem.dto.GetUserCartResponseDto;
import com.fsse2401.project_backend.data.cartItem.entity.CartItemEntity;

import java.util.ArrayList;
import java.util.List;

public class CartItemDataUtil {

    public static List<GetUserCartResponseDto> toResponseDtoList(List<GetUserCartResponseData> dataList){
        List<GetUserCartResponseDto> getUserCartResponseDtoList = new ArrayList<>();
        for(GetUserCartResponseData getUserCartResponseData: dataList){
            getUserCartResponseDtoList.add(
                    new GetUserCartResponseDto(getUserCartResponseData)
            );
        }
        return getUserCartResponseDtoList;
    }

    public static List<GetUserCartResponseData> toResponseDataList(List<CartItemEntity> cartItemEntityList){
        List<GetUserCartResponseData> getUserCartResponseData = new ArrayList<>();
        for(CartItemEntity cartItemEntity: cartItemEntityList){
            getUserCartResponseData.add(new GetUserCartResponseData(cartItemEntity));
        }
        return getUserCartResponseData;
    }
}
