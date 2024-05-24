package com.one.literAlura;

import com.one.literAlura.main.Main;
import com.one.literAlura.service.AutorService;
import com.one.literAlura.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiterAluraApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(LiterAluraApplication.class, args);
	}

	@Autowired
	private LivroService service;

	@Autowired
	private AutorService autorService;

	@Override
	public void run(String... args) throws Exception {
		Main main = new Main(service, autorService);
		main.menu();
	}
}
