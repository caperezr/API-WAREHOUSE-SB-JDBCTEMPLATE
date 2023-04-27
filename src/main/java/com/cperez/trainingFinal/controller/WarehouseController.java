package com.cperez.trainingFinal.controller;


import com.cperez.trainingFinal.dto.WarehouseDTO;
import com.cperez.trainingFinal.model.Warehouse;
import com.cperez.trainingFinal.service.WarehouseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/warehouses")
@Slf4j
public class WarehouseController {
    @Autowired
    private WarehouseService warehouseService;

    @GetMapping("")
    public ResponseEntity<List<Warehouse>> getWarehouses(){
        try {
            List<Warehouse> warehouses = warehouseService.getWarehouses();
            return ResponseEntity.status(HttpStatus.OK).body(warehouses);
        }
        catch(Exception ex) {
            log.error(ex.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Warehouse> getWarehousesById(@PathVariable int id){
        try {
            Warehouse warehouse = warehouseService.getWarehouseById(id);
            return ResponseEntity.status(HttpStatus.OK).body(warehouse);
        }
        catch(Exception ex) {
            log.error(ex.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }


    @PostMapping("")
    @Transactional
    public ResponseEntity<Warehouse> createWarehouse(@RequestBody WarehouseDTO warehouseDTO){
        try {
            Warehouse warehouse = warehouseService.createWarehouse(warehouseDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(warehouse);
        }
        catch(Exception ex) {
            log.error(ex.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }
}
