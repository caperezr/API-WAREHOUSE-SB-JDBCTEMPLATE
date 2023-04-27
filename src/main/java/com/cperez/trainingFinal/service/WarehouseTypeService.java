package com.cperez.trainingFinal.service;

import com.cperez.trainingFinal.dto.WarehouseTypeDTO;
import com.cperez.trainingFinal.model.WarehouseType;
import org.springframework.stereotype.Service;

import java.util.List;


public interface WarehouseTypeService {
    public List<WarehouseType> getWarehouseTypes();
    public WarehouseType getWarehouseTypeById(int id);

    public WarehouseType createWarehouseType(WarehouseTypeDTO warehouseTypeDTO);
}
