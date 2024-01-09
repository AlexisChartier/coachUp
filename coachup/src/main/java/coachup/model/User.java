package coachup.model;

/**
 * Représente un utilisateur avec ses informations associées.
 */
public class User {

    private int idUtilisateur;
    private String nom;
    private String email;
    private String motDePasse;
    private String role;

    /**
     * Constructeur par défaut de la classe User.
     */
    public User() {
        // Constructeur par défaut
    }

    /**
     * Constructeur avec paramètres pour initialiser toutes les propriétés de l'utilisateur.
     *
     * @param idUtilisateur L'identifiant de l'utilisateur.
     * @param nom            Le nom de l'utilisateur.
     * @param email          L'adresse e-mail de l'utilisateur.
     * @param motDePasse     Le mot de passe de l'utilisateur.
     * @param role           Le rôle de l'utilisateur.
     */
    public User(int idUtilisateur, String nom, String email, String motDePasse, String role) {
        this.idUtilisateur = idUtilisateur;
        this.nom = nom;
        this.email = email;
        this.motDePasse = motDePasse;
        this.role = role;
    }

    // Getters et Setters

    /**
     * Retourne l'identifiant de l'utilisateur.
     *
     * @return L'identifiant de l'utilisateur.
     */
    public int getIdUtilisateur() {
        return idUtilisateur;
    }

    /**
     * Définit l'identifiant de l'utilisateur.
     *
     * @param idUtilisateur L'identifiant de l'utilisateur.
     */
    public void setIdUtilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    /**
     * Retourne le nom de l'utilisateur.
     *
     * @return Le nom de l'utilisateur.
     */
    public String getNom() {
        return nom;
    }

    /**
     * Définit le nom de l'utilisateur.
     *
     * @param nom Le nom de l'utilisateur.
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Retourne l'adresse e-mail de l'utilisateur.
     *
     * @return L'adresse e-mail de l'utilisateur.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Définit l'adresse e-mail de l'utilisateur.
     *
     * @param email L'adresse e-mail de l'utilisateur.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Retourne le mot de passe de l'utilisateur.
     *
     * @return Le mot de passe de l'utilisateur.
     */
    public String getMotDePasse() {
        return motDePasse;
    }

    /**
     * Définit le mot de passe de l'utilisateur.
     *
     * @param motDePasse Le mot de passe de l'utilisateur.
     */
    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    /**
     * Retourne le rôle de l'utilisateur.
     *
     * @return Le rôle de l'utilisateur.
     */
    public String getRole() {
        return role;
    }

    /**
     * Définit le rôle de l'utilisateur.
     *
     * @param role Le rôle de l'utilisateur.
     */
    public void setRole(String role) {
        this.role = role;
    }
}
