package framework.view;

import adapter.presenter.FinancePresenter;
import domain.entity.SortOption;
import domain.entity.TransactionType;
import framework.util.InputUtil;
import usecase.FinanceUseCase;

public class FinanceView {
    private final FinanceUseCase useCase;
    private final FinancePresenter presenter;

    public FinanceView(FinanceUseCase useCase, FinancePresenter presenter) {
        this.useCase = useCase;
        this.presenter = presenter;
    }

    public void show() {
        while (true) {
            presenter.showTransactions(useCase.getAllTransactions());
            presenter.showBalance(useCase.getBalance(), "Saldo");

            presenter.showMenu();

            String input = InputUtil.input("Pilih");
            if (input.equalsIgnoreCase("x")) {
                break;
            }

            switch (input) {
                case "1":
                    addTransaction(TransactionType.PEMASUKAN);
                    break;
                case "2":
                    addTransaction(TransactionType.PENGELUARAN);
                    break;
                case "3":
                    searchTransaction();
                    break;
                case "4":
                    sortTransactions();
                    break;
                case "5":
                    presenter.showBalance(useCase.getBalance(), "Saldo saat ini");
                    break;
                case "6":
                    deleteTransaction();
                    break;
                default:
                    presenter.showError("[!] Pilihan tidak dimengerti.");
                    break;
            }

            presenter.showBlankLine();
        }
    }

    private void addTransaction(TransactionType type) {
        if (type == TransactionType.PEMASUKAN) {
            presenter.showPrompt("[Tambah Pemasukan]");
        } else {
            presenter.showPrompt("[Tambah Pengeluaran]");
        }

        String desc = InputUtil.input("Keterangan (x Jika Batal)");
        if (desc.equalsIgnoreCase("x")) return;
        if (desc.isBlank()) {
            presenter.showInvalidRequiredInput();
            return;
        }

        String amountStr = InputUtil.input("Jumlah");
        if (amountStr.equalsIgnoreCase("x")) return;

        try {
            double amount = Double.parseDouble(amountStr);
            presenter.showAddSuccess(useCase.addTransaction(desc, amount, type));

        } catch (IllegalArgumentException e) {
            presenter.showError("[!] Jumlah tidak valid!");
        }
    }

    private void searchTransaction() {
        presenter.showPrompt("[Cari Transaksi]");
        String query = InputUtil.input("Kata Kunci (x Jika Batal)");
        if (query.equalsIgnoreCase("x")) return;
        presenter.showSearchResults(query, useCase.searchTransactions(query));
    }

    private void sortTransactions() {
        presenter.showPrompt("[Urutkan Transaksi]");
        presenter.showSortMenu();
        String opt = InputUtil.input("Pilih");
        if (opt.equalsIgnoreCase("x")) return;

        SortOption sortOption;
        switch (opt) {
            case "1": sortOption = SortOption.AMOUNT_ASC; break;
            case "2": sortOption = SortOption.AMOUNT_DESC; break;
            case "3": sortOption = SortOption.INCOME_FIRST; break;
            case "4": sortOption = SortOption.EXPENSE_FIRST; break;
            default:
                presenter.showError("[!] Pilihan tidak valid!");
                return;
        }
        presenter.showSortedTransactions(useCase.getSortedTransactions(sortOption));
    }

    private void deleteTransaction() {
        presenter.showPrompt("[Hapus Transaksi]");
        String idStr = InputUtil.input("ID Transaksi (x Jika Batal)");
        if (idStr.equalsIgnoreCase("x")) return;

        try {
            int id = Integer.parseInt(idStr);
            if (useCase.deleteTransaction(id)) {
                presenter.showMessage("Berhasil menghapus transaksi.");
            } else {
                presenter.showError("[!] Gagal menghapus transaksi dengan ID: " + id + ".");
            }
        } catch (NumberFormatException e) {
            presenter.showError("[!] ID tidak valid!");
        }
    }
}