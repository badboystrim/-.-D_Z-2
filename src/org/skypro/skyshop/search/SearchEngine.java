package org.skypro.skyshop.search;

import java.util.Set;
import java.util.HashSet;
import java.util.TreeSet;
import java.util.Comparator;
import java.util.Objects;
import java.util.stream.Collectors;

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

        if (query == null || query.isBlank()) {
            return new TreeSet<>(searchComparator);
        }

        String lowerQuery = query.toLowerCase();

        return searchables.stream()
                .filter(Objects::nonNull)
                .filter(searchable -> searchable.getSearchTerm() != null)
                .filter(searchable -> searchable.getSearchTerm().toLowerCase().contains(lowerQuery))
                .collect(Collectors.toCollection(() -> new TreeSet<>(searchComparator)));
    }

    public Searchable searchBestMatch(String query) throws BestResultNotFoundException {
        if (query == null || query.isBlank()) {
            throw new BestResultNotFoundException(query);
        }

        return searchables.stream()
                .filter(Objects::nonNull)
                .filter(searchable -> searchable.getSearchTerm() != null)
                .max(Comparator.comparingInt(searchable -> countOccurrences(searchable.getSearchTerm(), query)))
                .filter(searchable -> countOccurrences(searchable.getSearchTerm(), query) > 0)
                .orElseThrow(() -> new BestResultNotFoundException(query));
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
