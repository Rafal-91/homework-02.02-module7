package com.repotracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class RepoTrackerApplication {

    public static void main(String[] args) {
        SpringApplication.run(RepoTrackerApplication.class, args);
    }
}
