package framework.view;

import adapter.presenter.ItemPresenter;
import domain.entity.SortOption;
import framework.util.InputUtil;
import usecase.ItemUseCase;

public class ItemView {
    private final ItemUseCase useCase;
    private final ItemPresenter presenter;

    public ItemView(ItemUseCase useCase, ItemPresenter presenter) {
        this.useCase = useCase;
        this.presenter = presenter;
    }

    public void show() {
        boolean running = true;
        while (running) {
            presenter.showItems(useCase.getAllItems());
            presenter.showMenu();
            String input = InputUtil.input("Pilih");
            switch (input) {
                case "1" -> addItem();
                case "2" -> updateItem();
                case "3" -> searchItem();
                case "4" -> sortItem();
                case "5" -> removeItem();
                case "x" -> running = false;
                default -> presenter.showInvalidChoice();
            }
            if (running)
                presenter.showBlankLine();
        }
    }

    private void addItem() {
        presenter.showPrompt("[Menambah Barang]");
        String name = InputUtil.input("Nama (x Jika Batal)");
        if (name.equalsIgnoreCase("x"))
            return;
        if (name.isBlank()) {
            presenter.showInvalidRequiredInput();
            return;
        }

        String strQuantity = InputUtil.input("Jumlah");
        if (strQuantity.equalsIgnoreCase("x"))
            return;

        Integer quantity = parseQuantity(strQuantity);
        if (quantity == null || quantity <= 0) {
            presenter.showInvalidQuantity();
            return;
        }

        String category = InputUtil.input("Kategori (x Jika Batal)");
        if (category.equalsIgnoreCase("x"))
            return;
        if (category.isBlank()) {
            presenter.showInvalidRequiredInput();
            return;
        }

        presenter.showAddSuccess(useCase.addItem(name, quantity, category));
    }

    private void updateItem() {
        presenter.showPrompt("[Mengubah Stok]");
        String strId = InputUtil.input("ID Barang yang diubah (x Jika Batal)");
        if (strId.equalsIgnoreCase("x"))
            return;

        Integer id = parseId(strId);
        if (id == null)
            return;

        String strQuantity = InputUtil.input("Jumlah Baru (Kosongkan jika tidak ingin mengubah)");
        if (strQuantity.equalsIgnoreCase("x"))
            return;
        Integer quantity = null;
        if (!strQuantity.isBlank()) {
            quantity = parseQuantity(strQuantity);
            if (quantity == null || quantity <= 0) {
                presenter.showInvalidQuantity();
                return;
            }
        }

        if (useCase.updateItem(id, quantity)) {
            presenter.showUpdateSuccess();
        } else {
            presenter.showUpdateFailed(id);
        }
    }

    private void searchItem() {
        presenter.showPrompt("[Mencari Barang]");
        String keyword = InputUtil.input("Kata Kunci (x Jika Batal)");
        if (!keyword.equalsIgnoreCase("x")) {
            presenter.showSearchResults(useCase.searchItems(keyword), keyword);
        }
    }

    private void sortItem() {
        presenter.showPrompt("[Mengurutkan Barang]");
        presenter.showSortMenu();
        String input = InputUtil.input("Pilih");
        if (input.equalsIgnoreCase("x"))
            return;

        SortOption option = mapSortOption(input);
        if (option == null) {
            presenter.showInvalidSortOption();
            return;
        }

        presenter.showSortedItems(useCase.sortItems(option));
    }

    private void removeItem() {
        presenter.showPrompt("[Menghapus Barang]");
        String strId = InputUtil.input("[ID Barang] yang dihapus (x Jika Batal)");
        if (strId.equalsIgnoreCase("x"))
            return;

        Integer id = parseId(strId);
        if (id == null)
            return;

        if (useCase.removeItem(id)) {
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

    private Integer parseQuantity(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private SortOption mapSortOption(String input) {
        return switch (input) {
            case "1" -> SortOption.NAME_ASC;
            case "2" -> SortOption.NAME_DESC;
            case "3" -> SortOption.QUANTITY_ASC;
            case "4" -> SortOption.QUANTITY_DESC;
            default -> null;
        };
    }
}
