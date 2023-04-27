package com.cperez.trainingFinal.service.impl;

import com.cperez.trainingFinal.dto.ProductTypeDTO;
import com.cperez.trainingFinal.model.ProductType;
import com.cperez.trainingFinal.repository.ProductTypeRepository;
import com.cperez.trainingFinal.service.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductTypeServiceImpl implements ProductTypeService {
    @Autowired
    private ProductTypeRepository productTypeRepository;

    @Override
    public List<ProductType> getProductTypes() {
        return productTypeRepository.getProductTypes();
    }

    @Override
    public ProductType getProductTypeByid(int id) {
        return productTypeRepository.getProductTypeByid(id);
    }

    @Override
    public ProductType createProductType(ProductTypeDTO productTypeDTO) {
        ProductType productType = new ProductType(productTypeDTO);
        return productTypeRepository.createProductType(productType);
    }
}
