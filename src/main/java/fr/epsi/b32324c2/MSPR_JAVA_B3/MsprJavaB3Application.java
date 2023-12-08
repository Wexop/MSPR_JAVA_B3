package fr.epsi.b32324c2.MSPR_JAVA_B3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class MsprJavaB3Application {

	public static void main(String[] args) {
		SpringApplication.run(MsprJavaB3Application.class, args);
	}
	@GetMapping("/hello")
	public String hello(@RequestParam(value = "Matthieu", defaultValue = "World") String name) {
		return String.format("Hello %s!", name);
	}

}
