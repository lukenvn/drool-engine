package com.luke.study.facts;

public class Product {
    private String name;
    private String type;
    private double discount;
    private double price;

    public String getName() {
        return name;
    }

    public Product setName(String name) {
        this.name = name;
        return this;
    }

    public String getType() {
        return type;
    }

    public Product setType(String type) {
        this.type = type;
        return this;
    }

    public double getDiscount() {
        return discount;
    }

    public Product setDiscount(double discount) {
        this.discount = discount;
        return this;
    }

    public double getPrice() {
        return price;
    }

    public Product setPrice(double price) {
        this.price = price;
        return this;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", discount=" + discount +
                ", price=" + price +
                '}';
    }
}
