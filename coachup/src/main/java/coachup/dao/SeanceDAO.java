package coachup.dao;

import coachup.model.Seance;

import java.util.List;

public abstract class SeanceDAO {
    public abstract List<Seance> getAllSeances();
    public abstract Seance getSeanceById(int id);
    public abstract int addSeance(Seance seance);
    public abstract void updateSeance(Seance seance);
    public abstract void deleteSeance(int id);
    public abstract List<Seance> getSeancesPassedByUserId(int id);

    public abstract List<Seance> getUpcomingSessionsByUserId(int id);

    public abstract List<Seance> getPendingPaymentsByUserId(int id);

    public abstract void paySeance(int idSeance);

    public abstract void refundSeance(int idSeance);
}
