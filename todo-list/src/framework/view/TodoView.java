package framework.view;

import adapter.presenter.TodoPresenter;
import domain.entity.SortOption;
import framework.util.InputUtil;
import usecase.TodoUseCase;
import java.util.OptionalInt;

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
            if (input.equalsIgnoreCase("x")) {
                running = false;
            } else {
                switch (input) {
                    case "1" -> addTodo();
                    case "2" -> updateTodo();
                    case "3" -> searchTodo();
                    case "4" -> sortTodo();
                    case "5" -> removeTodo();
                    default -> presenter.showInvalidChoice();
                }
            }
            if (running)
                presenter.showBlankLine();
        }
    }

    private void addTodo() {
        presenter.showPrompt("[Tambah Todo]");
        String title = InputUtil.input("Judul (x Jika Batal)");
        if (title.equalsIgnoreCase("x"))
            return;
        if (title.isBlank()) {
            presenter.showInvalidTitle();
            return;
        }
        presenter.showAddSuccess(useCase.addTodo(title));
    }

    private void updateTodo() {
        presenter.showPrompt("[Ubah Todo]");
        String strId = InputUtil.input("ID Todo (x Jika Batal)");
        if (strId.equalsIgnoreCase("x"))
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
        presenter.showPrompt("[Cari Todo]");
        String keyword = InputUtil.input("Kata Kunci (x Jika Batal)");
        if (!keyword.equalsIgnoreCase("x")) {
            presenter.showSearchResults(useCase.searchTodos(keyword), keyword);
        }
    }

    private void sortTodo() {
        presenter.showPrompt("[Urutkan Todo]");
        presenter.showSortMenu();
        String input = InputUtil.input("Pilih");
        if (input.equalsIgnoreCase("x"))
            return;
        SortOption option = mapSortOption(input);
        if (option == null) {
            presenter.showInvalidSortOption();
            return;
        }
        presenter.showSortedTodos(useCase.sortTodos(option));
    }

    private void removeTodo() {
        presenter.showPrompt("[Hapus Todo]");
        String strId = InputUtil.input("ID Todo (x Jika Batal)");
        if (strId.equalsIgnoreCase("x"))
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
        OptionalInt parsed = InputUtil.parseInteger(value);
        if (parsed.isEmpty()) {
            presenter.showInvalidId();
            return null;
        }
        return parsed.getAsInt();
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
