package domain.repository;

import domain.entity.Todo;
import java.util.List;
import java.util.Optional;

public interface ITodoRepository {
    List<Todo> findAll();
    /** Returns a detached copy; call update to persist changes. */
    Optional<Todo> findById(int id);
    Todo save(String title);
    boolean deleteById(int id);
    /** Persists changes made to a detached entity. */
    void update(Todo todo);
}
