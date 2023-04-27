package com.cperez.trainingFinal.service;

import com.cperez.trainingFinal.model.ProductType;
import com.cperez.trainingFinal.repository.ProductTypeRepository;
import com.cperez.trainingFinal.service.impl.ProductTypeServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.RowMapper;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductTypeServiceTest {
    @Mock
    private ProductTypeRepository productTypeRepository;

    @InjectMocks
    private ProductTypeServiceImpl productTypeServiceImpl;

    @Test
    void getProductTypesTest() {
        ProductType productType1 = new ProductType(1,"Producto tipo 1");
        ProductType productType2 = new ProductType(2,"Producto tipo 2");
        List<ProductType> expectedProductTypes = new ArrayList<>();
        expectedProductTypes.add(productType1);
        expectedProductTypes.add(productType2);
        when(productTypeRepository.getProductTypes()).thenReturn(expectedProductTypes);
        List<ProductType> actualProductTypes = productTypeServiceImpl.getProductTypes();
        assertEquals(expectedProductTypes.size(), actualProductTypes.size());
        assertEquals(expectedProductTypes, actualProductTypes);
    }
    @Test
    void getProductTypeByidTest() {
        int id = 1;
        ProductType expectedProductType = new ProductType(1,"Producto tipo 1");
        when(productTypeRepository.getProductTypeByid(id)).thenReturn(expectedProductType);
        ProductType actualProductType = productTypeServiceImpl.getProductTypeByid(id);
        assertNotNull(actualProductType);
        assertEquals(actualProductType.getName(), expectedProductType.getName());
    }
}
