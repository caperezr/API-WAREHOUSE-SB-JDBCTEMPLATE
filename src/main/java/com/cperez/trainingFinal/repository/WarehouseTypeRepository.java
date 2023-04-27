package com.cperez.trainingFinal.repository;

import com.cperez.trainingFinal.model.WarehouseType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WarehouseTypeRepository {
    public List<WarehouseType> getWarehouseTypes();
    public WarehouseType getWarehouseTypeById(int id);

    public WarehouseType createWarehouseType(WarehouseType warehouseType);
}
