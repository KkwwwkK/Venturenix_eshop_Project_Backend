package com.fsse2401.project_backend.service;

import com.fsse2401.project_backend.data.cartItem.domainObject.response.CartItemResponseData;
import com.fsse2401.project_backend.data.cartItem.domainObject.response.GetUserCartResponseData;
import com.fsse2401.project_backend.data.cartItem.domainObject.response.UpdateUserCartResponseData;
import com.fsse2401.project_backend.data.cartItem.entity.CartItemEntity;
import com.fsse2401.project_backend.data.product.entity.ProductEntity;
import com.fsse2401.project_backend.data.user.domainObject.FirebaseUserData;
import com.fsse2401.project_backend.data.user.entity.UserEntity;

import java.util.List;

public interface CartItemService {
    CartItemResponseData putCartItem(FirebaseUserData firebaseUserData,
                                     Integer pid, Integer quantity);

    List<GetUserCartResponseData> getUserCartItemList(FirebaseUserData firebaseUserData);

    UpdateUserCartResponseData UpdateCartQuantityByPid(FirebaseUserData firebaseUserData,
                                                       Integer pid, Integer quantity);

    CartItemResponseData removeCartItemByPid(FirebaseUserData firebaseUserData, Integer pid);

    // Create method of getting cart item entity list by user entity
    List<CartItemEntity> getCartItemEntityList(Integer uid);

    boolean isCartItemExistsByUserAndProduct(Integer uid, Integer pid);

    CartItemEntity getCartItemEntityByUserAndProduct(Integer uid, Integer pid);

    void deleteCartItemListByUser(UserEntity user);
}
