package adapter.presenter;

import domain.entity.Item;
import java.util.List;

public class ItemPresenter {
    private String format(Item item) {
        return String.format("%d | %s | %d | %s", item.getId(), item.getName(), item.getQuantity(), item.getCategory());
    }

    private void printList(List<Item> list, String header, String emptyMessage) {
        System.out.println(header);
        if (list.isEmpty()) {
            System.out.println(emptyMessage);
        } else {
            for (Item item : list) {
                System.out.println(format(item));
            }
        }
    }

    public void showItems(List<Item> list) {
        printList(list, "Daftar Barang:", "- Data barang belum tersedia!");
    }

    public void showSearchResults(List<Item> list, String keyword) {
        printList(list, "Hasil Pencarian: \"" + keyword + "\"", "- Barang tidak ditemukan!");
    }

    public void showSortedItems(List<Item> list) {
        printList(list, "Daftar Barang (Terurut):", "- Data barang belum tersedia!");
    }

    public void showMenu() {
        System.out.println("Menu:");
        System.out.println("1. Tambah");
        System.out.println("2. Ubah Stok");
        System.out.println("3. Cari");
        System.out.println("4. Urutkan");
        System.out.println("5. Hapus");
        System.out.println("x. Keluar");
    }

    public void showSortMenu() {
        System.out.println("Pilihan Pengurutan:");
        System.out.println("1. Nama (A-Z)");
        System.out.println("2. Nama (Z-A)");
        System.out.println("3. Jumlah (Terkecil -> Terbesar)");
        System.out.println("4. Jumlah (Terbesar -> Terkecil)");
        System.out.println("x. Batal");
    }

    public void showPrompt(String prompt) {
        System.out.println(prompt);
    }

    public void showBlankLine() {
        System.out.println();
    }

    public void showAddSuccess(Item item) {
        System.out.printf("Berhasil menambah barang: %s%n", format(item));
    }

    public void showRemoveSuccess() {
        System.out.println("Berhasil menghapus barang.");
    }

    public void showRemoveFailed(int id) {
        System.out.printf("[!] Gagal menghapus barang dengan ID: %d.%n", id);
    }

    public void showUpdateSuccess() {
        System.out.println("Berhasil mengubah stok barang.");
    }

    public void showUpdateFailed(int id) {
        System.out.printf("[!] Gagal mengubah stok barang dengan ID: %d.%n", id);
    }

    public void showInvalidChoice() {
        System.out.println("[!] Pilihan tidak dimengerti.");
    }

    public void showInvalidId() {
        System.out.println("[!] ID tidak valid!");
    }

    public void showInvalidSortOption() {
        System.out.println("[!] Pilihan tidak valid!");
    }

    public void showInvalidQuantity() {
        System.out.println("[!] Jumlah stok tidak valid!");
    }

    public void showInvalidRequiredInput() {
        System.out.println("[!] Input tidak boleh kosong!");
    }
}
