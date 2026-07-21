package org.skypro.skyshop;

import org.skypro.skyshop.article.Article;
import org.skypro.skyshop.basket.ProductBasket;
import org.skypro.skyshop.product.Product;
import org.skypro.skyshop.product.SimpleProduct;
import org.skypro.skyshop.product.DiscountedProduct;
import org.skypro.skyshop.product.FixPriceProduct;
import org.skypro.skyshop.search.SearchEngine;
import org.skypro.skyshop.search.Searchable;
import org.skypro.skyshop.search.BestResultNotFoundException;

import java.util.List;

public class App {
    public static void main(String[] args) {
        String khaki = "\u001B[32m";
        String reset = "\u001B[0m";

        System.out.println(khaki + "---------- [ ТЕСТ ВАЛИДАЦИИ ДАННЫХ ] ----------" + reset);
        try {
            Product badName = new SimpleProduct("   ", 100);
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка (Имя): " + e.getMessage());
        }

        try {
            Product badPrice = new SimpleProduct("Лимонад", -50);
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка (Цена): " + e.getMessage());
        }

        try {
            Product badDiscount = new DiscountedProduct("Кофе", 300, 150);
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка (Скидка): " + e.getMessage());
        }
        System.out.println();

        Product apple = new SimpleProduct("Яблоко", 160);
        Product milk = new DiscountedProduct("Молоко", 125, 20);
        Product bread = new SimpleProduct("Хлеб", 90);
        Product cheese = new FixPriceProduct("Сыр");
        Product meat = new SimpleProduct("Мясо", 950);
        Product juice = new DiscountedProduct("Сок", 220, 10);

        ProductBasket basket = new ProductBasket();

        System.out.println(khaki + "---------- [ ЗАДАЧА 1-10 (Корзина) ] ----------" + reset);
        basket.addProduct(apple);
        basket.addProduct(milk);
        basket.addProduct(bread);
        basket.addProduct(cheese);
        basket.addProduct(meat);
        basket.addProduct(juice);

        System.out.println("\nСодержимое корзины:");
        basket.printBasket();
        System.out.println();

        System.out.println(khaki + "---------- [ ТЕСТ УДАЛЕНИЯ ИЗ КОРЗИНЫ ] ----------" + reset);
        System.out.println("1. Удаляем существующий продукт 'Хлеб':");
        List<Product> removedItems1 = basket.removeProductByName("Хлеб");
        System.out.println("Удаленные продукты:");
        for (Product product : removedItems1) {
            System.out.println("-> " + product);
        }
        System.out.println();

        System.out.println("Содержимое корзины после удаления:");
        basket.printBasket();
        System.out.println();

        System.out.println("2. Удаляем несуществующий продукт 'Рыба':");
        List<Product> removedItems2 = basket.removeProductByName("Рыба");
        if (removedItems2.isEmpty()) {
            System.out.println("Список пуст");
        } else {
            for (Product product : removedItems2) {
                System.out.println("-> " + product);
            }
        }
        System.out.println();

        System.out.println("Итоговое содержимое корзины:");
        basket.printBasket();
        System.out.println(khaki + "------------------------------------------------" + reset + "\n");

        System.out.println(khaki + "---------- [ ЗАДАЧА 11 (Поиск) ] ----------" + reset);

        SearchEngine searchEngine = new SearchEngine(10);

        searchEngine.add(apple);
        searchEngine.add(milk);
        searchEngine.add(bread);
        searchEngine.add(cheese);
        searchEngine.add(meat);
        searchEngine.add(juice);

        Article appleArticle = new Article("Польза яблок", "Яблоко содержит много витаминов и полезно для здоровья. Яблоко - суперфуд.");
        Article milkArticle = new Article("Свежее молоко", "Молоко — отличный источник кальция для крепких костей.");
        Article healthyDiet = new Article("Здоровое питание", "Включите в рацион свежий сок и мясо.");

        searchEngine.add(appleArticle);
        searchEngine.add(milkArticle);
        searchEngine.add(healthyDiet);

        System.out.println("Тест 1: Поиск слова 'Яблоко'");
        printSearchResults(searchEngine.search("Яблоко"));

        System.out.println("Тест 2: Поиск слова 'Молоко'");
        printSearchResults(searchEngine.search("Молоко"));

        System.out.println("Тест 3: Поиск слова 'сок'");
        printSearchResults(searchEngine.search("сок"));

        System.out.println("Тест 4: Поиск несуществующего слова 'Рыба'");
        printSearchResults(searchEngine.search("Рыба"));

        System.out.println("Тест 5 (печать списка напрямую): Поиск слова 'Хлеб'");
        System.out.println(searchEngine.search("Хлеб"));
        System.out.println();

        System.out.println(khaki + "---------- [ ЗАДАЧА 12 (Поиск лучшего совпадения) ] ----------" + reset);
        try {
            System.out.println("Тест 1: Поиск лучшего совпадения для 'Яблоко'");
            Searchable bestMatch = searchEngine.searchBestMatch("Яблоко");
            System.out.println("-> Лучшее совпадение: " + bestMatch.getStringRepresentation());
        } catch (BestResultNotFoundException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
        System.out.println();

        try {
            System.out.println("Тест 2: Поиск лучшего совпадения для 'Рыба'");
            Searchable bestMatch = searchEngine.searchBestMatch("Рыба");
            System.out.println("-> Лучшее совпадение: " + bestMatch.getStringRepresentation());
        } catch (BestResultNotFoundException e) {
            System.out.println("-> Перехвачено исключение: " + e.getMessage());
        }
        System.out.println(khaki + "---------------------------------------------------------------" + reset);
    }

    private static void printSearchResults(List<Searchable> results) {
        if (results == null || results.isEmpty()) {
            System.out.println("-> Ничего не найдено.\n");
            return;
        }
        for (Searchable result : results) {
            if (result != null) {
                System.out.println("-> Найдено: " + result.getStringRepresentation());
            }
        }
        System.out.println();
    }
}
