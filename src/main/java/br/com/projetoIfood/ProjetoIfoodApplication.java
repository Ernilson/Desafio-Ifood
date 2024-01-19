package br.com.projetoIfood;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"br.com.projetoIfood.config.aws", "br.com.projetoIfood.service", "br.com.projetoIfood.controller"})
public class ProjetoIfoodApplication {
	public static void main(String[] args) {
		SpringApplication.run(ProjetoIfoodApplication.class, args);
	}
}

