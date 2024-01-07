package coachup.model;

import java.util.Date;

public class Seance {
    private int idSeance;
    private Date date;
    private int idCoach;
    private int idUser;
    private int idCategorie;
    private String statutPaiement;

    public Seance(){}

    public int getIdSeance() {
        return idSeance;
    }

    public void setIdSeance(int idSeance) {
        this.idSeance = idSeance;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getIdCoach() {
        return idCoach;
    }

    public void setIdCoach(int idCoach) {
        this.idCoach = idCoach;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdCategorie() {
        return idCategorie;
    }

    public void setIdCategorie(int idCategorie) {
        this.idCategorie = idCategorie;
    }

    public String getStatutPaiement() {
        return statutPaiement;
    }

    public void setStatutPaiement(String statutPaiement) {
        this.statutPaiement = statutPaiement;
    }
}
