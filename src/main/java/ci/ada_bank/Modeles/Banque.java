package ci.ada_bank.Modeles;

public class Banque {
    private Long id;
    private String nom;
    private String pays;
    private String ville;
    private String dateCreation;
    private int nombreClients;

    public Banque() {
        // Constructeur par défaut
    }

    public Banque(Long id, String nom, String pays, String ville, String dateCreation, int nombreClients) {
        this.id = id;
        this.nom = nom;
        this.pays = pays;
        this.ville = ville;
        this.dateCreation = dateCreation;
        this.nombreClients = nombreClients;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(String dateCreation) {
        this.dateCreation = dateCreation;
    }

    public int getNombreClients() {
        return nombreClients;
    }

    public void setNombreClients(int nombreClients) {
        this.nombreClients = nombreClients;
    }

    public String toString() {
        return String.format(" Banque : {nom : %s, pays : %s, ville : %s, dateCreation : %s, nombreClients : %s}",nom,pays,ville,dateCreation,nombreClients);
    }
}
