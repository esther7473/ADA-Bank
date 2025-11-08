package ci.ada.services.dto;
import lombok.*;


@NoArgsConstructor
//@Builder
@AllArgsConstructor
public class TransferDTO extends TransactionDTO{

    private Long id;

    private AccountDTO source;

    private AccountDTO destination;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AccountDTO getSource() {
        return source;
    }

    public void setSource(AccountDTO source) {
        this.source = source;
    }

    public AccountDTO getDestination() {
        return destination;
    }

    public void setDestination(AccountDTO destination) {
        this.destination = destination;
    }
}
