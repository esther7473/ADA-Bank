package ci.ada.models.entity;


import ci.ada.models.Auditing;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerEntity extends Auditing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrlPicture() {
        return urlPicture;
    }

    public void setUrlPicture(String urlPicture) {
        this.urlPicture = urlPicture;
    }

    public UserAccountEntity getUserAccountEntity() {
        return userAccountEntity;
    }

    public void setUserAccountEntity(UserAccountEntity userAccountEntity) {
        this.userAccountEntity = userAccountEntity;
    }

    public BankEntity getBankEntity() {
        return bankEntity;
    }

    public void setBankEntity(BankEntity bankEntity) {
        this.bankEntity = bankEntity;
    }

    public List<AccountEntity> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<AccountEntity> accounts) {
        this.accounts = accounts;
    }

    private String urlPicture;

    @OneToOne
    private UserAccountEntity userAccountEntity;


    @ManyToOne
    @JoinColumn(name = "bank_id")
    private BankEntity bankEntity;

    @OneToMany(mappedBy = "customer")
    private List<AccountEntity> accounts;

}

