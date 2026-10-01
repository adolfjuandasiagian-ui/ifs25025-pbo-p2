package usecase;

import domain.entity.SortOption;
import domain.entity.Transaction;
import domain.entity.TransactionType;
import domain.repository.ITransactionRepository;

import java.util.List;
import java.util.Locale;

public class FinanceUseCase {
    private final ITransactionRepository repository;

    public FinanceUseCase(ITransactionRepository repository) {
        this.repository = repository;
    }

    public Transaction addTransaction(String description, double amount, TransactionType type) {
        if (!Double.isFinite(amount) || amount <= 0) {
            throw new IllegalArgumentException("Amount must be finite and positive");
        }
        return repository.save(description, amount, type);
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

    public double getTotalIncome() {
        return repository.findAll().stream()
                .filter(t -> t.getType() == TransactionType.PEMASUKAN)
                .mapToDouble(Transaction::getAmount)
                .sum();
    }

    public double getTotalExpense() {
        return repository.findAll().stream()
                .filter(t -> t.getType() == TransactionType.PENGELUARAN)
                .mapToDouble(Transaction::getAmount)
                .sum();
    }

    public double getBalance() {
        return getTotalIncome() - getTotalExpense();
    }
}