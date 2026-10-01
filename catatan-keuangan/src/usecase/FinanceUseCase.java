package usecase;

import domain.entity.SortOption;
import domain.entity.Transaction;
import domain.entity.TransactionType;
import domain.repository.ITransactionRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Locale;

public class FinanceUseCase {
    private final ITransactionRepository repository;

    public FinanceUseCase(ITransactionRepository repository) {
        this.repository = repository;
    }

    public Transaction addTransaction(String description, BigDecimal amount, TransactionType type) {
        if (amount == null || amount.signum() <= 0) {
            throw new IllegalArgumentException("Amount must be finite and positive");
        }
        return repository.save(description, amount, type);
    }

    public Transaction addTransaction(String description, double amount, TransactionType type) {
        if (!Double.isFinite(amount)) {
            throw new IllegalArgumentException("Amount must be finite and positive");
        }
        return addTransaction(description, BigDecimal.valueOf(amount), type);
    }

    public List<Transaction> getAllTransactions() {
        return repository.findAll();
    }

    public boolean deleteTransaction(int id) {
        return repository.deleteById(id);
    }

    public List<Transaction> searchTransactions(String query) {
        String lowerQuery = query.toLowerCase(Locale.ROOT);
        return repository.findAll().stream()
                .filter(transaction -> transaction.getDescription().toLowerCase(Locale.ROOT).contains(lowerQuery))
                .toList();
    }

    public List<Transaction> getSortedTransactions(SortOption sortOption) {
        return repository.findAll().stream()
                .sorted(sortOption.comparator())
                .toList();
    }

    public BigDecimal getTotalIncome() {
        return repository.findAll().stream()
                .filter(t -> t.getType() == TransactionType.PEMASUKAN)
                .map(Transaction::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal getTotalExpense() {
        return repository.findAll().stream()
                .filter(t -> t.getType() == TransactionType.PENGELUARAN)
                .map(Transaction::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal getBalance() {
        return getTotalIncome().subtract(getTotalExpense());
    }
}