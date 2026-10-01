package usecase;

import domain.entity.Guest;
import domain.repository.IGuestRepository;
import java.util.List;

public class GuestUseCase {
    private final IGuestRepository repository;

    public GuestUseCase(IGuestRepository repository) {
        this.repository = repository;
    }

    public Guest addGuest(String name, String purpose) {
        return repository.save(name, purpose);
    }

    public List<Guest> getAllGuests() {
        return repository.findAll();
    }

    public List<Guest> searchGuests(String keyword) {
        String lowerKeyword = keyword.toLowerCase();
        return repository.findAll().stream()
                .filter(guest -> guest.getName().toLowerCase().contains(lowerKeyword))
                .toList();
    }

    public boolean deleteGuest(int id) {
        return repository.deleteById(id);
    }
}