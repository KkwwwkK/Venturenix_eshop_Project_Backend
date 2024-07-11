package com.fsse2401.project_backend.util;

import com.fsse2401.project_backend.data.product.domainObject.response.ProductResponseData;
import com.fsse2401.project_backend.data.product.dto.response.ProductResponseDto;
import com.fsse2401.project_backend.data.product.entity.ProductEntity;

import java.util.ArrayList;
import java.util.List;

public class ProductDataUtil {

    public static List<ProductResponseData> toProductResponseData(List<ProductEntity> productEntityList){
        List<ProductResponseData> productResponseDataList = new ArrayList<>();
        for(ProductEntity entity: productEntityList){
            productResponseDataList.add(new ProductResponseData(entity));
        }
        return productResponseDataList;
    }

    public static List<ProductResponseDto> toProductResponseDto(List<ProductResponseData> productResponseDataList){
        List<ProductResponseDto> productResponseDtoList = new ArrayList<>();
        for(ProductResponseData data: productResponseDataList){
            productResponseDtoList.add(new ProductResponseDto(data));
        }
        return productResponseDtoList;
    }
}
