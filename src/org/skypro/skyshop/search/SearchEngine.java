package org.skypro.skyshop.search;

public class SearchEngine {
    private final Searchable[] searchables;
    private int count = 0;

    public SearchEngine(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Размер поискового движка должен быть больше 0");
        }
        this.searchables = new Searchable[size];
    }

    public void add(Searchable searchable) {
        if (count < searchables.length) {
            searchables[count] = searchable;
            count++;
        } else {
            System.out.println("Невозможно добавить объект для поиска: массив заполнен");
        }
    }

    public Searchable[] search(String query) {
        Searchable[] results = new Searchable[5];
        int resultsCount = 0;

        for (int i = 0; i < count; i++) {
            if (resultsCount >= 5) {
                break;
            }
            if (searchables[i] != null && searchables[i].getSearchTerm().contains(query)) {
                results[resultsCount] = searchables[i];
                resultsCount++;
            }
        }
        return results;
    }
}
