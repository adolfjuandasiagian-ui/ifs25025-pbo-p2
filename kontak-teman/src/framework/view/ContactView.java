package framework.view;

import adapter.presenter.ContactPresenter;
import domain.entity.SortOption;
import framework.util.InputUtil;
import usecase.ContactUseCase;

public class ContactView {
    private final ContactUseCase useCase;
    private final ContactPresenter presenter;

    public ContactView(ContactUseCase useCase, ContactPresenter presenter) {
        this.useCase = useCase;
        this.presenter = presenter;
    }

    public void show() {
        boolean running = true;
        while (running) {
            presenter.showContacts(useCase.getAllContacts());
            presenter.showMenu();
            String input = InputUtil.input("Pilih");
            switch (input) {
                case "1" -> addContact();
                case "2" -> updateContact();
                case "3" -> searchContact();
                case "4" -> sortContact();
                case "5" -> removeContact();
                case "x" -> running = false;
                default -> presenter.showInvalidChoice();
            }
            if (running) presenter.showBlankLine();
        }
    }

    private void addContact() {
        presenter.showPrompt("[Menambah Kontak]");
        String name = InputUtil.input("Nama (x Jika Batal)");
        if (name.equalsIgnoreCase("x")) return;
        if (name.isBlank()) {
            presenter.showInvalidRequiredInput();
            return;
        }
        
        String phone = InputUtil.input("Telepon");
        if (phone.equalsIgnoreCase("x")) return;
        if (phone.isBlank()) {
            presenter.showInvalidRequiredInput();
            return;
        }
        
        String email = InputUtil.input("Email");
        if (email.equalsIgnoreCase("x")) return;
        if (email.isBlank()) {
            presenter.showInvalidRequiredInput();
            return;
        }
        
        presenter.showAddSuccess(useCase.addContact(name, phone, email));
    }

    private void updateContact() {
        presenter.showPrompt("[Mengubah Kontak]");
        String strId = InputUtil.input("ID Kontak yang diubah (x Jika Batal)");
        if (strId.equalsIgnoreCase("x")) return;
        
        Integer id = parseId(strId);
        if (id == null) return;
        
        String newName = InputUtil.input("Nama Baru (Kosongkan jika tidak ingin mengubah)");
        if (newName.equalsIgnoreCase("x")) return;
        String newPhone = InputUtil.input("Telepon Baru (Kosongkan jika tidak ingin mengubah)");
        if (newPhone.equalsIgnoreCase("x")) return;
        String newEmail = InputUtil.input("Email Baru (Kosongkan jika tidak ingin mengubah)");
        if (newEmail.equalsIgnoreCase("x")) return;
        
        String name = newName.isBlank() ? null : newName;
        String phone = newPhone.isBlank() ? null : newPhone;
        String email = newEmail.isBlank() ? null : newEmail;
        
        if (useCase.updateContact(id, name, phone, email)) {
            presenter.showUpdateSuccess();
        } else {
            presenter.showUpdateFailed(id);
        }
    }

    private void searchContact() {
        presenter.showPrompt("[Mencari Kontak]");
        String keyword = InputUtil.input("Kata Kunci (x Jika Batal)");
        if (!keyword.equalsIgnoreCase("x")) {
            presenter.showSearchResults(useCase.searchContacts(keyword), keyword);
        }
    }

    private void sortContact() {
        presenter.showPrompt("[Mengurutkan Kontak]");
        presenter.showSortMenu();
        String input = InputUtil.input("Pilih");
        if (input.equalsIgnoreCase("x")) return;
        
        SortOption option = mapSortOption(input);
        if (option == null) {
            presenter.showInvalidSortOption();
            return;
        }
        
        presenter.showSortedContacts(useCase.sortContacts(option));
    }

    private void removeContact() {
        presenter.showPrompt("[Menghapus Kontak]");
        String strId = InputUtil.input("[ID Kontak] yang dihapus (x Jika Batal)");
        if (strId.equalsIgnoreCase("x")) return;
        
        Integer id = parseId(strId);
        if (id == null) return;
        
        if (useCase.removeContact(id)) {
            presenter.showRemoveSuccess();
        } else {
            presenter.showRemoveFailed(id);
        }
    }

    private Integer parseId(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            presenter.showInvalidId();
            return null;
        }
    }

    private SortOption mapSortOption(String input) {
        return switch (input) {
            case "1" -> SortOption.NAME_ASC;
            case "2" -> SortOption.NAME_DESC;
            default -> null;
        };
    }
}
