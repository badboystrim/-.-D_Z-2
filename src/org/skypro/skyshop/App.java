package org.skypro.skyshop;

import org.skypro.skyshop.article.Article;
import org.skypro.skyshop.basket.ProductBasket;
import org.skypro.skyshop.product.Product;
import org.skypro.skyshop.product.SimpleProduct;
import org.skypro.skyshop.product.DiscountedProduct;
import org.skypro.skyshop.product.FixPriceProduct;
import org.skypro.skyshop.search.SearchEngine;
import org.skypro.skyshop.search.Searchable;

import java.util.Arrays;

public class App {
    public static void main(String[] args) {
        String khaki = "\u001B[32m";
        String reset = "\u001B[0m";

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
        System.out.println(khaki + "------------------------------------------------" + reset + "\n");

        System.out.println(khaki + "---------- [ ЗАДАЧА 11 (Поиск) ] ----------" + reset);

        SearchEngine searchEngine = new SearchEngine(10);

        searchEngine.add(apple);
        searchEngine.add(milk);
        searchEngine.add(bread);
        searchEngine.add(cheese);
        searchEngine.add(meat);
        searchEngine.add(juice);

        Article appleArticle = new Article("Польза яблок", "Яблоко содержит много витаминов и полезно для здоровья.");
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

        System.out.println("Тест 5 (через Arrays.toString): Поиск слова 'Хлеб'");
        System.out.println(Arrays.toString(searchEngine.search("Хлеб")));

        System.out.println(khaki + "-------------------------------------------" + reset);
    }

    private static void printSearchResults(Searchable[] results) {
        boolean found = false;
        for (Searchable result : results) {
            if (result != null) {
                System.out.println("-> Найдено: " + result.getStringRepresentation());
                found = true;
            }
        }
        if (!found) {
            System.out.println("-> Ничего не найдено.");
        }
        System.out.println();
    }
}
