package com.cperez.trainingFinal.repository.impl;

import com.cperez.trainingFinal.model.Product;
import com.cperez.trainingFinal.model.ProductType;
import com.cperez.trainingFinal.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ProductRepositoryImpl implements ProductRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;


    public Product mapToProduct(ResultSet rs) throws SQLException {
        Product product = new Product();
        product.setId(rs.getInt("id"));

        ProductType productType = new ProductType();
        productType.setId(rs.getInt("idProductType"));
        product.setProductType(productType);

        product.setName(rs.getString("name"));
        product.setSku(rs.getString("sku"));
        product.setPartNumber(rs.getString("partNumber"));
        product.setCost(rs.getDouble("cost"));
        product.setTotalStock(rs.getInt("totalStock"));
        return product;
    }

    @Override
    public List<Product> getProducts() {
        String sql = "CALL ListAllProducts();";
        List<Product> products = jdbcTemplate.query(sql, (rs, rowNum) -> mapToProduct(rs));
        return products;
    }

    @Override
    public Product getProductByid(int id) {
        String sql = "CALL GetProductById(?);";
        Product product = jdbcTemplate.queryForObject(sql, (rs, rowNum) -> mapToProduct(rs), id);
        return product;
    }

    @Override
    public Product createProduct(Product product) {
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate.getDataSource())
                .withTableName("Product")
                .usingGeneratedKeyColumns("id");
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("idProductType", product.getProductType().getId());
        parameters.put("name", product.getName());
        parameters.put("sku", product.getSku());
        parameters.put("partNumber", product.getPartNumber());
        parameters.put("cost", product.getCost());
        parameters.put("totalStock", product.getTotalStock());
        int id = simpleJdbcInsert.executeAndReturnKey(parameters).intValue();
        product.setId(id);
        return product;
    }
}
