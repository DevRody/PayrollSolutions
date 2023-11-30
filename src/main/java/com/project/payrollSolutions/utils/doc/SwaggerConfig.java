package com.project.payrollSolutions.utils.doc;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customSwagger() {
        return new OpenAPI()
                .info(new Info()
                        .title("API folha de pagamentos (Payroll)")
                        .description("API para cadastrar funcionários e suas respectivas folhas de pagamento")
                        .contact(new Contact().name("Arthur Santana").email("arthurgiannonisantana@yahoo.com.br")).version("1.0.0")
                );
    }
}