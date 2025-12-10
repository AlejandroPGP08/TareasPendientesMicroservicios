// Define el paquete donde se encuentra la clase
package com.example.TareasPendientes;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
// Anotación que marca esta clase como la clase principal de una aplicación Spring Boot
@SpringBootApplication
public class MicroServicio1 /*implements CommandLineRunner*/ {

    public static void main(String[] args) {
        // Inicia la aplicación Spring Boot con esta clase y los argumentos
        SpringApplication.run(MicroServicio1.class, args);
    }

    /*@Override
    public void run(String... args) throws Exception {
        // Código de inicialización si es necesario
    }*/
}