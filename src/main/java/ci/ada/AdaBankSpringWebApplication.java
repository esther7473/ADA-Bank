package ci.ada;

import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@Slf4j
@SpringBootApplication
public class AdaBankSpringWebApplication {


    public static void main(String[] args) {
        SpringApplication.run(AdaBankSpringWebApplication.class, args);
    }

}
