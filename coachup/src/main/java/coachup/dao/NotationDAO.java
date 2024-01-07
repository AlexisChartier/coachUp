package coachup.dao;

import coachup.model.Notation;

public abstract class NotationDAO {
    public abstract boolean addNotation(Notation notation);
    public abstract boolean modifyNotation(Notation notation);
    public abstract boolean deleteNotation(int NotationId);

}
