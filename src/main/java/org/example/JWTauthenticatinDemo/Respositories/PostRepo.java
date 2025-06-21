package org.example.JWTauthenticatinDemo.Respositories;

import org.example.JWTauthenticatinDemo.Entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostRepo extends JpaRepository<Post, Long> {


    Optional<Post> findPostById(long id);

}
