package org.kcsmini2.ojeommo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class OjeomMoApplication {

	public static void main(String[] args) {
		SpringApplication.run(OjeomMoApplication.class, args);
	}

}
