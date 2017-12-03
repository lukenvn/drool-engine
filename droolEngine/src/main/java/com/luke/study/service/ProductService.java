package com.luke.study.service;

import com.luke.study.facts.Product;
import org.kie.api.runtime.KieSession;

public class ProductService {


    private KieSession kieSession;

    public void setKieSession(KieSession kieSession) {
        this.kieSession = kieSession;
    }

    public void calculatePrice(Product product){
        kieSession.insert(product);
        int rules = kieSession.fireAllRules();

//        product.setPrice(product.getPrice()*product.getDiscount());
        System.out.println(product);

        System.out.println("running rules: "+rules);
    }

}
