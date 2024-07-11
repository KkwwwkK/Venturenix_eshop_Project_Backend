package com.fsse2401.project_backend.data.product.domainObject.response;

import com.fsse2401.project_backend.data.product.entity.ProductEntity;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;

@Getter
@Setter
public class GetAllProductResponseData{
    private Integer pid;
    private String name;
    private String imageUrl;
    private BigDecimal price;
    private boolean hasStock;

    public GetAllProductResponseData(ProductEntity entity) {
        this.pid = entity.getPid();
        this.name = entity.getName();
        this.imageUrl = entity.getImageUrl();
        this.price = entity.getPrice();
        hasStock = entity.getStock() > 0;
    }

}
