package com.azarenka;

import com.azarenka.javafx.ApplicationStarter;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Starts application.
 */
@SpringBootApplication
@ComponentScan("com.azarenka")
public class Main extends ApplicationStarter {

    public static void main(String[] args) {
        startApplication(args, Main.class);
    }
}