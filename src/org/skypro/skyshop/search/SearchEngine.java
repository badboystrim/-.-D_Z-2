package org.skypro.skyshop.search;

import java.util.LinkedList;
import java.util.List;

public class SearchEngine {
    private final List<Searchable> searchables = new LinkedList<>();

    public SearchEngine(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Размер поискового движка должен быть больше 0");
        }
    }

    public void add(Searchable searchable) {
        searchables.add(searchable);
    }

    public List<Searchable> search(String query) {
        List<Searchable> results = new LinkedList<>();
        for (Searchable searchable : searchables) {
            if (searchable != null && searchable.getSearchTerm().contains(query)) {
                results.add(searchable);
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

        for (Searchable searchable : searchables) {
            if (searchable != null) {
                int currentCount = countOccurrences(searchable.getSearchTerm(), query);
                if (currentCount > maxCount) {
                    maxCount = currentCount;
                    bestMatch = searchable;
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
