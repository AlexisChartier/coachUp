package coachup.dao;

import coachup.model.Creneau_dispo;

import java.util.List;

public abstract class CreneauDispoDAO {

    public abstract boolean addCreneauDispo(Creneau_dispo creneau_dispo);

    public abstract List<Creneau_dispo> getCreneauByDay(Integer year, Integer month, Integer day);

    public abstract boolean deleteCreneauDispo(int creneauDispoId);

    public abstract boolean modifyCreneauDispo(Creneau_dispo creneau_dispo);

}
