package ci.ada.models.entity;

import ci.ada.models.enums.UserRole;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class UserAccountEntityTest {

    private final Long id = 1L;
    private final String login = "auroreUser";
    private final String password = "securePass123";
    private final String nom = "Aurore";
    private final String prenom = "Doe";
    private final String email = "aurore.doe@example.com";
    private final String phone = "+22501020304";
    private final UserRole role = UserRole.ADMIN;

    private UserAccountEntity userAccountEntity;

    @BeforeEach
    void setUp() {
        userAccountEntity = new UserAccountEntity();
    }

    @Test
    void assertThatFieldsAreEquals() {
        userAccountEntity.setId(id);
        userAccountEntity.setLogin(login);
        userAccountEntity.setPassword(password);
        userAccountEntity.setFirstName(nom);
        userAccountEntity.setLastName(prenom);
        userAccountEntity.setEmail(email);
        userAccountEntity.setPhoneNumber(phone);
        userAccountEntity.setRole(role);

        assertThat(userAccountEntity).isNotNull()
                .satisfies(u -> assertEquals(id, u.getId()))
                .satisfies(u -> assertEquals(login, u.getLogin()))
                .satisfies(u -> assertEquals(password, u.getPassword()))
                .satisfies(u -> assertEquals(nom, u.getFirstName()))
                .satisfies(u -> assertEquals(prenom, u.getLastName()))
                .satisfies(u -> assertEquals(email, u.getEmail()))
                .satisfies(u -> assertEquals(phone, u.getPhoneNumber()))
                .satisfies(u -> assertEquals(role, u.getRole()));

        // Vérifier une autre instance pour cohérence
        UserAccountEntity other = new UserAccountEntity();
        other.setId(id);
        other.setLogin(login);
        other.setPassword(password);
        other.setFirstName(nom);
        other.setLastName(prenom);
        other.setEmail(email);
        other.setPhoneNumber(phone);
        other.setRole(role);

        assertThat(other).isNotNull()
                .satisfies(u -> assertEquals(id, u.getId()))
                .satisfies(u -> assertEquals(login, u.getLogin()))
                .satisfies(u -> assertEquals(password, u.getPassword()))
                .satisfies(u -> assertEquals(nom, u.getFirstName()))
                .satisfies(u -> assertEquals(prenom, u.getLastName()))
                .satisfies(u -> assertEquals(email, u.getEmail()))
                .satisfies(u -> assertEquals(phone, u.getPhoneNumber()))
                .satisfies(u -> assertEquals(role, u.getRole()));
    }
}
