package framework.view;

import domain.entity.Guest;
import adapter.presenter.GuestPresenter;
import framework.util.InputUtil;
import usecase.GuestUseCase;
import java.util.List;

public class GuestView {
    private final GuestUseCase guestUseCase;
    private final GuestPresenter guestPresenter;

    public GuestView(GuestUseCase guestUseCase, GuestPresenter guestPresenter) {
        this.guestUseCase = guestUseCase;
        this.guestPresenter = guestPresenter;
    }

    public void show() {
        while (true) {
            List<Guest> guests = guestUseCase.getAllGuests();
            guestPresenter.showGuests(guests);
            guestPresenter.showMenu();
            String menuOption = InputUtil.input("Pilih : ");

            if ("1".equals(menuOption)) {
                guestPresenter.showRegistrationPrompt();
                String name = InputUtil.input("Nama (x Jika Batal) : ");
                if ("x".equalsIgnoreCase(name)) {
                    guestPresenter.showBlankLine();
                    continue;
                }

                String purpose = InputUtil.input("Tujuan Kunjungan (x Jika Batal) : ");
                if ("x".equalsIgnoreCase(purpose)) {
                    guestPresenter.showBlankLine();
                    continue;
                }

                Guest createdGuest = guestUseCase.addGuest(name, purpose);
                guestPresenter.showAddSuccess(createdGuest);
                guestPresenter.showBlankLine();

            } else if ("2".equals(menuOption)) {
                guestPresenter.showSearchPrompt();
                String keyword = InputUtil.input("Nama (x Jika Batal) : ");
                if ("x".equalsIgnoreCase(keyword)) {
                    guestPresenter.showBlankLine();
                    continue;
                }

                List<Guest> searchResults = guestUseCase.searchGuests(keyword);
                guestPresenter.showSearchResults(keyword, searchResults);
                guestPresenter.showBlankLine();

            } else if ("3".equals(menuOption)) {
                guestPresenter.showDeletePrompt();
                String idInput = InputUtil.input("[ID Tamu] yang dihapus (x Jika Batal) : ");
                if ("x".equalsIgnoreCase(idInput)) {
                    guestPresenter.showBlankLine();
                    continue;
                }

                try {
                    int id = Integer.parseInt(idInput);
                    boolean isDeleted = guestUseCase.deleteGuest(id);
                    guestPresenter.showDeleteResult(isDeleted, id);
                } catch (NumberFormatException e) {
                    guestPresenter.showInvalidId();
                }
                guestPresenter.showBlankLine();

            } else if ("x".equalsIgnoreCase(menuOption)) {
                break;

            } else {
                guestPresenter.showInvalidChoice();
                guestPresenter.showBlankLine();
            }
        }
    }
}