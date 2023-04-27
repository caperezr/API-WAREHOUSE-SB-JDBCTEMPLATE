package com.cperez.trainingFinal.controller;

import com.cperez.trainingFinal.model.ProductType;
import com.cperez.trainingFinal.service.ProductTypeService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@WebMvcTest(ProductTypeController.class)
public class ProductTypeControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductTypeService productTypeService;

    @Test
    void getProductTypesTest() throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        ProductType productType1 = new ProductType(1,"Producto tipo 1");
        ProductType productType2 = new ProductType(2,"Producto tipo 2");
        List<ProductType> expectedProductTypes = new ArrayList<>();
        expectedProductTypes.add(productType1);
        expectedProductTypes.add(productType2);
        int expectedStatus = 200;
        String uri = "/v1/productTypes";

        when(productTypeService.getProductTypes()).thenReturn(expectedProductTypes);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri)).andReturn();
        int actualStatus = mvcResult.getResponse().getStatus();
        String content = mvcResult.getResponse().getContentAsString();
        List<ProductType> actualProductTypes = mapper.readValue(content, new TypeReference<List<ProductType>>() {});
        assertEquals(expectedStatus, actualStatus);
        assertEquals(expectedProductTypes.size(), actualProductTypes.size());
        assertEquals(expectedProductTypes, actualProductTypes);
    }

    @Test
    void getProductTypesErrorTest() throws Exception{
        int expectedStatus = 500;
        String uri = "/v1/productTypes";
        when(productTypeService.getProductTypes()).thenThrow(new NullPointerException("Error"));
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri)).andReturn();
        int actualStatus = mvcResult.getResponse().getStatus();
        assertEquals(expectedStatus, actualStatus);
    }


}
