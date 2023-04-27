package com.cperez.trainingFinal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WarehouseXProductDTO {
    private int idWarehouse;
    private int stock;
}
