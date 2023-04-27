package com.cperez.trainingFinal.service;

import com.cperez.trainingFinal.dto.ProductDTO;
import com.cperez.trainingFinal.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ProductService {
    List<Product> getProducts();
    Product getProductByid(int id);
    Product createProduct(ProductDTO productDTO);
}
