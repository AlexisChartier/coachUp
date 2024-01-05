package coachup.dao;

import coachup.model.Coach;

import java.util.List;

public abstract class CoachDAO {

    public abstract Coach getCoachById(int coachId);

    public abstract List<Coach> getAllCoaches();

    public abstract boolean addCoach(Coach coach);

    public abstract boolean updateCoach(Coach coach);

    public abstract boolean deleteCoach(int coachId);

    public abstract List<Coach> getUnapprovedCoaches();
}
