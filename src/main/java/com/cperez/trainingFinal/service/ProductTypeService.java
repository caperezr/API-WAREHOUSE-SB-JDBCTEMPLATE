package com.cperez.trainingFinal.service;

import com.cperez.trainingFinal.dto.ProductTypeDTO;
import com.cperez.trainingFinal.model.ProductType;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ProductTypeService {
    public List<ProductType> getProductTypes();
    public ProductType getProductTypeByid(int id);
    public ProductType createProductType(ProductTypeDTO productTypeDTO);
}
