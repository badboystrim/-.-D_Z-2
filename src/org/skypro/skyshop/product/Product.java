package org.skypro.skyshop.product;

import org.skypro.skyshop.search.Searchable;


public abstract class Product implements Searchable {
    private final String name;

    public Product(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Название товара не может быть пустым");
        }
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getSearchTerm() {
        return name;
    }

    @Override
    public String getContentType() {
        return "PRODUCT";
    }

    public abstract int getPrice();

    public abstract boolean isSpecial();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return java.util.Objects.equals(name, product.name);

    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(name);
    }
}
