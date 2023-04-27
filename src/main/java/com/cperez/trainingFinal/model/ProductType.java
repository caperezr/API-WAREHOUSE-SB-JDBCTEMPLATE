package com.cperez.trainingFinal.model;

import com.cperez.trainingFinal.dto.ProductTypeDTO;
import com.cperez.trainingFinal.dto.WarehouseTypeDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductType {
    private int id;
    private String name;
    public ProductType(ProductTypeDTO productTypeDTO) {
        this.name = productTypeDTO.getName();
    }

}
