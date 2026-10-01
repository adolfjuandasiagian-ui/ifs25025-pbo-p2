package adapter.presenter;

import domain.entity.Transaction;
import domain.entity.TransactionType;
import java.math.BigDecimal;
import java.util.List;

public class FinancePresenter {

    public void showMenu() {
        System.out.println("Menu:");
        System.out.println("1. Tambah Pemasukan");
        System.out.println("2. Tambah Pengeluaran");
        System.out.println("3. Cari");
        System.out.println("4. Urutkan");
        System.out.println("5. Lihat Saldo");
        System.out.println("6. Hapus");
        System.out.println("x. Keluar");
    }

    public void showSortMenu() {
        System.out.println("1. Jumlah (Terkecil)");
        System.out.println("2. Jumlah (Terbesar)");
        System.out.println("3. Pemasukan Dulu");
        System.out.println("4. Pengeluaran Dulu");
        System.out.println("x. Batal");
    }

    public void showPrompt(String prompt) {
        System.out.println(prompt);
    }

    public void showBlankLine() {
        System.out.println();
    }

    public void showInvalidRequiredInput() {
        System.out.println("[!] Input tidak boleh kosong!");
    }

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
        if (transactions == null || transactions.isEmpty()) {
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

    public void showBalance(BigDecimal balance, String label) {
        System.out.println(label + ": Rp " + formatAmount(balance));
    }

    public void showBalance(double balance, String label) {
        showBalance(BigDecimal.valueOf(balance), label);
    }

    private String formatTransaction(Transaction transaction) {
        String type = transaction.getType() == TransactionType.PEMASUKAN ? "Pemasukan" : "Pengeluaran";
        return transaction.getId() + " | " + transaction.getDescription() + " | Rp "
                + formatAmount(transaction.getAmount()) + " | " + type;
    }

    private String formatAmount(BigDecimal amount) {
        return amount.stripTrailingZeros().toPlainString();
    }

    public void showMessage(String message) {
        System.out.println(message);
    }

    public void showError(String error) {
        System.out.println(error);
    }
}