package com.cperez.trainingFinal.repository;

import com.cperez.trainingFinal.model.ProductType;
import com.cperez.trainingFinal.repository.impl.ProductTypeRepositoryImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductTypeRepositoryTest {
    @Mock
    private JdbcTemplate jdbcTemplate;
    @InjectMocks
    private ProductTypeRepositoryImpl productTypeRepositoryImpl;

    @Test
    void getProductTypesTest() {
        ProductType productType1 = new ProductType(1,"Producto tipo 1");
        ProductType productType2 = new ProductType(2,"Producto tipo 2");
        List<ProductType> expectedProductTypes = new ArrayList<>();
        expectedProductTypes.add(productType1);
        expectedProductTypes.add(productType2);
        when(jdbcTemplate.query(anyString(), any(RowMapper.class))).thenReturn(expectedProductTypes);
        List<ProductType> actualProductTypes = productTypeRepositoryImpl.getProductTypes();
        assertEquals(expectedProductTypes.size(), actualProductTypes.size());
        assertEquals(expectedProductTypes, actualProductTypes);
    }

    @Test
    void getProductTypeByidTest() {
        int id = 1;
        ProductType expectedProductType = new ProductType(1,"Producto tipo 1");
        when(jdbcTemplate.queryForObject(anyString(), any(RowMapper.class), anyInt())).thenReturn(expectedProductType);
        ProductType actualProductType = productTypeRepositoryImpl.getProductTypeByid(id);
        assertNotNull(actualProductType);
        assertEquals(actualProductType.getName(), expectedProductType.getName());
    }

    @Test
    void mapToProductTypeTest() throws SQLException {
        ResultSet resultSet = mock(ResultSet.class);
        ProductType expectedProductType = new ProductType(1,"Producto tipo 1");
        when(resultSet.getInt("id")).thenReturn(1);
        when(resultSet.getString("name")).thenReturn("Producto tipo 1");
        ProductType actualProductType = productTypeRepositoryImpl.mapToProductType(resultSet);
        assertNotNull(actualProductType);
        assertEquals(actualProductType.getId(), expectedProductType.getId());
        assertEquals(actualProductType.getName(), expectedProductType.getName());
    }
}
