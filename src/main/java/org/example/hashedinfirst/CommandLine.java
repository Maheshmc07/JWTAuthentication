package org.example.hashedinfirst;

import org.example.hashedinfirst.Entities.User;
import org.example.hashedinfirst.Respositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component // Make this class discoverable by Spring
public class CommandLine implements CommandLineRunner {

    private final PasswordEncoder passwordEncoder;
    private final UserRepo userRepo;

    @Autowired
    public CommandLine(PasswordEncoder passwordEncoder, UserRepo userRepo) {
        this.passwordEncoder = passwordEncoder;
        this.userRepo = userRepo;
    }

    @Override
    public void run(String... args) throws Exception {
        if (userRepo.findByUserName("Mahesh Chandra").isEmpty()) {
            User user = new User();
            user.setUserName("Mahesh Chandra");
            user.setPassword(passwordEncoder.encode("123123"));
            user.setRole("USER");
            userRepo.save(user);
            System.out.println("New user created");
        } else {
            System.out.println("User already exists");
        }
    }
}
