package com.cperez.trainingFinal.model;

import com.cperez.trainingFinal.dto.WarehouseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Warehouse {
    private int id;
    private WarehouseType warehouseType;
    private String name;


    public Warehouse(WarehouseDTO warehouseDTO) {
        WarehouseType warehouseType = new WarehouseType();
        warehouseType.setId(warehouseDTO.getIdWarehouseType());
        this.warehouseType = warehouseType;
        this.name = warehouseDTO.getName();
    }
}
