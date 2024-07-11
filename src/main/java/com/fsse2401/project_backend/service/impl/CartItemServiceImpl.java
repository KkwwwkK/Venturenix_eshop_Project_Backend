package com.fsse2401.project_backend.service.impl;

import com.fsse2401.project_backend.constant.CartItemResult;
import com.fsse2401.project_backend.data.cartItem.domainObject.response.CartItemResponseData;
import com.fsse2401.project_backend.data.cartItem.domainObject.response.GetUserCartResponseData;
import com.fsse2401.project_backend.data.cartItem.domainObject.response.UpdateUserCartResponseData;
import com.fsse2401.project_backend.data.cartItem.entity.CartItemEntity;
import com.fsse2401.project_backend.data.product.entity.ProductEntity;
import com.fsse2401.project_backend.data.user.domainObject.FirebaseUserData;
import com.fsse2401.project_backend.data.user.entity.UserEntity;
import com.fsse2401.project_backend.exception.cartItem.AddQuantityException;
import com.fsse2401.project_backend.exception.cartItem.CartItemNotFoundException;
import com.fsse2401.project_backend.exception.cartItem.GetUserCartException;
import com.fsse2401.project_backend.exception.product.DataMissingException;
import com.fsse2401.project_backend.repository.CartItemRepository;
import com.fsse2401.project_backend.service.CartItemService;
import com.fsse2401.project_backend.service.ProductService;
import com.fsse2401.project_backend.service.UserService;
import com.fsse2401.project_backend.util.CartItemDataUtil;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartItemServiceImpl implements CartItemService {
    private final UserService userService;
    private final ProductService productService;
    private final CartItemRepository cartItemRepository;

    @Autowired
    public CartItemServiceImpl(UserService userService,
                               ProductService productService,
                               CartItemRepository cartItemRepository){
        this.userService = userService;
        this.productService = productService;
        this.cartItemRepository = cartItemRepository;

    }

    @Override
    public CartItemResponseData putCartItem(FirebaseUserData firebaseUserData,
                                            Integer pid, Integer quantity){
        // Check if quantity is negative
        if (quantity <= 0){
            throw new AddQuantityException();
        }
        // Check if pid or quantity is null << Not working yet
        if (pid == null){
            throw new DataMissingException("pid input invalid!");
        }
        if (quantity == null){
            throw new DataMissingException("quantity input invalid!");
        }
        // Get user entity
        UserEntity userEntity = userService.getEntityByFirebaseUserData(firebaseUserData);
        // Get product entity and check if product existed
        ProductEntity productEntity = productService.getEntityByPid(pid);

        // Search if cart item exists
        if(isCartItemExistsByUserAndProduct(userEntity.getUid(), productEntity.getPid())){
            CartItemEntity cartItemEntity = getCartItemEntityByUserAndProduct(userEntity.getUid(), productEntity.getPid());
            // Check if quantity bigger than product's stock
            if (quantity + cartItemEntity.getQuantity() > productEntity.getStock()){
                // May change to throw new exception
                throw new AddQuantityException();
            }
            cartItemEntity.setQuantity(cartItemEntity.getQuantity() + quantity);
            cartItemRepository.save(cartItemEntity);
        } else {
            // Check if quantity bigger than product's stock
            if (quantity > productEntity.getStock()){
                // May change to throw new exception
                throw new AddQuantityException();
            }
            // Add new cart item if do not find exist cartItem
            CartItemEntity cartItemEntity = new CartItemEntity(quantity, productEntity, userEntity);
            cartItemRepository.save(cartItemEntity);
        }
        return new CartItemResponseData(CartItemResult.SUCCESS);
    }

    @Override
    public List<GetUserCartResponseData> getUserCartItemList(FirebaseUserData firebaseUserData){
        // Get user entity
        UserEntity userEntity = userService.getEntityByFirebaseUserData(firebaseUserData);

        // Get cart item list entity
        List<CartItemEntity> cartItemEntityList = cartItemRepository.findAllByUserUid(userEntity.getUid());

        // return list
        return CartItemDataUtil.toResponseDataList(cartItemEntityList);
    }

    @Override
    public UpdateUserCartResponseData UpdateCartQuantityByPid(FirebaseUserData firebaseUserData,
                                                              Integer pid, Integer quantity){
        // Get user entity
        UserEntity userEntity = userService.getEntityByFirebaseUserData(firebaseUserData);
        // Get product entity and check if product existed
        ProductEntity productEntity = productService.getEntityByPid(pid);

        // Check if pid or quantity is null
        if (pid == null){
            throw new DataMissingException("pid input invalid!");
        }
        if (quantity == null){
            throw new DataMissingException("quantity input invalid!");
        }
        // Check if quantity is negative and if quantity bigger than product's stock
        if (quantity <= 0){
            throw new AddQuantityException();
        }
        // Get and Search if cart item exists
        CartItemEntity cartItemEntity = getCartItemEntityByUserAndProduct(userEntity.getUid(), productEntity.getPid());
        // Check if quantity bigger than stock
        if(quantity > productEntity.getStock()){
            throw new AddQuantityException();
        }
        cartItemEntity.setQuantity(quantity);
        return new UpdateUserCartResponseData(cartItemRepository.save(cartItemEntity));

    }
    @Transactional
    @Override
    public CartItemResponseData removeCartItemByPid(FirebaseUserData firebaseUserData, Integer pid){
        // Get user entity
        UserEntity userEntity = userService.getEntityByFirebaseUserData(firebaseUserData);
        // Get product entity and check if product existed
        ProductEntity productEntity = productService.getEntityByPid(pid);
        // Check if pid or quantity is null << Not working yet
        if (pid == null){
            throw new DataMissingException("pid input invalid!");
        }
        // Search if cart item exists
        if(isCartItemExistsByUserAndProduct(userEntity.getUid(), productEntity.getPid())){
            // Delete the cart item
            cartItemRepository.deleteByUserAndProduct(userEntity, productEntity);
        } else {
            throw new CartItemNotFoundException();
        }
        return new CartItemResponseData(CartItemResult.SUCCESS);
    }

    @Override
    // Create method of getting cart item entity list by user entity
    public List<CartItemEntity> getCartItemEntityList(Integer uid){
        // Get cart item entity list
        List<CartItemEntity> cartItemEntityList = cartItemRepository.findAllByUserUid(uid);
        // Search if cart item list is empty
        if (cartItemEntityList.isEmpty()){
            throw new CartItemNotFoundException();
        }
        return cartItemEntityList;
    }

    @Override
    public boolean isCartItemExistsByUserAndProduct(Integer uid, Integer pid){
        return cartItemRepository.existsByUserUidAndProductPid(uid, pid);
    }

    @Override
    public CartItemEntity getCartItemEntityByUserAndProduct(Integer uid, Integer pid){
        return cartItemRepository.findByUserUidAndProductPid(uid, pid).
                orElseThrow(CartItemNotFoundException::new);
    }

    @Override
    public void deleteCartItemListByUser(UserEntity user){
        if(cartItemRepository.existsAllByUser(user)){
            cartItemRepository.deleteAllByUser(user);
        } else {
            throw new CartItemNotFoundException();
        }
    }




}
