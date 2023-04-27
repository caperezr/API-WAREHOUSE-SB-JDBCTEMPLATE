package com.cperez.trainingFinal.repository.impl;

import com.cperez.trainingFinal.model.WarehouseType;
import com.cperez.trainingFinal.repository.WarehouseTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class WarehouseTypeRepositoryImpl implements WarehouseTypeRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;



    public WarehouseType mapToWarehouseType(ResultSet rs) throws SQLException {
        return new WarehouseType(
                rs.getInt("id"),
                rs.getString("name"));
    }

    @Override
    public List<WarehouseType> getWarehouseTypes() {
        String sql = "CALL ListAllWarehouseTypes();";
        List<WarehouseType> warehouseTypes = jdbcTemplate.query(sql,
                (rs, rowNum) -> mapToWarehouseType(rs));
        return warehouseTypes;
    }

    @Override
    public WarehouseType getWarehouseTypeById(int id) {
        String sql = "CALL GetWarehouseTypeById(?);";
        WarehouseType warehouseType = jdbcTemplate.queryForObject(sql,
                (rs, rowNum) -> mapToWarehouseType(rs), id);
        return warehouseType;
    }

    @Override
    public WarehouseType createWarehouseType(WarehouseType warehouseType) {
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate.getDataSource())
                .withTableName("WarehouseType")
                .usingGeneratedKeyColumns("id");
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", warehouseType.getName());
        int id = simpleJdbcInsert.executeAndReturnKey(parameters).intValue();
        warehouseType.setId(id);
        return warehouseType;
    }
}
