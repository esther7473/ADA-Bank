package ci.ada_bank.Modeles.builders.clientBuilder;

import ci.ada_bank.Modeles.Banque;
import ci.ada_bank.Modeles.Client;

public class ClientBuilderImpl implements ClientBulderInterface {

    private Long id;
    private String nom;
    private String prenom;
    private String email;
    private String numeroClient;
    private String dateInscription;
    private Banque banque;



    @Override
    public ClientBulderInterface setId(Long id) {
        this.id = id;
        return this;
    }


    @Override
    public ClientBulderInterface setNom(String name) {
        this.nom = name;
        return this ;
    }

    @Override
    public ClientBulderInterface setEmail(String email) {
        this.email = email;
        return this;
    }

    @Override
    public ClientBulderInterface setPrenom(String prenom) {
        this.prenom = prenom;
        return this;
    }

    @Override
    public ClientBulderInterface setNumeroClient(String numeroClient) {
        this.numeroClient = numeroClient;
        return this;
    }

    @Override
    public ClientBulderInterface setDateInscription(String dateInscription) {
        this.dateInscription = dateInscription;
        return this;
    }

    @Override
    public ClientBulderInterface setBanque(Banque banque) {
        this.banque = banque;
        return this;
    }

    @Override
    public Client build() {
        return new Client(id,nom,prenom,email,numeroClient,banque,dateInscription);
    }


}

