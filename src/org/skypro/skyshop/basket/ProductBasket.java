package org.skypro.skyshop.basket;

import org.skypro.skyshop.product.Product;

public class ProductBasket {
    private final Product[] products = new Product[5];
    private int count = 0;
    public void addProduct(Product product) {
        if (count < products.length) {
            products[count] = product;
            count++;
        } else {
            System.out.println("Невозможно добавить продукт");
        }
    }

    public int getTotalCost() {
        int totalCost = 0;
        for (int i = 0; i < count; i++) {
            totalCost += products[i].getPrice();
        }
        return totalCost;
    }

    public void printBasket() {
        if (count == 0) {
            System.out.println("в корзине пусто");
            return;
        }

        int specialCount = 0;

        for (int i = 0; i < count; i++) {
            System.out.println(products[i].toString());
            if (products[i].isSpecial()) {
                specialCount++;
            }
        }

        System.out.println("Итого: " + getTotalCost());
        System.out.println("Специальных товаров: " + specialCount);
    }


    public boolean hasProduct(String title) {
        for (int i = 0; i < count; i++) {
            if (products[i].getName().equals(title)) {
                return true;
            }
        }
        return false;
    }

    public void clearBasket() {
        for (int i = 0; i < count; i++) {
            products[i] = null;
        }
        count = 0;
    }
}
