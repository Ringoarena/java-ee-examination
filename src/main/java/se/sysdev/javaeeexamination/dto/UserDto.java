package se.sysdev.javaeeexamination.dto;

import se.sysdev.javaeeexamination.model.Role;

import java.util.Collection;

public class UserDto {
    private String name;
    private String password;
    private String email;
    private Collection<Role> roles;

    public UserDto(String name, String password, String email, Collection<Role> roles) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.roles = roles;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }
}
