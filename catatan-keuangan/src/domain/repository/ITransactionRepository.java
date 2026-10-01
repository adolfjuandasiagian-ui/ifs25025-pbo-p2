package domain.repository;

import domain.entity.Transaction;
import domain.entity.TransactionType;
import java.util.List;
import java.util.Optional;

public interface ITransactionRepository {
    Transaction save(String description, double amount, TransactionType type);
    List<Transaction> findAll();
    Optional<Transaction> findById(int id);
    boolean deleteById(int id);
}