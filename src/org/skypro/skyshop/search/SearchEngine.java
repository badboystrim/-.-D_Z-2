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

    public Searchable searchBestMatch(String query) throws BestResultNotFoundException {
        if (query == null || query.isBlank()) {
            throw new BestResultNotFoundException(query);
        }

        Searchable bestMatch = null;
        int maxCount = 0;

        for (int i = 0; i < count; i++) {
            if (searchables[i] != null) {
                int currentCount = countOccurrences(searchables[i].getSearchTerm(), query);
                if (currentCount > maxCount) {
                    maxCount = currentCount;
                    bestMatch = searchables[i];
                }
            }
        }

        if (bestMatch == null) {
            throw new BestResultNotFoundException(query);
        }

        return bestMatch;
    }

    private int countOccurrences(String text, String substring) {
        if (text == null || substring == null || substring.isEmpty()) {
            return 0;
        }

        int countOccurrences = 0;
        int index = 0;
        int substringIndex = text.indexOf(substring, index);

        while (substringIndex != -1) {
            countOccurrences++;
            index = substringIndex + substring.length();
            substringIndex = text.indexOf(substring, index);
        }
        return countOccurrences;
    }
}
