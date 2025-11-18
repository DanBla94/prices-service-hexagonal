package com.store.prices;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class PricesIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    private static final String PRODUCT_ID = "35455";
    private static final String BRAND_ID = "1";

    @Test
    @DisplayName("Test 1: Request at 10:00 on the 14th for product 35455 and brand 1")
    void test1() throws Exception {
        mockMvc.perform(get("/prices")
                .param("applicationDate", "2020-06-14T10:00:00Z")
                .param("productId", PRODUCT_ID)
                .param("brandId", BRAND_ID))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.priceList").value(1))
            .andExpect(jsonPath("$.price").value(35.50));
    }

    @Test
    @DisplayName("Test 2: Request at 16:00 on the 14th for product 35455 and brand 1")
    void test2() throws Exception {
        mockMvc.perform(get("/prices")
                .param("applicationDate", "2020-06-14T16:00:00Z")
                .param("productId", PRODUCT_ID)
                .param("brandId", BRAND_ID))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.priceList").value(2))
            .andExpect(jsonPath("$.price").value(25.45));
    }

    @Test
    @DisplayName("Test 3: Request at 21:00 on the 14th for product 35455 and brand 1")
    void test3() throws Exception {
        mockMvc.perform(get("/prices")
                .param("applicationDate", "2020-06-14T21:00:00Z")
                .param("productId", PRODUCT_ID)
                .param("brandId", BRAND_ID))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.priceList").value(1))
            .andExpect(jsonPath("$.price").value(35.50));
    }

    @Test
    @DisplayName("Test 4: Request at 10:00 on the 15th for product 35455 and brand 1")
    void test4() throws Exception {
        mockMvc.perform(get("/prices")
                .param("applicationDate", "2020-06-15T10:00:00Z")
                .param("productId", PRODUCT_ID)
                .param("brandId", BRAND_ID))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.priceList").value(3))
            .andExpect(jsonPath("$.price").value(30.50));
    }

    @Test
    @DisplayName("Test 5: Request at 21:00 on the 16th for product 35455 and brand 1")
    void test5() throws Exception {
        mockMvc.perform(get("/prices")
                .param("applicationDate", "2020-06-16T21:00:00Z")
                .param("productId", PRODUCT_ID)
                .param("brandId", BRAND_ID))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.priceList").value(4))
            .andExpect(jsonPath("$.price").value(38.95));
    }
}