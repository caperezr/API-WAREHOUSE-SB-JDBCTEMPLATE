package com.cperez.trainingFinal.model;

import com.cperez.trainingFinal.dto.WarehouseTypeDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WarehouseType {
    private int id;
    private String name;

    public WarehouseType(WarehouseTypeDTO warehouseTypeDTO) {
        this.name = warehouseTypeDTO.getName();
    }
}
