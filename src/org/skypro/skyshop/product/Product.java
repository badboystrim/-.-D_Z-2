package org.skypro.skyshop.product;

public class Product {
    private final String title;
    private final int price;

    public Product(String title, int price) {
        this.title = title;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public int getPrice() {
        return price;
    }
}
