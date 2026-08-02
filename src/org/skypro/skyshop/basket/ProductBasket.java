package org.skypro.skyshop.basket;

import org.skypro.skyshop.product.Product;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class ProductBasket {
    private final Map<String, List<Product>> products = new HashMap<>();

    public void addProduct(Product product) {
        if (product == null) return;
        products.computeIfAbsent(product.getName(), k -> new ArrayList<>()).add(product);
    }

    public int getTotalCost() {
        int totalCost = 0;
        for (List<Product> productList : products.values()) {
            for (Product product : productList) {
                totalCost += product.getPrice();
            }
        }
        return totalCost;
    }

    public void printBasket() {
        if (products.isEmpty()) {
            System.out.println("в корзине пусто");
            return;
        }

        for (List<Product> productList : products.values()) {
            for (Product product : productList) {
                System.out.println(product.toString());
            }
        }

        System.out.println("Итого: " + getTotalCost());
        System.out.println("Специальных товаров: " + getSpecialCount());
    }

    private int getSpecialCount() {
        int specialCount = 0;
        for (List<Product> productList : products.values()) {
            for (Product product : productList) {
                if (product.isSpecial()) {
                    specialCount++;
                }
            }
        }
        return specialCount;
    }

    public boolean hasProduct(String title) {
        return products.containsKey(title);
    }

    public void clearBasket() {
        products.clear();
    }

    public List<Product> removeProductByName(String name) {
        List<Product> removedProducts = products.remove(name);
        return (removedProducts == null) ? Collections.emptyList() : removedProducts;
    }
}
