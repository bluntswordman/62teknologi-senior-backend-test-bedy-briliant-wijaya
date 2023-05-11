package com.bedevenamdua;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "62 Teknologi Senior Backend",
                version = "1.0.0",
                description = "Project Test 62 Teknologi - Senior Backend",
                contact = @Contact(
                        name = "Bedy Briliant Wijaya",
                        email = "wijaya.bedybriliant@gmail.com"
                )
        )
)
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}
