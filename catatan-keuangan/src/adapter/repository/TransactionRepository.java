package adapter.repository;

import domain.entity.Transaction;
import domain.entity.TransactionType;
import domain.repository.ITransactionRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Optional;
import java.util.List;

public class TransactionRepository implements ITransactionRepository {
    private final List<Transaction> transactions = new ArrayList<>();
    private int idCounter = 0;

    @Override
    public Transaction save(String description, BigDecimal amount, TransactionType type) {
        Transaction transaction = new Transaction(++idCounter, description, amount, type);
        transactions.add(transaction);
        return transaction;
    }

    @Override
    public List<Transaction> findAll() {
        return new ArrayList<>(transactions);
    }

    @Override
    public Optional<Transaction> findById(int id) {
        return transactions.stream()
                .filter(t -> t.getId() == id)
                .findFirst()
                .map(transaction -> new Transaction(transaction.getId(), transaction.getDescription(), transaction.getAmount(), transaction.getType()));
    }

    @Override
    public boolean deleteById(int id) {
        return transactions.removeIf(t -> t.getId() == id);
    }
}