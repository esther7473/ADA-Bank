package ci.ada.services.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BankDataDto {

    private String name;

    private Integer customersSize;

    private UserDataDTO admin;

    private List<CustomerDataDTO> customers;
}
