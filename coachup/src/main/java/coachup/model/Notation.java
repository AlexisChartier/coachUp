package coachup.model;

public class Notation {

    private int NotationId;
    private String comment;
    private float note;
    private int CoachId;
    private int UserId;

    /**
     * Constructeur par défaut.
     */
    public Notation() {
        // Constructeur par défaut
    }


    public Notation(int NotationId, String comment, float note, int CoachId, int UserId) {
        this.NotationId = NotationId;
        this.comment = comment;
        this.note = note;
        this.CoachId = CoachId;
        this.UserId = UserId;
    }

    public int getNotationId() {
        return NotationId;
    }

    public void setNotationId(int NotationId) {
        this.NotationId = NotationId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public float getNote() {
        return note;
    }

    public void setNote(float note) {
        this.note = note;
    }

    public int getCoachId() {
        return CoachId;
    }

    public void setCoachId(int CoachId) {
        this.CoachId = CoachId;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int UserId) {
        this.UserId = UserId;
    }

}