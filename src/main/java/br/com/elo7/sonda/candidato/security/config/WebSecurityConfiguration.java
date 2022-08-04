package br.com.elo7.sonda.candidato.security.config;

import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;



@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter{
	
	@Value("#{'${app.allowed.cors.origins}'.split(',')}")
    private String[] allowedCorsOrigins;
    
    @Override
    protected void configure(final HttpSecurity http) throws Exception {
		http.cors()
			.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
			.csrf().disable()
			.authorizeRequests()
			.antMatchers("/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll()
			.antMatchers("/planets/**", "/probes/**").authenticated()
			.and()
			.httpBasic();
	}
	
	@Bean
    CorsConfigurationSource corsConfigurationSource() {
        final CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList(StringUtils.stripAll(allowedCorsOrigins)));
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
	
}
