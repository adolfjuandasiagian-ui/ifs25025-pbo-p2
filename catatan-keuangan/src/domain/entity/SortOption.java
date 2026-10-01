package domain.entity;

import java.util.Comparator;

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