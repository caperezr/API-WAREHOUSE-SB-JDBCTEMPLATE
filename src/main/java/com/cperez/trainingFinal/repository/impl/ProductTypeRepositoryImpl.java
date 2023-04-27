package com.cperez.trainingFinal.repository.impl;

import com.cperez.trainingFinal.model.ProductType;
import com.cperez.trainingFinal.repository.ProductTypeRepository;
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
public class ProductTypeRepositoryImpl implements ProductTypeRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;



    public ProductType mapToProductType(ResultSet rs) throws SQLException {
        return new ProductType(
                rs.getInt("id"),
                rs.getString("name"));
    }
    @Override
    public List<ProductType> getProductTypes() {
        String sql = "CALL ListAllProductTypes();";
        List<ProductType> productTypes = jdbcTemplate.query(sql,
                (rs, rowNum) -> mapToProductType(rs));
        return productTypes;
    }

    @Override
    public ProductType getProductTypeByid(int id) {
        String sql = "CALL GetProductTypeById(?);";
        ProductType productType = jdbcTemplate.queryForObject(sql,
                (rs, rowNum) -> mapToProductType(rs), id);
        return productType;
    }

    @Override
    public ProductType createProductType(ProductType productType) {
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate.getDataSource())
                .withTableName("ProductType")
                .usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", productType.getName());
        int id = simpleJdbcInsert.executeAndReturnKey(parameters).intValue();
        productType.setId(id);
        return productType;
    }
}
