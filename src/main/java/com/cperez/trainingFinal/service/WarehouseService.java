package com.cperez.trainingFinal.service;


import com.cperez.trainingFinal.dto.WarehouseDTO;
import com.cperez.trainingFinal.model.Warehouse;

import java.util.List;


public interface WarehouseService {
    public List<Warehouse> getWarehouses();
    public Warehouse getWarehouseById(int id);
    public Warehouse createWarehouse(WarehouseDTO warehouseDTO);

}
