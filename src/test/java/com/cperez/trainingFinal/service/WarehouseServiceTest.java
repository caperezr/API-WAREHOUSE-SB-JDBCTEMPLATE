package com.cperez.trainingFinal.service;

import com.cperez.trainingFinal.model.Warehouse;
import com.cperez.trainingFinal.model.WarehouseType;
import com.cperez.trainingFinal.repository.ProductTypeRepository;
import com.cperez.trainingFinal.repository.WarehouseRepository;
import com.cperez.trainingFinal.repository.WarehouseTypeRepository;
import com.cperez.trainingFinal.service.impl.ProductTypeServiceImpl;
import com.cperez.trainingFinal.service.impl.WarehouseServiceImpl;
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
public class WarehouseServiceTest {
    @Mock
    private WarehouseRepository warehouseRepository;
    @Mock
    private WarehouseTypeRepository warehouseTypeRepository;

    @InjectMocks
    private WarehouseServiceImpl warehouseServiceImpl;

    @Test
    void getWarehousesTest() {
        WarehouseType warehouseType = new WarehouseType(1,"Warehouse tipo 1");
        Warehouse warehouse1 = new Warehouse(1, warehouseType, "Warehouse 1");
        Warehouse warehouse2 = new Warehouse(2, warehouseType, "Warehouse 2");
        List<Warehouse> expectedWarehouses = new ArrayList<>();
        expectedWarehouses.add(warehouse1);
        expectedWarehouses.add(warehouse2);

        when(warehouseRepository.getWarehouses()).thenReturn(expectedWarehouses);
        when(warehouseTypeRepository.getWarehouseTypeById(1)).thenReturn(warehouseType);

        List<Warehouse> actualWarehouses = warehouseServiceImpl.getWarehouses();
        assertNotNull(actualWarehouses);
        assertEquals(2, actualWarehouses.size());

    }

    @Test
    void getWarehouseById() {
        int id = 1;
        WarehouseType warehouseType = new WarehouseType(1,"Warehouse tipo 1");
        Warehouse expectedWarehouse = new Warehouse(1, warehouseType, "Warehouse 1");

        when(warehouseRepository.getWarehouseById(id)).thenReturn(expectedWarehouse);
        Warehouse actualWarehouse = warehouseServiceImpl.getWarehouseById(id);
        assertNotNull(actualWarehouse);
        assertEquals(actualWarehouse.getName(), expectedWarehouse.getName());
    }
}
