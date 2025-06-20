package org.example.JWTauthenticatinDemo;

import org.example.JWTauthenticatinDemo.Entities.User;
import org.example.JWTauthenticatinDemo.Entities.role;
import org.example.JWTauthenticatinDemo.Respositories.UserRepo;
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

            User user = new User();
            user.setUserName("Mahesh Chandra");
            user.setPassword(passwordEncoder.encode("123123"));
            user.setRole(role.valueOf("USER"));
            userRepo.save(user);
            System.out.println("New user created");


        User user2 = new User();
        user2.setUserName("Jaswanth");
        user2.setPassword(passwordEncoder.encode("1231234"));
        user2.setRole(role.valueOf("ADMIN"));
        userRepo.save(user2);
        System.out.println("New 2 user created");






    }
}
