package domain.repository;

import domain.entity.Item;
import java.util.List;
import java.util.Optional;

public interface IItemRepository {
    List<Item> findAll();
    /** Returns a detached copy; call update to persist changes. */
    Optional<Item> findById(int id);
    Item save(String name, int quantity, String category);
    boolean deleteById(int id);
    /** Persists changes made to a detached entity. */
    void update(Item item);
}
