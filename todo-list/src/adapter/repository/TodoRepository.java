package adapter.repository;

import domain.entity.Todo;
import domain.repository.ITodoRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TodoRepository implements ITodoRepository {
    private final List<Todo> todos = new ArrayList<>();
    private int nextId = 1;

    @Override
    public List<Todo> findAll() {
        return new ArrayList<>(todos);
    }

    @Override
    public Optional<Todo> findById(int id) {
        return todos.stream().filter(t -> t.getId() == id).findFirst();
    }

    @Override
    public Todo save(String title) {
        Todo todo = new Todo(nextId++, title);
        todos.add(todo);
        return todo;
    }

    @Override
    public boolean deleteById(int id) {
        return todos.removeIf(t -> t.getId() == id);
    }

    @Override
    public void update(Todo todo) {
        for (int index = 0; index < todos.size(); index++) {
            if (todos.get(index).getId() == todo.getId()) {
                todos.set(index, todo);
                return;
            }
        }
    }
}
