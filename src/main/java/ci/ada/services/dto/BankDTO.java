package ci.ada.services.dto;

import lombok.*;

import java.io.Serializable;


@NoArgsConstructor
@AllArgsConstructor
public class BankDTO extends AuditingDTO implements Serializable {

    private Long id;

    private String name;

    private Integer customers;

    private UserAccountDTO admin;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCustomers() {
        return customers;
    }

    public void setCustomers(Integer customers) {
        this.customers = customers;
    }

    public UserAccountDTO getAdmin() {
        return admin;
    }

    public void setAdmin(UserAccountDTO admin) {
        this.admin = admin;
    }
}
