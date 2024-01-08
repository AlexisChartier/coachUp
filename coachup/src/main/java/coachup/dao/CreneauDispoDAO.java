package coachup.dao;

import coachup.model.Creneau_dispo;

import java.sql.SQLException;
import java.util.List;

public abstract class CreneauDispoDAO {

    public abstract int addCreneauDispo(Creneau_dispo creneau_dispo);

    public abstract List<Creneau_dispo> getCreneauByDay(Integer year, Integer month, Integer day);

    public abstract void setReserved(int id) throws SQLException;

    public abstract boolean deleteCreneauDispo(int creneauDispoId);

    public abstract boolean modifyCreneauDispo(Creneau_dispo creneau_dispo);

    public abstract List<Creneau_dispo> getCreneauByDayAndCoachId(Integer year, Integer month, Integer day, Integer coachId);

    public abstract void reserverCreneau(Creneau_dispo creneau_dispo, int iduser, int idcategorie) throws SQLException;
}
