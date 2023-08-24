package com.zukeramrenzo.workshopmongo.config;

import com.zukeramrenzo.workshopmongo.domain.User;
import com.zukeramrenzo.workshopmongo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Instantiation implements CommandLineRunner {
    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        userRepository.deleteAll();

        userRepository.save(new User(null, "Yoshio Takahashi", "yoshio.taka@gmail.com"));
        userRepository.save(new User(null, "Hirohito", "hiro@gmail.com"));
        userRepository.save(new User(null, "Kasumi Yamaguchi", "hiro@gmail.com"));
    }
}
