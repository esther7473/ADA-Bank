package ci.ada_bank.Modeles.builders.clientBuilder;

import ci.ada_bank.Modeles.Banque;
import ci.ada_bank.Modeles.Client;

public interface ClientBulderInterface {
    ClientBulderInterface setId(Long id);
    ClientBulderInterface setNom(String name);
    ClientBulderInterface setEmail(String email);
    ClientBulderInterface setPrenom(String prenom);
    ClientBulderInterface setNumeroClient(String numeroClient);
    ClientBulderInterface setDateInscription(String dateInscription);
    ClientBulderInterface setBanque(Banque banque);

    Client build();
}
