package fr.iocean.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import fr.iocean.application.configuration.WebSecurityConfig;


@SpringBootApplication
@EnableJpaRepositories
public class FilRougeApplication {

	public static void main(String[] args) {
		SpringApplication.run(FilRougeApplication.class, args);
	}
}
