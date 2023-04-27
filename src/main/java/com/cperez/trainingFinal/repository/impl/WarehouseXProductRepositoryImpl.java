package com.cperez.trainingFinal.repository.impl;

import com.cperez.trainingFinal.model.Product;
import com.cperez.trainingFinal.model.Warehouse;
import com.cperez.trainingFinal.model.WarehouseType;
import com.cperez.trainingFinal.model.WarehouseXProduct;
import com.cperez.trainingFinal.repository.WarehouseXProductRepository;
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
public class WarehouseXProductRepositoryImpl implements WarehouseXProductRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public WarehouseXProduct mapToWarehouseXProduct(ResultSet rs) throws SQLException {
        WarehouseXProduct warehouseXProduct = new WarehouseXProduct();
        warehouseXProduct.setId(rs.getInt("id"));
        Warehouse warehouse = new Warehouse();
        warehouse.setId(rs.getInt("idWarehouse"));
        Product product = new Product();
        product.setId(rs.getInt("idProduct"));
        warehouseXProduct.setStock(rs.getInt("stock"));
        warehouseXProduct.setWarehouse(warehouse);
        return warehouseXProduct;
    }

    @Override
    public List<WarehouseXProduct> getWarehouseXProductsByProductId(int id) {
        String sql = "SELECT * FROM WarehouseXProduct WHERE idProduct = ?;";
        List<WarehouseXProduct> warehouseXProducts = jdbcTemplate.query(sql, (rs, rowNum) -> mapToWarehouseXProduct(rs), id);
        return warehouseXProducts;
    }

    @Override
    public List<WarehouseXProduct> getWarehouseXProductsByWarehouseId(int id) {
        String sql = "SELECT * FROM WarehouseXProduct WHERE idWarehouse = ?;";
        List<WarehouseXProduct> warehouseXProducts = jdbcTemplate.query(sql, (rs, rowNum) -> mapToWarehouseXProduct(rs), id);
        return warehouseXProducts;
    }


    @Override
    public WarehouseXProduct createWarehouseXProduct(int productId, WarehouseXProduct warehouseXProduct) {
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate.getDataSource())
                .withTableName("WarehouseXProduct")
                .usingGeneratedKeyColumns("id");
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("idWarehouse", warehouseXProduct.getWarehouse().getId());
        parameters.put("idProduct", productId);
        parameters.put("stock", warehouseXProduct.getStock());
        int id = simpleJdbcInsert.executeAndReturnKey(parameters).intValue();
        warehouseXProduct.setId(id);
        return warehouseXProduct;
    }
}
