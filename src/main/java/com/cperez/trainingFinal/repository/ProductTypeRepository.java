package com.cperez.trainingFinal.repository;

import com.cperez.trainingFinal.model.ProductType;
import com.cperez.trainingFinal.model.WarehouseType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductTypeRepository {
    public List<ProductType> getProductTypes();
    public ProductType getProductTypeByid(int id);
    public ProductType createProductType(ProductType productType);
}
