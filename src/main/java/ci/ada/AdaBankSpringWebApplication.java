package ci.ada;

import ci.ada.Repository.AccountRepository;
import ci.ada.models.enums.AccountStatus;
import ci.ada.models.enums.AccountType;
import ci.ada.models.enums.UserRole;
import ci.ada.services.*;
import ci.ada.services.dto.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;

@Slf4j
@SpringBootApplication
@RequiredArgsConstructor
public class AdaBankSpringWebApplication implements CommandLineRunner {

    private final AccountService accountService;
    private final UserAccountService userAccountService;
    private final CustomerService customerService;
    private final BankService bankService;
    private final UserFacade userFacade;


    @Override
    public void run(String... args) throws Exception {


    }

    public static void main(String[] args) {
        SpringApplication.run(AdaBankSpringWebApplication.class, args);
    }

}
