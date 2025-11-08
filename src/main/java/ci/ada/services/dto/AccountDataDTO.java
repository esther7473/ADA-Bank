package ci.ada.services.dto;

import ci.ada.models.enums.AccountStatus;
import ci.ada.models.enums.AccountType;

import java.math.BigDecimal;

public class AccountDataDTO {
    private String slug;

    private String numberAccount;

    private BigDecimal balance;

    private AccountType accountType;

    private AccountStatus accountStatus;

}
