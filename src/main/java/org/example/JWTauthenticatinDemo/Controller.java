package org.example.JWTauthenticatinDemo;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
public class Controller {

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
