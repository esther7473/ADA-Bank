package ci.ada_bank.Modeles;

import ci.ada_bank.Modeles.Enumeration.CompteType;
import ci.ada_bank.Modeles.Enumeration.Statut;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.Assert.assertEquals;


import java.math.BigDecimal;

@DisplayName("Test Compte modele")
public class CompteTest {
    private Long id = 2L;
    private String numeroCompte = "jssssssss";
    private BigDecimal solde = new BigDecimal("1.00");
    private CompteType typeCompte = CompteType.Épargne;
    private Client client ;
    private String dateOuverture = "ksssssssss";
    private Statut statut = Statut.Actif;

    private Compte compte;

    @Test
    public void assertThatFieldsAreEquals(){
        compte = new Compte();
        client = new Client();
        compte.setId(id);
        compte.setNumeroCompte(numeroCompte);
        compte.setSolde(solde);
        compte.setTypeCompte(typeCompte);
        compte.setClient(client);
        compte.setDateOuverture(dateOuverture);
        compte.setStatut(statut);

        assertThat(compte).isNotNull()
                .satisfies(compte1 -> assertEquals(numeroCompte,compte1.getNumeroCompte()))
                .satisfies(compte1 -> assertEquals(solde,compte1.getSolde()))
                .satisfies(compte1 -> assertEquals(typeCompte,compte1.getTypeCompte()))
                .satisfies(compte1 -> assertEquals(client,compte1.getClient()))
                .satisfies(compte1 -> assertEquals(dateOuverture,compte1.getDateOuverture()))
                .satisfies(compte1 -> assertEquals(statut,compte1.getStatut()))
                .satisfies(compte1 -> assertEquals(id,compte1.getId()));

        compte = new Compte(id,numeroCompte,solde,typeCompte,client,dateOuverture,statut);
        compte.setId(id);
        compte.setNumeroCompte(numeroCompte);
        compte.setSolde(solde);
        compte.setTypeCompte(typeCompte);
        compte.setClient(client);
        compte.setDateOuverture(dateOuverture);
        compte.setStatut(statut);

        assertThat(compte).isNotNull()
                .satisfies(compte1 -> assertEquals(numeroCompte,compte1.getNumeroCompte()))
                .satisfies(compte1 -> assertEquals(solde,compte1.getSolde()))
                .satisfies(compte1 -> assertEquals(typeCompte,compte1.getTypeCompte()))
                .satisfies(compte1 -> assertEquals(client,compte1.getClient()))
                .satisfies(compte1 -> assertEquals(dateOuverture,compte1.getDateOuverture()))
                .satisfies(compte1 -> assertEquals(statut,compte1.getStatut()))
                .satisfies(compte1 -> assertEquals(id,compte1.getId()));

    }
}