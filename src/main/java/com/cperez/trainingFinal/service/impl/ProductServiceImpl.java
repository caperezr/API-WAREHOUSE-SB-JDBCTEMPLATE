package com.cperez.trainingFinal.service.impl;

import com.cperez.trainingFinal.dto.ProductDTO;
import com.cperez.trainingFinal.dto.WarehouseXProductDTO;
import com.cperez.trainingFinal.model.Product;
import com.cperez.trainingFinal.model.WarehouseXProduct;
import com.cperez.trainingFinal.repository.*;
import com.cperez.trainingFinal.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductTypeRepository productTypeRepository;

    @Autowired
    private WarehouseXProductRepository warehouseXProductRepository;

    @Autowired
    private WarehouseRepository warehouseRepository;

    @Autowired
    private WarehouseTypeRepository warehouseTypeRepository;



    @Override
    public List<Product> getProducts() {
        List<Product> products = productRepository.getProducts();
        for (Product product : products) {
            List<WarehouseXProduct> warehouseXProducts = warehouseXProductRepository.getWarehouseXProductsByProductId(product.getId());
            for (WarehouseXProduct warehouseXProduct : warehouseXProducts) {
                warehouseXProduct.setWarehouse(warehouseRepository.getWarehouseById(warehouseXProduct.getWarehouse().getId()));
                warehouseXProduct.getWarehouse().setWarehouseType(warehouseTypeRepository.getWarehouseTypeById(warehouseXProduct.getWarehouse().getWarehouseType().getId()));
            }
            product.setProductType(productTypeRepository.getProductTypeByid(product.getProductType().getId()));
            product.setWarehouseXProducts(warehouseXProducts);
        }
        return products;
    }

    @Override
    public Product getProductByid(int id) {
        Product product = productRepository.getProductByid(id);
        product.setProductType(productTypeRepository.getProductTypeByid(product.getProductType().getId()));
        List<WarehouseXProduct> warehouseXProducts = warehouseXProductRepository.getWarehouseXProductsByProductId(product.getId());
        for (WarehouseXProduct warehouseXProduct : warehouseXProducts) {
            warehouseXProduct.setWarehouse(warehouseRepository.getWarehouseById(warehouseXProduct.getWarehouse().getId()));
            warehouseXProduct.getWarehouse().setWarehouseType(warehouseTypeRepository.getWarehouseTypeById(warehouseXProduct.getWarehouse().getWarehouseType().getId()));
        }
        product.setWarehouseXProducts(warehouseXProducts);
        return product;
    }

    @Override
    public Product createProduct(ProductDTO productDTO) {
        Product product = new Product(productDTO);
        product.setProductType(productTypeRepository.getProductTypeByid(product.getProductType().getId()));
        product = productRepository.createProduct(product);

        ArrayList<WarehouseXProductDTO> warehouseDTOS = productDTO.getWarehousesDTO();

        for ( WarehouseXProductDTO warehouseDTO : warehouseDTOS) {
            WarehouseXProduct warehouseXProduct = new WarehouseXProduct(warehouseDTO);
            warehouseXProductRepository.createWarehouseXProduct(product.getId(), warehouseXProduct);
        }

        List<WarehouseXProduct> warehouseXProducts = warehouseXProductRepository.getWarehouseXProductsByProductId(product.getId());

        for (WarehouseXProduct warehouseXProduct : warehouseXProducts) {
            warehouseXProduct.setWarehouse(warehouseRepository.getWarehouseById(warehouseXProduct.getWarehouse().getId()));
            warehouseXProduct.getWarehouse().setWarehouseType(warehouseTypeRepository.getWarehouseTypeById(warehouseXProduct.getWarehouse().getWarehouseType().getId()));
        }

        product.setWarehouseXProducts(warehouseXProducts);

        return product;
    }
}
