package framework.view;

import adapter.presenter.TodoPresenter;
import domain.entity.SortOption;
import framework.util.InputUtil;
import usecase.TodoUseCase;

public class TodoView {
    private final TodoUseCase useCase;
    private final TodoPresenter presenter;

    public TodoView(TodoUseCase useCase, TodoPresenter presenter) {
        this.useCase = useCase;
        this.presenter = presenter;
    }

    public void show() {
        boolean running = true;
        while (running) {
            presenter.showTodos(useCase.getAllTodos());
            presenter.showMenu();
            String input = InputUtil.input("Pilih");
            switch (input) {
                case "1" -> addTodo();
                case "2" -> updateTodo();
                case "3" -> searchTodo();
                case "4" -> sortTodo();
                case "5" -> removeTodo();
                case "x" -> running = false;
                default -> presenter.showInvalidChoice();
            }
            if (running)
                System.out.println();
        }
    }

    private void addTodo() {
        System.out.println("[Tambah Todo]");
        String title = InputUtil.input("Judul (x Jika Batal)");
        if (title.equals("x"))
            return;
        if (title.isBlank()) {
            presenter.showInvalidTitle();
            return;
        }
        presenter.showAddSuccess(useCase.addTodo(title));
    }

    private void updateTodo() {
        System.out.println("[Ubah Todo]");
        String strId = InputUtil.input("ID Todo (x Jika Batal)");
        if (strId.equals("x"))
            return;
        Integer id = parseId(strId);
        if (id == null)
            return;
        String newTitle = InputUtil.input("Judul Baru (kosongkan jika tidak diubah, x untuk batal)");
        if (newTitle.equalsIgnoreCase("x"))
            return;
        String finishedInput = InputUtil.input("Selesai? (y/n, kosongkan jika tidak diubah, x untuk batal)");
        if (finishedInput.equalsIgnoreCase("x"))
            return;

        String title = newTitle.isBlank() ? null : newTitle;
        Boolean done = null;
        if (!finishedInput.isBlank()) {
            if (finishedInput.equalsIgnoreCase("y")) {
                done = true;
            } else if (finishedInput.equalsIgnoreCase("n")) {
                done = false;
            } else {
                presenter.showInvalidFinishedStatus();
                return;
            }
        }

        if (useCase.updateTodo(id, title, done)) {
            presenter.showUpdateSuccess();
        } else {
            presenter.showUpdateFailed(id);
        }
    }

    private void searchTodo() {
        System.out.println("[Cari Todo]");
        String keyword = InputUtil.input("Kata Kunci (x Jika Batal)");
        if (!keyword.equals("x")) {
            presenter.showSearchResults(useCase.searchTodos(keyword), keyword);
        }
    }

    private void sortTodo() {
        System.out.println("[Urutkan Todo]");
        presenter.showSortMenu();
        String input = InputUtil.input("Pilih");
        if (input.equals("x"))
            return;
        SortOption option = mapSortOption(input);
        if (option == null) {
            presenter.showInvalidSortOption();
            return;
        }
        presenter.showSortedTodos(useCase.sortTodos(option));
    }

    private void removeTodo() {
        System.out.println("[Hapus Todo]");
        String strId = InputUtil.input("ID Todo (x Jika Batal)");
        if (strId.equals("x"))
            return;
        Integer id = parseId(strId);
        if (id == null)
            return;
        if (useCase.removeTodo(id)) {
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
            case "1" -> SortOption.TITLE_ASC;
            case "2" -> SortOption.TITLE_DESC;
            case "3" -> SortOption.STATUS_DONE_FIRST;
            case "4" -> SortOption.STATUS_UNDONE_FIRST;
            default -> null;
        };
    }
}
