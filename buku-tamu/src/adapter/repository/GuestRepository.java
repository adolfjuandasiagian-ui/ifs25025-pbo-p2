package adapter.repository;

import domain.entity.Guest;
import domain.repository.IGuestRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GuestRepository implements IGuestRepository {
    private final List<Guest> database = new ArrayList<>();
    private int idCounter = 0;

    @Override
    public List<Guest> findAll() {
        return new ArrayList<>(database);
    }

    @Override
    public Optional<Guest> findById(int id) {
        return database.stream().filter(guest -> guest.getId() == id).findFirst();
    }

    @Override
    public Guest save(String name, String purpose) {
        Guest guest = new Guest(++idCounter, name, purpose);
        database.add(guest);
        return guest;
    }

    @Override
    public boolean deleteById(int id) {
        return database.removeIf(guest -> guest.getId() == id);
    }
}