package org.example.JWTauthenticatinDemo;


import org.example.JWTauthenticatinDemo.Entities.Post;
import org.example.JWTauthenticatinDemo.Respositories.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/v1")
public class Controller {


    @Autowired
    private PostRepo postRepo;

    @PostMapping("/addPost")
    public ResponseEntity<?> addPost(@RequestBody Post post) {
        postRepo.save(post);
        return ResponseEntity.ok().build();


    }


    @GetMapping("/getPost/{id}")
    @Cacheable("posts")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PostAuthorize("returnObject != null && returnObject.owner == authentication.name")
    public Post getPost(@PathVariable long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();

        Post post = postRepo.findById(id).orElse(null);

        if (post != null) {
            System.out.println("POST OWNER: " + post.getOwner());
        }
        System.out.println("AUTHENTICATED USER: " + currentUsername);

        return post;
    }






    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    @GetMapping("/print")
    public String print() {
        return "Hello World";
    }
@GetMapping("/admin/user")
@PreAuthorize("hasAnyRole('ADMIN','USER') and hasAnyAuthority('READ', 'WRITE')")
    public String AdminUser() {
        return "Admin Printed and USER Printed";
    }

   @GetMapping("/guest")
    public String guestPrint() {
        return "Guest Printed";
    }
    @GetMapping("/user")
    @PreAuthorize("hasAnyRole('USER') and hasAnyAuthority('UPDATE')")

    public String UserPrint() {
        return "USER Printed";
    }


    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/onlyAdmin")
    public String onlyAdmin(){

        return "Only Admin Printed";
    }



}
