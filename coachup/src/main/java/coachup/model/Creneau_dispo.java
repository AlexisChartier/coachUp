package coachup.model;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.ZonedDateTime;
import java.time.LocalDateTime;

public class Creneau_dispo {
    private ZonedDateTime date_debut;

    private ZonedDateTime date_fin;

    private Integer CoachId;
    private Integer Creneau_dispo_id;

    public Creneau_dispo(ZonedDateTime date_debut,ZonedDateTime date_fin, Integer CoachId,Integer Creneau_dispo_id) {
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.CoachId = CoachId;
        this.Creneau_dispo_id = Creneau_dispo_id;
    }

    public ZonedDateTime getDateDebut() {
        return date_debut;
    }

    public void setDateDebut(ZonedDateTime date) {
        this.date_debut = date;
    }

    public Timestamp getDateDebutTimestamp() {
        LocalDateTime withoutTimezone = date_debut.toLocalDateTime();
        Timestamp timestamp = Timestamp.valueOf(withoutTimezone);
        return timestamp;
    }

    public Timestamp getDateFinTimestamp() {
        LocalDateTime withoutTimezone = date_fin.toLocalDateTime();
        Timestamp timestamp = Timestamp.valueOf(withoutTimezone);
        return timestamp;
    }

    public ZonedDateTime getDateFin(){return date_fin;}

    public void setDateFin(ZonedDateTime date){this.date_fin = date;}

    public Integer getCoachId() {
        return CoachId;
    }

    public void setCoachId(Integer coachid) {
        this.CoachId = coachid;
    }

    public Integer getCreneauDispoId() {
        return Creneau_dispo_id;
    }

    public void setCreneauDispoId(Integer creneau_dispo_id) {
        this.Creneau_dispo_id = creneau_dispo_id;
    }





}