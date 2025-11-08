package ci.ada.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class AdressTest {

    private final String pays = "Côte d'Ivoire";
    private final String ville = "Abidjan";
    private final String longitude = "-4.008256";
    private final String latitude = "5.359952";

    private Adress adress;

    @BeforeEach
    void setUp() {
        adress = new Adress();
    }

    @Test
    void assertThatFieldsAreEquals() {
        adress.setPays(pays);
        adress.setVille(ville);
        adress.setLongitude(longitude);
        adress.setLattitude(latitude);

        assertThat(adress).isNotNull()
                .satisfies(a -> assertEquals(pays, a.getPays()))
                .satisfies(a -> assertEquals(ville, a.getVille()))
                .satisfies(a -> assertEquals(longitude, a.getLongitude()))
                .satisfies(a -> assertEquals(latitude, a.getLattitude()));
    }
}
