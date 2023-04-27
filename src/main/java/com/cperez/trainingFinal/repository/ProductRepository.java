package com.cperez.trainingFinal.repository;

import com.cperez.trainingFinal.model.Product;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository {
    public List<Product> getProducts();
    public Product getProductByid(int id);
    public Product createProduct(Product product);
}
