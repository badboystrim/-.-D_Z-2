package org.skypro.skyshop.search;

import java.util.Set;
import java.util.HashSet;
import java.util.TreeSet;
import java.util.Comparator;

public class SearchEngine {
    private final Set<Searchable> searchables = new HashSet<>();

    public SearchEngine(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Размер поискового движка должен быть больше 0");
        }
    }

    public void add(Searchable searchable) {
        if (searchable != null) {
            searchables.add(searchable);
        }
    }

    public Set<Searchable> search(String query) {
        Comparator<Searchable> searchComparator = (s1, s2) -> {
            int lengthCompare = Integer.compare(s2.getName().length(), s1.getName().length());
            if (lengthCompare == 0) {
                return s1.getName().compareTo(s2.getName());
            }
            return lengthCompare;
        };

        Set<Searchable> results = new TreeSet<>(searchComparator);

        if (query == null || query.isBlank()) {
            return results;
        }

        String lowerQuery = query.toLowerCase();

        for (Searchable searchable : searchables) {
            if (searchable != null && searchable.getSearchTerm() != null) {
                if (searchable.getSearchTerm().toLowerCase().contains(lowerQuery)) {
                    results.add(searchable);
                }
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
            if (searchable != null && searchable.getSearchTerm() != null) {
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

        String lowerText = text.toLowerCase();
        String lowerSubstring = substring.toLowerCase();

        int countOccurrences = 0;
        int index = 0;
        int substringIndex = lowerText.indexOf(lowerSubstring, index);

        while (substringIndex != -1) {
            countOccurrences++;
            index = substringIndex + lowerSubstring.length();
            substringIndex = lowerText.indexOf(lowerSubstring, index);
        }
        return countOccurrences;
    }
}
