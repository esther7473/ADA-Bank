package ci.ada.services.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserToregisterDTO {
    private Long id;

    private String login;
    private String password;

    private String firstName;

    private String lastName;

    private String email;

    private String phoneNumber;
}
