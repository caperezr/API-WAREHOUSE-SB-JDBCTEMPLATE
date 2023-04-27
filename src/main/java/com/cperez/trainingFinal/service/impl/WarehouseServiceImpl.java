package com.cperez.trainingFinal.service.impl;


import com.cperez.trainingFinal.dto.WarehouseDTO;
import com.cperez.trainingFinal.model.Warehouse;
import com.cperez.trainingFinal.model.WarehouseXProduct;
import com.cperez.trainingFinal.repository.ProductRepository;
import com.cperez.trainingFinal.repository.WarehouseRepository;
import com.cperez.trainingFinal.repository.WarehouseTypeRepository;
import com.cperez.trainingFinal.repository.WarehouseXProductRepository;
import com.cperez.trainingFinal.service.WarehouseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class WarehouseServiceImpl implements WarehouseService {
    @Autowired
    private WarehouseRepository warehouseRepository;
    @Autowired
    private WarehouseTypeRepository warehouseTypeRepository;
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private WarehouseXProductRepository warehouseXProductRepository;

    @Override
    public List<Warehouse> getWarehouses() {
        List<Warehouse> warehouses = warehouseRepository.getWarehouses();
        for (Warehouse warehouse: warehouses) {
            warehouse.setWarehouseType(warehouseTypeRepository.getWarehouseTypeById(warehouse.getWarehouseType().getId()));
        }
        return warehouses;
    }



    @Override
    public Warehouse getWarehouseById(int id) {
        Warehouse warehouse = warehouseRepository.getWarehouseById(id);
        warehouse.setWarehouseType(warehouseTypeRepository.getWarehouseTypeById(warehouse.getWarehouseType().getId()));
        List<WarehouseXProduct> warehouseXProducts = warehouseXProductRepository.getWarehouseXProductsByWarehouseId(id);
        for (WarehouseXProduct warehouseXProduct:warehouseXProducts) {
            System.out.println();
            //warehouseXProduct.setWarehouse(warehouseRepository.getWarehouseById(warehouseXProduct.getWarehouse().getId()));
            //warehouseXProduct.getWarehouse().setWarehouseType(warehouseTypeRepository.getWarehouseTypeById(warehouseXProduct.getWarehouse().getWarehouseType().getId()));
        }
        //warehouse.setWarehouseXProductsByIdWarehouse(warehouseXProducts);
        return warehouse;
    }

    @Override
    public Warehouse createWarehouse(WarehouseDTO warehouseDTO) {
        try {
            Warehouse warehouse = new Warehouse(warehouseDTO);
            warehouse.setWarehouseType(warehouseTypeRepository.getWarehouseTypeById(warehouse.getWarehouseType().getId()));
            System.out.println("Nombre: "+warehouseDTO.getName());
            System.out.println("Nombre: "+warehouse.getName());
            warehouse = warehouseRepository.createWarehouse(warehouse);
            return warehouse;
        } catch (Exception ex) {
            log.error(ex.getMessage());
            throw ex;
        }


    }
}
