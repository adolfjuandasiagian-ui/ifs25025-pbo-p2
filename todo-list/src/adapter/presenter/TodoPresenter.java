package adapter.presenter;

import domain.entity.Todo;
import java.util.List;

public class TodoPresenter {
    private String format(Todo todo) {
        String status = todo.isDone() ? "Selesai" : "Belum Selesai";
        return String.format("%d | %s | %s", todo.getId(), todo.getTitle(), status);
    }

    private void printList(List<Todo> list, String header, String emptyMessage) {
        System.out.println(header);
        if (list == null || list.isEmpty()) {
            System.out.println(emptyMessage);
        } else {
            for (Todo t : list) {
                System.out.println(format(t));
            }
        }
    }

    public void showTodos(List<Todo> list) {
        printList(list, "Daftar Todo:", "- Data todo belum tersedia!");
    }

    public void showSearchResults(List<Todo> list, String keyword) {
        printList(list, "Hasil Pencarian: \"" + keyword + "\"", "- Todo tidak ditemukan!");
    }

    public void showSortedTodos(List<Todo> list) {
        printList(list, "Daftar Todo (Terurut):", "- Data todo belum tersedia!");
    }

    public void showMenu() {
        System.out.println("Menu:");
        System.out.println("1. Tambah Todo");
        System.out.println("2. Ubah (judul/status selesai)");
        System.out.println("3. Cari");
        System.out.println("4. Urutkan");
        System.out.println("5. Hapus");
        System.out.println("x. Keluar");
    }

    public void showSortMenu() {
        System.out.println("Pilihan Pengurutan:");
        System.out.println("1. Judul (A-Z)");
        System.out.println("2. Judul (Z-A)");
        System.out.println("3. Selesai Dulu");
        System.out.println("4. Belum Selesai Dulu");
        System.out.println("x. Batal");
    }

    public void showPrompt(String prompt) {
        System.out.println(prompt);
    }

    public void showBlankLine() {
        System.out.println();
    }

    public void showAddSuccess(Todo t) {
        System.out.printf("Berhasil menambah todo: %s%n", format(t));
    }

    public void showRemoveSuccess() {
        System.out.println("Berhasil menghapus todo.");
    }

    public void showRemoveFailed(int id) {
        System.out.printf("[!] Gagal menghapus todo dengan ID: %d.%n", id);
    }

    public void showUpdateSuccess() {
        System.out.println("Berhasil mengubah todo.");
    }

    public void showUpdateFailed(int id) {
        System.out.printf("[!] Gagal mengubah todo dengan ID: %d.%n", id);
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

    public void showInvalidTitle() {
        System.out.println("[!] Judul tidak boleh kosong!");
    }

    public void showInvalidFinishedStatus() {
        System.out.println("[!] Pilihan status selesai tidak valid (gunakan y/n)!");
    }
}
