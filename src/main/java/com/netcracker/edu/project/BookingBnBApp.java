package com.netcracker.edu.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Locale;

@SpringBootApplication
public class BookingBnBApp {
    public static void main(String[] args) {

        Locale.setDefault(Locale.ENGLISH);
        SpringApplication.run(BookingBnBApp.class, args);
    }
}
