package adapter.presenter;

import domain.entity.Transaction;
import domain.entity.TransactionType;
import java.util.List;

public class FinancePresenter {

    public void showTransactions(List<Transaction> transactions) {
        showTransactionList(transactions, "Daftar Transaksi:", "- Belum ada transaksi!");
    }

    public void showSearchResults(String query, List<Transaction> transactions) {
        showTransactionList(transactions, "Hasil Pencarian: \"" + query + "\"", "- Transaksi tidak ditemukan!");
    }

    public void showSortedTransactions(List<Transaction> transactions) {
        showTransactionList(transactions, "Daftar Transaksi (Terurut):", "- Belum ada transaksi!");
    }

    private void showTransactionList(List<Transaction> transactions, String header, String emptyMessage) {
        System.out.println(header);
        if (transactions.isEmpty()) {
            System.out.println(emptyMessage);
            return;
        }
        for (Transaction transaction : transactions) {
            showSingleTransaction(transaction);
        }
    }

    public void showSingleTransaction(Transaction transaction) {
        System.out.println(formatTransaction(transaction));
    }

    public void showAddSuccess(Transaction transaction) {
        System.out.println("Berhasil menambah transaksi: " + formatTransaction(transaction));
    }

    public void showBalance(double balance, String label) {
        System.out.println(label + ": Rp " + (long) balance);
    }

    private String formatTransaction(Transaction transaction) {
        String type = transaction.getType() == TransactionType.PEMASUKAN ? "Pemasukan" : "Pengeluaran";
        return transaction.getId() + " | " + transaction.getDescription() + " | Rp "
                + (long) transaction.getAmount() + " | " + type;
    }

    public void showMessage(String message) {
        System.out.println(message);
    }

    public void showError(String error) {
        System.out.println(error);
    }
}