package com.aakash.personal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class Game implements CommandLineRunner {

	@Autowired
	GameService service;

	public static void main(String[] args) {
		SpringApplication.run(Game.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		service.startup();
	}
}
