package com.cperez.trainingFinal.repository.impl;

import com.cperez.trainingFinal.model.Warehouse;
import com.cperez.trainingFinal.model.WarehouseType;
import com.cperez.trainingFinal.repository.WarehouseRepository;
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
public class WarehouseRepositoryImpl implements WarehouseRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;



    public Warehouse mapToWarehouse(ResultSet rs) throws SQLException {
        Warehouse warehouse = new Warehouse();
        warehouse.setId(rs.getInt("id"));
        warehouse.setName(rs.getString("name"));
        WarehouseType warehouseType = new WarehouseType();
        warehouseType.setId(rs.getInt("idWarehouseType"));
        warehouse.setWarehouseType(warehouseType);
        return warehouse;
    }

    @Override
    public List<Warehouse> getWarehouses() {
        String sql = "CALL ListAllWarehouses();";
        List<Warehouse> warehouses = jdbcTemplate.query(sql, (rs, rowNum) -> mapToWarehouse(rs));
        return warehouses;
    }

    @Override
    public Warehouse getWarehouseById(int id) {
        String sql = "CALL GetWarehouseById(?);";
        Warehouse warehouse = jdbcTemplate.queryForObject(sql, (rs, rowNum) -> mapToWarehouse(rs), id);
        return warehouse;
    }

    @Override
    public Warehouse createWarehouse(Warehouse warehouse) {
        SimpleJdbcInsert simpleJdbcInsert =
                new SimpleJdbcInsert(jdbcTemplate.getDataSource())
                        .withTableName("Warehouse")
                        .usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("idWarehouseType", warehouse.getWarehouseType().getId());
        System.out.println("nombre"+warehouse.getName());
        parameters.put("name", warehouse.getName());
        int id = simpleJdbcInsert.executeAndReturnKey(parameters).intValue();
        warehouse.setId(id);
        return warehouse;
    }
}
