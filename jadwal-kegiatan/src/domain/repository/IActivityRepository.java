package domain.repository;

import domain.entity.Activity;
import java.util.List;
import java.util.Optional;

public interface IActivityRepository {
    List<Activity> findAll();
    /** Returns a detached copy; call update to persist changes. */
    Optional<Activity> findById(int id);
    Activity save(String title, String day, String time);
    boolean deleteById(int id);
    /** Persists changes made to a detached entity. */
    void update(Activity activity);
}
