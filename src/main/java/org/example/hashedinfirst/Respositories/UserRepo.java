package org.example.hashedinfirst.Respositories;

import org.example.hashedinfirst.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;
@Repository
public interface UserRepo extends JpaRepository<User,Long> {

    Optional<User> findByUserName(String name);
}
