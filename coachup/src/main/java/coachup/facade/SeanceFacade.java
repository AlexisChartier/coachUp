package coachup.facade;

import coachup.dao.AbstractDAOFactory;
import coachup.model.Seance;

import java.sql.SQLException;
import java.util.List;

public class SeanceFacade {

    private static SeanceFacade instance;

    private Seance managedSeance;

    private static AbstractDAOFactory daoFactory;

    static {
        try {
            daoFactory = AbstractDAOFactory.getInstance();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private SeanceFacade() {
    }

    public static synchronized SeanceFacade getInstance() throws SQLException, ClassNotFoundException {
        if (instance == null) {
            instance = new SeanceFacade();
        }
        return instance;
    }

    public void setManagedSeance(Seance managedSeance) {
        this.managedSeance = managedSeance;
    }

    public Seance getManagedSeance() {
        return managedSeance;
    }

    public List<Seance> getSeancesPassedByUserId(int id) throws SQLException, ClassNotFoundException {
        return daoFactory.getSeanceDAO().getSeancesPassedByUserId(id);
    }

    public List<Seance> getAllSeances() throws SQLException, ClassNotFoundException {
        return daoFactory.getSeanceDAO().getAllSeances();
    }

    public Seance getSeanceById(int id) throws SQLException, ClassNotFoundException {
        return daoFactory.getSeanceDAO().getSeanceById(id);
    }

    public int addSeance(Seance seance) throws SQLException, ClassNotFoundException {
        return daoFactory.getSeanceDAO().addSeance(seance);
    }

    public void updateSeance(Seance seance) throws SQLException, ClassNotFoundException {
        daoFactory.getSeanceDAO().updateSeance(seance);
    }

    public void deleteSeance(int id) throws SQLException, ClassNotFoundException {
        daoFactory.getSeanceDAO().deleteSeance(id);
    }

    public List<Seance> getPendingSeances(int id) throws SQLException, ClassNotFoundException {
        return daoFactory.getSeanceDAO().getPendingPaymentsByUserId(id);
    }

    public List<Seance> getUpcomingSeances(int id) throws SQLException, ClassNotFoundException {
        return daoFactory.getSeanceDAO().getUpcomingSessionsByUserId(id);
    }

    public void paySeance(Seance seance) throws SQLException, ClassNotFoundException {
        daoFactory.getSeanceDAO().paySeance(seance.getIdSeance());
    }

    public void refundSeance(Seance seance) throws SQLException, ClassNotFoundException {
        daoFactory.getSeanceDAO().refundSeance(seance.getIdSeance());
    }
}
