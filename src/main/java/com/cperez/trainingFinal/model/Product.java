package com.cperez.trainingFinal.model;

import com.cperez.trainingFinal.dto.ProductDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private int id;
    private ProductType productType;
    private String name;
    private String sku;
    private String partNumber;
    private double cost;
    private int totalStock;
    private List<WarehouseXProduct> warehouseXProducts;

    public Product(ProductDTO productDTO) {
        ProductType productType = new ProductType();
        productType.setId(productDTO.getIdProductType());
        this.productType = productType;
        this.name = productDTO.getName();
        this.sku = productDTO.getSku();
        this.partNumber = productDTO.getPartNumber();
        this.cost = productDTO.getCost();
        int totalStock = 0;
        for (int i = 0; i < productDTO.getWarehousesDTO().size(); i++) {
            totalStock+=productDTO.getWarehousesDTO().get(i).getStock();
        }
        this.totalStock = totalStock;
    }

}
