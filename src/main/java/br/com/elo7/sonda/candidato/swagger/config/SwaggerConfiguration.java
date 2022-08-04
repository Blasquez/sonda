package br.com.elo7.sonda.candidato.swagger.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
@SecurityScheme(name="X-API-Key", type = SecuritySchemeType.APIKEY)
public class SwaggerConfiguration {

	@Value("${app.title}")
    private String title;
	
	@Bean
    public OpenAPI swaggerDocOpenApi(@Value("${app.description}") final String applicationDescription, @Value("${app.version}") final String applicationVersion) {
        return new OpenAPI()
                        .info(new Info().title(title)
                        .description(applicationDescription)
                        .version(applicationVersion));
    }
}
