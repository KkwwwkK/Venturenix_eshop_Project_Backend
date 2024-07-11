package com.fsse2401.project_backend.api;

import com.fsse2401.project_backend.config.EnvConfig;
import com.fsse2401.project_backend.data.product.domainObject.response.GetAllProductResponseData;
import com.fsse2401.project_backend.data.product.domainObject.response.ProductResponseData;
import com.fsse2401.project_backend.data.product.dto.response.GetAllProductResponseDto;
import com.fsse2401.project_backend.data.product.dto.response.ProductResponseDto;
import com.fsse2401.project_backend.service.ProductService;
import com.fsse2401.project_backend.util.ProductDataUtil;
import jakarta.annotation.security.PermitAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/public/product")
@CrossOrigin({
        EnvConfig.DEV_BASE_URL,
        EnvConfig.PROD_BASE_URL,
        EnvConfig.PROD_S3_BASE_URL
})
public class ProductApi {
    // Add bean
    private final ProductService productService;
    @Autowired
    public ProductApi(ProductService productService){
        this.productService = productService;
    }

    @GetMapping
    @CachePut(value = "GetAllProductCache")
//    @CrossOrigin("*")
    public List<GetAllProductResponseDto> getAllProducts(){
        List<GetAllProductResponseDto> getAllProductResponseDtoList = new ArrayList<>();
        for(GetAllProductResponseData productResponseData: productService.getAllProducts()){
            getAllProductResponseDtoList.add(new GetAllProductResponseDto(productResponseData));
        }
        return getAllProductResponseDtoList;
    }

    @GetMapping("/{id}")
//    @CachePut(value = "GetProductByIdCache", key="#id")
//    @CrossOrigin("*")
    public ProductResponseDto getProductById(@PathVariable Integer id){
        return new ProductResponseDto(
                productService.getProductById(id)
        );
    }

    @GetMapping("/all/{user_input}")
//    @CachePut(value = "GetProductsByUserInputCache", key="#user_input")
//    @CrossOrigin("*")
    public List<ProductResponseDto> getProductsByUserInput(@PathVariable String user_input){
        return ProductDataUtil.toProductResponseDto(
                productService.getProductsByUserInput(user_input));
    }

}
