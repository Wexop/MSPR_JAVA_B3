package fr.mspr_java_b3.security;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;

@Configuration
@SecurityScheme(
        type = SecuritySchemeType.HTTP,
        name = "bearer",
        scheme = "bearer")
public class SpringdocConfig {
}
