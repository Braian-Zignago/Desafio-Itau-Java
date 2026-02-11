package dev.braianz.DesafioItau.Config;

import io.swagger.oas.models.OpenAPI;
import io.swagger.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfiguration {

    @Bean
    public OpenAPI apiInfo() {
        return new OpenAPI()
                .info(
                        new Info()
                                .title("Desafio ITAU")
                                .description("Api desafio tecnico do ITAU")
                                .version("1.0.0")
                );
    }

}