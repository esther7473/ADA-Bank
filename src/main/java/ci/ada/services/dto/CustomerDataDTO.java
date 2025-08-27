package ci.ada.services.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CustomerDataDTO {

    private String slug;

    private String urlPicture;

    private BankDataDto bank;

    private List<AccountDataDTO> accounts;

    private UserDataDTO userAccountDTO;
}
