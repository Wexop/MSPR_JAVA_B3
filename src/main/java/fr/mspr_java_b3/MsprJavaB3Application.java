package fr.mspr_java_b3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class MsprJavaB3Application {

    public static void main(String[] args) {
        SpringApplication.run(MsprJavaB3Application.class, args);
    }

}
