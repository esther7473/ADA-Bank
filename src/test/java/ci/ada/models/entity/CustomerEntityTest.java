package ci.ada.models.entity;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


class CustomerEntityTest {

    private final Long id = 1L;
    private final String url = "url";
    private final BankEntity bankEntity = new BankEntity();
    private final List<AccountEntity> accountEntities = new ArrayList<>();

    AccountEntity accountEntity1= AccountEntity.builder().id(1L).build();
    AccountEntity accountEntity2 =  AccountEntity.builder().id(2L).build();
    CustomerEntity customerEntity;
    @Test
    public void assertThatFieldsAreEquals() {
        accountEntities.add(accountEntity1);
        accountEntities.add(accountEntity2);

        customerEntity= new  CustomerEntity();
        customerEntity.setId(id);
        customerEntity.setBankEntity(bankEntity);
        customerEntity.setUrlPicture(url);
        customerEntity.setAccounts(accountEntities);

        assertThat(customerEntity).satisfies(entity -> assertEquals(id, entity.getId()))
                .satisfies(entity -> assertEquals(url, entity.getUrlPicture()))
                .satisfies(entity -> assertEquals(bankEntity, entity.getBankEntity()))
                .satisfies(entity ->assertEquals(id, entity.getId()))
                .satisfies(entity->assertEquals(accountEntities, entity.getAccounts()));



    }

}