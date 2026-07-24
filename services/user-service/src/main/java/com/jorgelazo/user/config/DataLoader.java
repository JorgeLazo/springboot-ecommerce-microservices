package com.jorgelazo.user.config;

import com.jorgelazo.user.entity.User;
import com.jorgelazo.user.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final UserRepository repository;

    public DataLoader(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) {

        if (repository.count() == 0) {

            repository.save(new User(
                    null, "John Doe",
                    "john.doe@example.com"
            ));

            repository.save(new User(
                    null, "Jane Smith",
                    "jane.smith@example.com"
            ));
        }
    }
}