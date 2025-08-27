package ci.ada.models.entity;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.entry;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class BankEntityTest  {

    private final Long id = 2L;
    private final String name = "name";
    private final Integer Customers = 3;
    private final UserAccountEntity userAccountEntity = new UserAccountEntity();

    private  BankEntity bankEntity;

    @Test
    public void assertThatFieldsAreEquals(){
        bankEntity = new BankEntity();
        bankEntity.setId(id);
        bankEntity.setName(name);
        bankEntity.setCustomers(Customers);
        bankEntity.setAdmin(userAccountEntity);

        assertThat(bankEntity).satisfies(entity ->assertEquals(id,entity.getId()))
                .satisfies(entity ->assertEquals(name,entity.getName()))
                .satisfies(entity ->assertEquals(Customers,entity.getCustomers()))
                .satisfies(entity ->assertEquals(userAccountEntity,entity.getAdmin()));


    }


}