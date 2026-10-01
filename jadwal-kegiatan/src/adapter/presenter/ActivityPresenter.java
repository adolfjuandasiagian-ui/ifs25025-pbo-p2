package adapter.presenter;

import domain.entity.Activity;
import java.util.List;

public class ActivityPresenter {
    private String format(Activity activity) {
        return String.format("%d | %s | %s | %s", activity.getId(), activity.getTitle(), activity.getDay(), activity.getTime());
    }

    private void printList(List<Activity> list, String header, String emptyMessage) {
        System.out.println(header);
        if (list == null || list.isEmpty()) {
            System.out.println(emptyMessage);
        } else {
            for (Activity activity : list) {
                System.out.println(format(activity));
            }
        }
    }

    public void showActivities(List<Activity> list) {
        printList(list, "Daftar Kegiatan:", "- Data kegiatan belum tersedia!");
    }

    public void showSearchResults(List<Activity> list, String keyword) {
        printList(list, "Hasil Pencarian: \"" + keyword + "\"", "- Kegiatan tidak ditemukan!");
    }

    public void showSortedActivities(List<Activity> list) {
        printList(list, "Daftar Kegiatan (Terurut):", "- Data kegiatan belum tersedia!");
    }

    public void showMenu() {
        System.out.println("Menu:");
        System.out.println("1. Tambah");
        System.out.println("2. Ubah");
        System.out.println("3. Cari");
        System.out.println("4. Urutkan");
        System.out.println("5. Hapus");
        System.out.println("x. Keluar");
    }

    public void showSortMenu() {
        System.out.println("Pilihan Pengurutan:");
        System.out.println("1. Hari (Senin -> Minggu)");
        System.out.println("2. Waktu (Awal -> Akhir)");
        System.out.println("3. Judul (A-Z)");
        System.out.println("4. Judul (Z-A)");
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

    public void showAddSuccess(Activity activity) {
        System.out.printf("Berhasil menambah kegiatan: %s%n", format(activity));
    }

    public void showRemoveSuccess() {
        System.out.println("Berhasil menghapus kegiatan.");
    }

    public void showRemoveFailed(int id) {
        System.out.printf("[!] Gagal menghapus kegiatan dengan ID: %d.%n", id);
    }

    public void showUpdateSuccess() {
        System.out.println("Berhasil mengubah kegiatan.");
    }

    public void showUpdateFailed(int id) {
        System.out.printf("[!] Gagal mengubah kegiatan dengan ID: %d.%n", id);
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
}
