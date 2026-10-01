package domain.entity;

import java.math.BigDecimal;
import java.util.Objects;

public class Transaction {
    private final int id;
    private String description;
    private BigDecimal amount;
    private TransactionType type;

    public Transaction(int id, String description, BigDecimal amount, TransactionType type) {
        this.id = id;
        this.description = description;
        this.amount = Objects.requireNonNull(amount);
        this.type = type;
    }

    public Transaction(int id, String description, double amount, TransactionType type) {
        this(id, description, BigDecimal.valueOf(amount), type);
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = Objects.requireNonNull(amount);
    }

    public void setAmount(double amount) {
        setAmount(BigDecimal.valueOf(amount));
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }
}