package com.example.stateapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StateapiApplication {

    public static void main(String[] args) {
        SpringApplication.run(StateapiApplication.class, args);
    }

}

//http://localhost:8080/state/NY
//curl.exe -X GET http://localhost:8080/state/NY