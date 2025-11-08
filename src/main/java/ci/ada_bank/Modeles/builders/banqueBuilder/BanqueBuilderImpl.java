package ci.ada_bank.Modeles.builders.banqueBuilder;

import ci.ada_bank.Modeles.Banque;

public class BanqueBuilderImpl implements BanqueBuilderInterface {
    private Long id;
    private String nom;
    private String pays;
    private String ville;
    private String dateCreation;
    private int nombreClients;


    @Override
    public BanqueBuilderInterface setId(Long id) {
        this.id = id;
        return this;
    }

    @Override
    public BanqueBuilderInterface setNom(String nom) {
        this.nom = nom;
        return this;
    }

    @Override
    public BanqueBuilderInterface setPays(String pays) {
        this.pays = pays;
        return this;
    }

    @Override
    public BanqueBuilderInterface setVille(String ville) {
        this.ville = ville;
        return this;
    }

    @Override
    public BanqueBuilderInterface setDateCreation(String dateCreation) {
        this.dateCreation = dateCreation;
        return this;
    }

    @Override
    public BanqueBuilderInterface setNombreClients(String nombreClients) {
        this.nombreClients = Integer.parseInt(nombreClients);
        return this;
    }

    @Override
    public BanqueBuilderInterface setNombreBanque(String nombreBanque) {
        this.nom = nombreBanque;
        return this;
    }

    @Override
    public Banque build() {
        return new Banque(id,nom,pays,ville,dateCreation,nombreClients);
    }
}
