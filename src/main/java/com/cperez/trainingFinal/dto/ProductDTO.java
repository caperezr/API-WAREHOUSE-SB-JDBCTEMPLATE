package com.cperez.trainingFinal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductDTO {
    private int idProductType;
    private String name;
    private String sku;
    private String partNumber;
    private double cost;
    private ArrayList<WarehouseXProductDTO> warehousesDTO;

}
