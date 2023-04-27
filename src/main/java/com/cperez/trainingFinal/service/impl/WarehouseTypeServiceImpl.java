package com.cperez.trainingFinal.service.impl;

import com.cperez.trainingFinal.dto.WarehouseTypeDTO;
import com.cperez.trainingFinal.model.WarehouseType;
import com.cperez.trainingFinal.repository.WarehouseTypeRepository;
import com.cperez.trainingFinal.service.WarehouseTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WarehouseTypeServiceImpl implements WarehouseTypeService {
    @Autowired
    private WarehouseTypeRepository warehouseTypeRepository;
    @Override
    public List<WarehouseType> getWarehouseTypes() {
        return warehouseTypeRepository.getWarehouseTypes();
    }

    @Override
    public WarehouseType getWarehouseTypeById(int id) {
        return warehouseTypeRepository.getWarehouseTypeById(id);
    }

    @Override
    public WarehouseType createWarehouseType(WarehouseTypeDTO warehouseTypeDTO) {
        WarehouseType warehouseType = new WarehouseType(warehouseTypeDTO);
        return warehouseTypeRepository.createWarehouseType(warehouseType);
    }
}
