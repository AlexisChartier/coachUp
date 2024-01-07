package coachup.dao;

import coachup.model.Categorie;
import coachup.model.Coach;

import java.util.List;

public abstract class CoachDAO {

    public abstract Coach getCoachById(int coachId);

    public abstract List<Coach> getAllCoaches();

    public abstract boolean addCoach(Coach coach);

    public abstract boolean updateCoach(Coach coach);

    public abstract boolean deleteCoach(int coachId);


    public abstract List<Coach> getUnapprovedCoaches();

    public abstract void denyCoach(int id);

    public abstract List<Categorie> getCategoriesByCoachId(int id);
}
