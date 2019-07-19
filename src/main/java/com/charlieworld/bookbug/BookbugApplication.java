package com.charlieworld.bookbug;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class BookbugApplication {

	@RestController
	public class Ping {
		@RequestMapping("/ping")
		public String ping() {
			return "pong";
		}
	}

	public static void main(String[] args) {
		SpringApplication.run(BookbugApplication.class, args);
	}

}
