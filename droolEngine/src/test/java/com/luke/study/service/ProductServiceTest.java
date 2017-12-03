package com.luke.study.service;

import com.luke.study.config.DroolFactory;
import com.luke.study.constant.Constant;
import com.luke.study.facts.Product;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.*;

public class ProductServiceTest {
    private ProductService productService;

    @Before
    public void setUp() throws Exception {
        productService = new ProductService();
        productService.setKieSession(new DroolFactory().getKieSession());
    }

    @Test
    public void calculatePrice_should_apply_discount() throws Exception {

        double oldPrice = 200000;
        Product product = new Product()
                .setName(Constant.P_DELL)
                .setType(Constant.LAPTOP)
                .setPrice(oldPrice);

        productService.calculatePrice(product);
        System.out.println(product.getPrice());

        assertThat(product.getPrice(),not(oldPrice));



    }

}