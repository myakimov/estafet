package com.estafet.induction.ev;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * Main application class
 * 
 * @version 1.0
 * @author Marin Yakimov
 */
@ComponentScan
@EnableAutoConfiguration
public class MainClass {

    public static void main(String[] args) {
        SpringApplication.run(MainClass.class, args);
    }
}
