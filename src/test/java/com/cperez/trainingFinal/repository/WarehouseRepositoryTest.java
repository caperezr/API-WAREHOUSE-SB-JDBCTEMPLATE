package com.cperez.trainingFinal.repository;

import com.cperez.trainingFinal.model.Warehouse;
import com.cperez.trainingFinal.model.WarehouseType;
import com.cperez.trainingFinal.repository.impl.WarehouseRepositoryImpl;
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
public class WarehouseRepositoryTest {
    @Mock
    private JdbcTemplate jdbcTemplate;
    @InjectMocks
    private WarehouseRepositoryImpl warehouseRepositoryImpl;

    @Test
    void getWarehousesTest() {
        WarehouseType warehouseType = new WarehouseType(1,"Warehouse tipo 1");
        Warehouse warehouse1 = new Warehouse(1, warehouseType, "Warehouse 1");
        Warehouse warehouse2 = new Warehouse(2, warehouseType, "Warehouse 2");
        List<Warehouse> expectedWarehouses = new ArrayList<>();
        expectedWarehouses.add(warehouse1);
        expectedWarehouses.add(warehouse2);
        when(jdbcTemplate.query(anyString(), any(RowMapper.class))).thenReturn(expectedWarehouses);
        List<Warehouse> actualWarehouse = warehouseRepositoryImpl.getWarehouses();
        assertEquals(expectedWarehouses.size(), actualWarehouse.size());
        assertEquals(expectedWarehouses, actualWarehouse);
    }

    @Test
    void getWarehouseByIdTest() {
        int id = 1;
        WarehouseType warehouseType = new WarehouseType(1,"Warehouse tipo 1");
        Warehouse expectedWarehouse = new Warehouse(1, warehouseType, "Warehouse 1");
        when(jdbcTemplate.queryForObject(anyString(), any(RowMapper.class), anyInt())).thenReturn(expectedWarehouse);
        Warehouse actualWarehouse = warehouseRepositoryImpl.getWarehouseById(id);
        assertNotNull(actualWarehouse);
        assertEquals(actualWarehouse.getName(), expectedWarehouse.getName());
    }

    @Test
    void mapToWarehouseTest() throws SQLException {
        ResultSet resultSet = mock(ResultSet.class);
        WarehouseType warehouseType = new WarehouseType(1,"Warehouse tipo 1");
        Warehouse expectedWarehouse = new Warehouse(1, warehouseType, "Warehouse 1");
        when(resultSet.getInt("id")).thenReturn(1);
        when(resultSet.getString("name")).thenReturn("Cristhian");
        when(resultSet.getInt("idWarehouseType")).thenReturn(1);
        Warehouse actualWarehouse = warehouseRepositoryImpl.mapToWarehouse(resultSet);
        assertNotNull(actualWarehouse);
        assertEquals(expectedWarehouse.getWarehouseType().getId(), actualWarehouse.getWarehouseType().getId());
    }


}
