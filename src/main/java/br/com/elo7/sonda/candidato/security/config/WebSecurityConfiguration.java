package br.com.elo7.sonda.candidato.security.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter{
	
	@Override
    protected void configure(final HttpSecurity http) throws Exception {
		http.cors()
    		.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
    		.and()
    		.authorizeRequests()
    		.antMatchers("/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll()
    		.antMatchers("/planets/**", "/probes/**").authenticated();
    }
	
}
