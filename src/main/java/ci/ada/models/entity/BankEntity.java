package ci.ada.models.entity;

import ci.ada.models.Auditing;
import jakarta.persistence.*;


@Entity


public class BankEntity extends Auditing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

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

    public UserAccountEntity getAdmin() {
        return admin;
    }

    public void setAdmin(UserAccountEntity admin) {
        this.admin = admin;
    }

    private Integer customers;

    @OneToOne
    @JoinColumn(name = "admin_id")
    private UserAccountEntity admin;



}
