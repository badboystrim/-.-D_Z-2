package org.skypro.skyshop.product;

public abstract class Product {
    private final String title;

    public Product(String title) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Название товара не может быть пустым");
        }
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public abstract int getPrice();

    public boolean isSpecial() {
        return false;
    }
}
