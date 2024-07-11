package com.fsse2401.project_backend.service.impl;

import com.fsse2401.project_backend.data.product.domainObject.response.GetAllProductResponseData;
import com.fsse2401.project_backend.data.product.domainObject.response.ProductResponseData;
import com.fsse2401.project_backend.data.product.entity.ProductEntity;
import com.fsse2401.project_backend.exception.product.DataMissingException;
import com.fsse2401.project_backend.exception.product.ProductNotFoundException;
import com.fsse2401.project_backend.repository.ProductRepository;
import com.fsse2401.project_backend.service.ProductService;
import com.fsse2401.project_backend.util.ProductDataUtil;
import jakarta.persistence.Cacheable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductSerivceImpl implements ProductService {
    // Add beans
    private final ProductRepository productRepository;

    @Autowired
    public ProductSerivceImpl(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @Override
    // Get Service
    public List<GetAllProductResponseData> getAllProducts(){
        List<GetAllProductResponseData> getAllProductResponseDataList = new ArrayList<>();
        for(ProductEntity productEntity: productRepository.getAllBy()){
            getAllProductResponseDataList.add(new GetAllProductResponseData(productEntity));
        }
        return getAllProductResponseDataList;
    }

    // Logic for get product by id Api
    @Override
    public ProductResponseData getProductById(Integer pid){
        // Get Product
        return new ProductResponseData(getEntityByPid(pid));
    }

    // Logic for get products by user description of product name Api
    @Override
    public List<ProductResponseData> getProductsByUserInput(String userInput){
//        // Check if userInput is empty
//        if (userInput.isEmpty() || userInput.isBlank()){
//            throw new DataMissingException("description is needed");
//        }
//        // Check if no such product
//        if (getProductsByName(userInput).isEmpty()){
//            throw new ProductNotFoundException();
//        }
        // Get products by name description
        return ProductDataUtil.toProductResponseData(getProductsByName(userInput));
    }

    // // Create method to query database for a product by id
    @Override
    public ProductEntity getEntityByPid(Integer pid){
        return productRepository.findByPid(pid).orElseThrow(ProductNotFoundException::new);
    }

    // Create method for querying database to find products by description of product name
    @Override
    public List<ProductEntity> getProductsByName(String userInput){
        return productRepository.findByNameContaining(userInput);
    }

    @Override
    public void updateProductToDatabase(ProductEntity productEntity){
        productRepository.save(productEntity);
    }

}
