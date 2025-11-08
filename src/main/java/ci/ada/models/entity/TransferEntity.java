package ci.ada.models.entity;

import ci.ada.models.Transaction;
import jakarta.persistence.*;


@Entity
@Table(name = "transfer")

public class TransferEntity extends Transaction {


    public AccountEntity getSource() {
        return source;
    }

    public void setSource(AccountEntity source) {
        this.source = source;
    }

    public AccountEntity getDestination() {
        return destination;
    }

    public void setDestination(AccountEntity destination) {
        this.destination = destination;
    }

    @ManyToOne
    @JoinColumn(name = "sourceAccount_id")
    private AccountEntity source;

    @ManyToOne
    @JoinColumn(name = "destinationAccount_id")
    private AccountEntity destination;

}