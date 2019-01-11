package com.formation.ecommerce;

import java.util.HashSet;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.context.WebApplicationContext;

import com.formation.ecommerce.model.Panier;

@Configuration
@ComponentScan(basePackages = "com.formation.ecommerce")
public class ApplicationConfig {

	@Bean
	@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
	protected Panier initPanier() {
		Panier panier = new Panier();
		panier.setLignes(new HashSet<>());
		return panier;
	}
}
