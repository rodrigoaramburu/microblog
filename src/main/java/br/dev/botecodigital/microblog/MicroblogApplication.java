package br.dev.botecodigital.microblog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication

public class MicroblogApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroblogApplication.class, args);
	}

}
