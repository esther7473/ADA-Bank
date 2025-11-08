package ci.ada.utils;

import org.springframework.stereotype.Component;

import java.security.SecureRandom;

@Component
public final class AccountNumberGenerator {
    private static final SecureRandom random = new SecureRandom();
    private static final int ACCOUNT_LENGTH = 12;


    //private contructor
    private AccountNumberGenerator() {
    }

    public static String generateRandomNumber() {
        StringBuilder sb = new StringBuilder(ACCOUNT_LENGTH);
        for (int i = 0; i < ACCOUNT_LENGTH; i++) {
            int digit = random.nextInt(10);
            sb.append(digit);
        }
        return sb.toString();
    }
}
