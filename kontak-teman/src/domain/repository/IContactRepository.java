package domain.repository;

import domain.entity.Contact;
import java.util.List;
import java.util.Optional;

public interface IContactRepository {
    List<Contact> findAll();
    /** Returns a detached copy; call update to persist changes. */
    Optional<Contact> findById(int id);
    Contact save(String name, String phone, String email);
    boolean deleteById(int id);
    /** Persists changes made to a detached entity. */
    void update(Contact contact);
}
