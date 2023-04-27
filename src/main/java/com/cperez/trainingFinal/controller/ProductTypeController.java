package com.cperez.trainingFinal.controller;

import com.cperez.trainingFinal.dto.ProductTypeDTO;
import com.cperez.trainingFinal.model.ProductType;
import com.cperez.trainingFinal.service.ProductTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/productTypes")
@Slf4j
public class ProductTypeController {
    @Autowired
    private ProductTypeService productTypeService;

    @GetMapping("")
    public ResponseEntity<List<ProductType>> getProductTypes(){
        try {
            List<ProductType> productTypes = productTypeService.getProductTypes();
            return ResponseEntity.status(HttpStatus.OK).body(productTypes);
        }
        catch(Exception ex) {
            log.error(ex.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductType> getProductTypeById(@PathVariable int id){
        try {
            ProductType productType = productTypeService.getProductTypeByid(id);
            return ResponseEntity.status(HttpStatus.OK).body(productType);
        }
        catch(Exception ex) {
            log.error(ex.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }
    @PostMapping("")
    @Transactional
    public ResponseEntity<ProductType> createProductTypes(@RequestBody ProductTypeDTO productTypeDTO){
        try {
            ProductType productType = productTypeService.createProductType(productTypeDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(productType);
        }
        catch(Exception ex) {
            log.error(ex.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }
}
