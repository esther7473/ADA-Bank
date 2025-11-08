package ci.ada_bank.Modeles;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;


public class ClientTest {

    private final static Long id = 1L;
    private final static String nom = "test";
    private final static String prenom = "test";
    private final static String email = "test";
    private final static String numeroClient = "JDJD";
    private final static String dateCreation = "299994";
    private final static Banque banque = new Banque();

    private static Client client;

    @Test
    public void assertThatFieldsAreEquals() {
        client = new Client();
        client.setId(id);
        client.setNom(nom);
        client.setPrenom(prenom);
        client.setNumeroClient(numeroClient);
        client.setEmail(email);
        client.setDateInscription(dateCreation);
        client.setBanque(banque);

        assertThat(client).isNotNull()
                .satisfies(client1 -> assertEquals(id, client1.getId()))
                .satisfies(client1 -> assertEquals(nom, client1.getNom()))
                .satisfies(client1 -> assertEquals(prenom, client1.getPrenom()))
                .satisfies(client1 -> assertEquals(numeroClient, client1.getNumeroClient()))
                .satisfies(client1 -> assertEquals(email, client1.getEmail()))
                .satisfies(client1 -> assertEquals(dateCreation, client1.getDateInscription()))
                .satisfies(client1 -> assertEquals(banque, client1.getBanque()));

        client = new Client(id,nom,prenom,email,numeroClient,banque,dateCreation);
        client.setId(id);
        client.setNom(nom);
        client.setNumeroClient(numeroClient);
        client.setEmail(email);
        client.setDateInscription(dateCreation);
        client.setBanque(banque);

        assertThat(client).isNotNull()
                .satisfies(client1 -> assertEquals(id, client1.getId()))
                .satisfies(client1 -> assertEquals(nom, client1.getNom()))
                .satisfies(client1 -> assertEquals(numeroClient, client1.getNumeroClient()))
                .satisfies(client1 -> assertEquals(email, client1.getEmail()))
                .satisfies(client1 -> assertEquals(dateCreation, client1.getDateInscription()))
                .satisfies(client1 -> assertEquals(banque, client1.getBanque()));


    }



}