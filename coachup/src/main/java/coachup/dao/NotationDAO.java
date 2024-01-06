package coachup.dao;

import coachup.model.Notation;

public abstract class NotationDAO {
    public abstract boolean addNotation(Notation notation);
    public abstract boolean modifyNotation(Notation notation);
    public abstract boolean deleteNotation(int NotationId);

    public abstract Notation[] getNotationByCoachId(int CoachId);

    public abstract Notation[] getNotationByUserId(int UserId);

    public abstract Notation getNotationById(int NotationId);

}
