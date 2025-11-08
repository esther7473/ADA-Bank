package ci.ada_bank.Modeles;

import ci.ada_bank.Modeles.Enumeration.CompteType;
import ci.ada_bank.Modeles.Enumeration.Statut;

import java.math.BigDecimal;

public class Compte{
    private Long id;
    private String numeroCompte;
    private BigDecimal solde;
    private CompteType typeCompte;
    private Client client;
    private String dateOuverture;
    private Statut statut;

    public Compte() {
        this.solde= BigDecimal.ZERO;
    }

    public Compte(Long id, String numeroCompte, BigDecimal solde, CompteType typeCompte, Client client, String dateOuverture, Statut statut) {
        this.id = id;
        this.numeroCompte = numeroCompte;
        this.solde = solde;
        this.typeCompte = typeCompte;
        this.client = client;
        this.dateOuverture = dateOuverture;
        this.statut = statut;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumeroCompte() {
        return numeroCompte;
    }

    public void setNumeroCompte(String numeroCompte) {
        this.numeroCompte = numeroCompte;
    }

    public BigDecimal getSolde() {
        return solde;
    }

    public void setSolde(BigDecimal solde) {
        this.solde = solde;
    }

    public CompteType getTypeCompte() {
        return typeCompte;
    }

    public void setTypeCompte(CompteType typeCompte) {
        this.typeCompte = typeCompte;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getDateOuverture() {
        return dateOuverture;
    }

    public void setDateOuverture(String dateOuverture) {
        this.dateOuverture = dateOuverture;
    }

    public Statut getStatut() {
        return statut;
    }

    public void setStatut(Statut statut) {
        this.statut = statut;
    }
}
