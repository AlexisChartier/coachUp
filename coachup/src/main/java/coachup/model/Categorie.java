package coachup.model;

/**
 * La classe Categorie représente une catégorie de coachs.
 * Chaque catégorie possède un identifiant, un nom et une description.
 */
public class Categorie {

    private int idcategorie;
    private String nom;
    private String description;

    /**
     * Constructeur par défaut de la classe Categorie.
     * Initialise les propriétés à leurs valeurs par défaut.
     */
    public Categorie() {
    }

    /**
     * Obtient l'identifiant de la catégorie.
     *
     * @return L'identifiant de la catégorie.
     */
    public int getIdcategorie() {
        return idcategorie;
    }

    /**
     * Définit l'identifiant de la catégorie.
     *
     * @param idcategorie L'identifiant à définir.
     */
    public void setIdcategorie(int idcategorie) {
        this.idcategorie = idcategorie;
    }

    /**
     * Obtient le nom de la catégorie.
     *
     * @return Le nom de la catégorie.
     */
    public String getNom() {
        return nom;
    }

    /**
     * Définit le nom de la catégorie.
     *
     * @param nom Le nom à définir.
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Obtient la description de la catégorie.
     *
     * @return La description de la catégorie.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Définit la description de la catégorie.
     *
     * @param description La description à définir.
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
