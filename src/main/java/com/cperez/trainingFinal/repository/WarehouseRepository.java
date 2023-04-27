package com.cperez.trainingFinal.repository;

import com.cperez.trainingFinal.model.ProductType;
import com.cperez.trainingFinal.model.Warehouse;
import com.cperez.trainingFinal.model.WarehouseType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WarehouseRepository {
    public List<Warehouse> getWarehouses();
    public Warehouse getWarehouseById(int id);
    public Warehouse createWarehouse(Warehouse warehouse);
}
