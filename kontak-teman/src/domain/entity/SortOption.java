package domain.entity;

import java.util.Comparator;

/** Domain-owned comparators keep sorting rules out of the view and use case. */
public enum SortOption {
    NAME_ASC(Comparator.comparing(Contact::getName, String.CASE_INSENSITIVE_ORDER)),
    NAME_DESC(Comparator.comparing(Contact::getName, String.CASE_INSENSITIVE_ORDER).reversed());

    private final Comparator<Contact> comparator;

    SortOption(Comparator<Contact> comparator) {
        this.comparator = comparator;
    }

    public Comparator<Contact> comparator() {
        return comparator;
    }
}
