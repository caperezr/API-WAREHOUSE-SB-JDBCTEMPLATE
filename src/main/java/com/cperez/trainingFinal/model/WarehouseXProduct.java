package com.cperez.trainingFinal.model;

import com.cperez.trainingFinal.dto.WarehouseXProductDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WarehouseXProduct {
    private int id;
    private Warehouse warehouse;
    private int stock;

    public WarehouseXProduct(WarehouseXProductDTO warehouseXProductDTO) {
        Warehouse warehouse = new Warehouse();
        warehouse.setId(warehouseXProductDTO.getIdWarehouse());
        this.warehouse = warehouse;
        this.stock = warehouseXProductDTO.getStock();
    }

}
