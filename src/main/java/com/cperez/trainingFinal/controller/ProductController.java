package com.cperez.trainingFinal.controller;

import com.cperez.trainingFinal.dto.ProductDTO;
import com.cperez.trainingFinal.model.Product;
import com.cperez.trainingFinal.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/products")
@Slf4j
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("")
    public ResponseEntity<List<Product>> getProduct(){
        try {
            List<Product> products = productService.getProducts();
            return ResponseEntity.status(HttpStatus.OK).body(products);
        }
        catch(Exception ex) {
            log.error(ex.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable int id){
        try {
            Product product = productService.getProductByid(id);
            return ResponseEntity.status(HttpStatus.OK).body(product);
        }
        catch(Exception ex) {
            log.error(ex.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }
    @PostMapping("")
    @Transactional
    public ResponseEntity<Product> createProduct(@RequestBody ProductDTO productDTO){
        try {
            Product product = productService.createProduct(productDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(product);
        }
        catch(Exception ex) {
            log.error(ex.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }
}
