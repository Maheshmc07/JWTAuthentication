package org.example.JWTauthenticatinDemo.Entities;


import java.util.HashSet;
import java.util.Set;

public enum role {

    ADMIN(Set.of(Permission.READ,Permission.DELETE,Permission.WRITE,Permission.UPDATE)),
    USER(Set.of(Permission.READ,Permission.DELETE)),
    GUEST(Set.of(Permission.READ));
    Set<Permission> permissionSet = new HashSet<>();

    public Set<Permission> getPermissionSet() {
        return permissionSet;
    }

    role(Set<Permission> permissionSet) {
        this.permissionSet = permissionSet;
    }
}
