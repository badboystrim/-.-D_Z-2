package org.skypro.skyshop;

import org.skypro.skyshop.basket.ProductBasket;
import org.skypro.skyshop.product.Product;

public class App {
    public static void main(String[] args) {
        String khaki = "\u001B[32m";
        String reset = "\u001B[0m";

        Product apple = new Product("Яблоко", 100);
        Product milk = new Product("Молоко", 80);
        Product bread = new Product("Хлеб", 80-);
        Product cheese = new Product("Сыр", 300);
        Product meat = new Product("Мясо", 500);
        Product juice = new Product("Сок", 180);

        ProductBasket basket = new ProductBasket();

        System.out.println(khaki + "---------- [ ЗАДАЧА 1 ] ----------" + reset);
        basket.addProduct(apple);
        basket.addProduct(milk);
        basket.addProduct(bread);
        basket.addProduct(cheese);
        basket.addProduct(meat);
        System.out.println(khaki + "----------------------------------" + reset + "\n");

        System.out.println(khaki + "---------- [ ЗАДАЧА 2 ] ----------" + reset);
        basket.addProduct(juice);
        System.out.println(khaki + "----------------------------------" + reset + "\n");

        System.out.println(khaki + "---------- [ ЗАДАЧА 3 ] ----------" + reset);
        basket.printBasket();
        System.out.println(khaki + "----------------------------------" + reset + "\n");

        System.out.println(khaki + "---------- [ ЗАДАЧA 4 ] ----------" + reset);
        System.out.println("Общая стоимость: " + basket.getTotalCost());
        System.out.println(khaki + "----------------------------------" + reset + "\n");

        System.out.println(khaki + "---------- [ ЗАДАЧА 5 ] ----------" + reset);
        System.out.println("Есть ли 'Молоко' в корзине? " + basket.hasProduct("Молоко"));
        System.out.println(khaki + "----------------------------------" + reset + "\n");

        System.out.println(khaki + "---------- [ ЗАДАЧА 6 ] ----------" + reset);
        System.out.println("Есть ли 'Сок' в корзине? " + basket.hasProduct("Сок"));
        System.out.println(khaki + "----------------------------------" + reset + "\n");

        System.out.println(khaki + "---------- [ ЗАДАЧА 7 ] ----------" + reset);
        basket.clearBasket();
        System.out.println("Корзина успешно очищена.");
        System.out.println(khaki + "----------------------------------" + reset + "\n");

        System.out.println(khaki + "---------- [ ЗАДАЧА 8 ] ----------" + reset);
        basket.printBasket();
        System.out.println(khaki + "----------------------------------" + reset + "\n");

        System.out.println(khaki + "---------- [ ЗАДАЧА 9 ] ----------" + reset);
        System.out.println("Стоимость пустой корзины: " + basket.getTotalCost());
        System.out.println(khaki + "----------------------------------" + reset + "\n");

        System.out.println(khaki + "---------- [ ЗАДАЧА 10 ] ---------" + reset);
        System.out.println("Есть ли 'Яблоко' в пустой корзине? " + basket.hasProduct("Яблоко"));
        System.out.println(khaki + "----------------------------------" + reset);
    }
}
