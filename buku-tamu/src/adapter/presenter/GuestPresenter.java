package adapter.presenter;

import domain.entity.Guest;
import java.util.List;

public class GuestPresenter {

    public GuestPresenter() {
    }

    public void showGuests(List<Guest> guests) {
        System.out.println("Daftar Tamu:");
        if (guests == null || guests.isEmpty()) {
            System.out.println("- Data tamu belum tersedia!");
        } else {
            for (Guest guest : guests) {
                System.out.println(guest.getId() + " | " + guest.getName() + " | " + guest.getPurpose());
            }
        }
    }

    public void showMenu() {
        System.out.println("Menu:");
        System.out.println("1. Daftarkan");
        System.out.println("2. Cari");
        System.out.println("3. Hapus");
        System.out.println("x. Keluar");
    }

    public void showRegistrationPrompt() {
        System.out.println("[Mendaftarkan Tamu]");
    }

    public void showSearchPrompt() {
        System.out.println("[Mencari Tamu]");
    }

    public void showDeletePrompt() {
        System.out.println("[Menghapus Tamu]");
    }

    public void showBlankLine() {
        System.out.println();
    }

    public void showAddSuccess(Guest guest) {
        System.out.println("Berhasil mendaftarkan tamu: " + guest.getId() + " | " + guest.getName() + " | " + guest.getPurpose());
    }

    public void showDeleteResult(boolean deleted, int id) {
        if (deleted) {
            System.out.println("Berhasil menghapus tamu.");
        } else {
            System.out.println("[!] Gagal menghapus tamu dengan ID: " + id + ".");
        }
    }

    public void showInvalidId() {
        System.out.println("[!] ID tidak valid!");
    }

    public void showInvalidChoice() {
        System.out.println("[!] Pilihan tidak dimengerti.");
    }

    public void showSearchResults(String keyword, List<Guest> results) {
        System.out.println("Hasil Pencarian: \"" + keyword + "\"");
        if (results == null || results.isEmpty()) {
            System.out.println("- Tamu tidak ditemukan!");
        } else {
            for (Guest guest : results) {
                System.out.println(guest.getId() + " | " + guest.getName() + " | " + guest.getPurpose());
            }
        }
    }
}