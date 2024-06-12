package com.mtatgc.bookshelfms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// this annotation tells gradle that this class is spring boot class and autoconfiguration starts here
@SpringBootApplication
public class BookshelfmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookshelfmsApplication.class, args);
	}
}
