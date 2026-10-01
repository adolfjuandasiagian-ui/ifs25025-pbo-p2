package adapter.repository;

import domain.entity.Activity;
import domain.repository.IActivityRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ActivityRepository implements IActivityRepository {
    private final List<Activity> data = new ArrayList<>();
    private int idCounter = 0;

    @Override
    public List<Activity> findAll() {
        return new ArrayList<>(data);
    }

    @Override
    public Optional<Activity> findById(int id) {
        return data.stream()
            .filter(activity -> activity.getId() == id)
            .findFirst()
            .map(activity -> new Activity(activity.getId(), activity.getTitle(), activity.getDay(), activity.getTime()));
    }

    @Override
    public Activity save(String title, String day, String time) {
        Activity activity = new Activity(++idCounter, title, day, time);
        data.add(activity);
        return activity;
    }

    @Override
    public boolean deleteById(int id) {
        return data.removeIf(a -> a.getId() == id);
    }

    @Override
    public void update(Activity activity) {
        for (int index = 0; index < data.size(); index++) {
            if (data.get(index).getId() == activity.getId()) {
                data.set(index, activity);
                return;
            }
        }
    }
}
