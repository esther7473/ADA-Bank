package ci.ada_bank.Modeles;

import org.junit.Test;
import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.Assert.assertEquals;

public class BanqueTest  {

    private final static Long id = 1L;
    private final static String nom = "Banque1";
    private final static String pays = "France";
    private final static String ville = "Paris";
    private final static String dateCreation = LocalDateTime.now().toString();
    private final static int nombreClients = 10;
    //private String bankToString = "Banque : {nom : Banque1, pays : France, ville : Paris, dateCreation : "+LocalDateTime.now()+", nombreClients : 10}" ;


    private Banque banque;

    @Test
    public void assertThatFieldsAreEquals(){
        banque = new Banque();
        banque.setId(id);
        banque.setNom(nom);
        banque.setPays(pays);
        banque.setVille(ville);
        banque.setDateCreation(dateCreation);
        banque.setNombreClients(nombreClients);


        assertThat(banque)
                .isNotNull()
                .satisfies(banque1 -> assertEquals(id, banque.getId()))
                .satisfies(banque1 -> assertEquals(nom, banque.getNom()))
                .satisfies(banque1 -> assertEquals(pays, banque.getPays()))
                .satisfies(banque1 -> assertEquals(ville, banque.getVille()))
                .satisfies(banque1 -> assertEquals(dateCreation, banque.getDateCreation()))
                .satisfies(banque1 -> assertEquals(nombreClients, banque.getNombreClients()));
                //.satisfies(banque1 -> assertEquals(bankToString, banque.toString()));

        banque = new Banque(id,nom,pays,ville,dateCreation,nombreClients);


        banque.setId(id);
        banque.setNom(nom);
        banque.setPays(pays);
        banque.setVille(ville);
        banque.setDateCreation(dateCreation);
        banque.setNombreClients(nombreClients);
        //bankToString = banque.toString();

        assertThat(banque)
                .isNotNull()
                .satisfies(banque1 -> assertEquals(id, banque.getId()))
                .satisfies(banque1 -> assertEquals(nom, banque.getNom()))
                .satisfies(banque1 -> assertEquals(pays, banque.getPays()))
                .satisfies(banque1 -> assertEquals(ville, banque.getVille()))
                .satisfies(banque1 -> assertEquals(dateCreation, banque.getDateCreation()))
                .satisfies(banque1 -> assertEquals(nombreClients, banque.getNombreClients()));
//                .satisfies(banque1 -> assertEquals(bankToString, banque.toString()));


    }
}