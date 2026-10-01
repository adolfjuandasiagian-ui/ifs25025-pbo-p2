package domain.entity;

import java.util.Comparator;

/** Domain-owned comparators keep sorting rules out of the view and use case. */
public enum SortOption {
    AMOUNT_ASC(Comparator.comparingDouble(Transaction::getAmount)),
    AMOUNT_DESC(Comparator.comparingDouble(Transaction::getAmount).reversed()),
    INCOME_FIRST(Comparator.comparing((Transaction transaction) -> transaction.getType() != TransactionType.PEMASUKAN)),
    EXPENSE_FIRST(Comparator.comparing((Transaction transaction) -> transaction.getType() != TransactionType.PENGELUARAN));

    private final Comparator<Transaction> comparator;

    SortOption(Comparator<Transaction> comparator) {
        this.comparator = comparator;
    }

    public Comparator<Transaction> comparator() {
        return comparator;
    }
}