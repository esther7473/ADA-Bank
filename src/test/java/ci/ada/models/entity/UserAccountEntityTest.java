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
    private final String firstName = "Aurore";
    private final String lastName = "Doe";
    private final String email = "aurore.doe@example.com";
    private final String phoneNumber = "+22501020304";
    private final UserRole role = UserRole.ADMIN;

    private UserAccountEntity userAccountEntity;

    @BeforeEach
    void setUp() {
        userAccountEntity = new UserAccountEntity();
    }

    @Test
    void assertThatFieldsAreEquals() {
        // ✅ Setter / Getter test
        userAccountEntity.setId(id);
        userAccountEntity.setLogin(login);
        userAccountEntity.setPassword(password);
        userAccountEntity.setFirstName(firstName);
        userAccountEntity.setLastName(lastName);
        userAccountEntity.setEmail(email);
        userAccountEntity.setPhoneNumber(phoneNumber);
        userAccountEntity.setRole(role);

        assertThat(userAccountEntity).isNotNull()
                .satisfies(u -> assertEquals(id, u.getId()))
                .satisfies(u -> assertEquals(login, u.getLogin()))
                .satisfies(u -> assertEquals(password, u.getPassword()))
                .satisfies(u -> assertEquals(firstName, u.getFirstName()))
                .satisfies(u -> assertEquals(lastName, u.getLastName()))
                .satisfies(u -> assertEquals(email, u.getEmail()))
                .satisfies(u -> assertEquals(phoneNumber, u.getPhoneNumber()))
                .satisfies(u -> assertEquals(role, u.getRole()));

        // ✅ Vérifier une autre instance pour cohérence
        UserAccountEntity other = new UserAccountEntity();
        other.setId(id);
        other.setLogin(login);
        other.setPassword(password);
        other.setFirstName(firstName);
        other.setLastName(lastName);
        other.setEmail(email);
        other.setPhoneNumber(phoneNumber);
        other.setRole(role);

        assertThat(other).isNotNull()
                .satisfies(u -> assertEquals(id, u.getId()))
                .satisfies(u -> assertEquals(login, u.getLogin()))
                .satisfies(u -> assertEquals(password, u.getPassword()))
                .satisfies(u -> assertEquals(firstName, u.getFirstName()))
                .satisfies(u -> assertEquals(lastName, u.getLastName()))
                .satisfies(u -> assertEquals(email, u.getEmail()))
                .satisfies(u -> assertEquals(phoneNumber, u.getPhoneNumber()))
                .satisfies(u -> assertEquals(role, u.getRole()));
    }
}
