package se.sysdev.javaeeexamination.dto;

import se.sysdev.javaeeexamination.model.Address;

import java.util.List;

public class UserDto {
    private String name;
    private String password;
    private String email;
    private List<Address> addresses;

    public UserDto(String name, String password, String email, List<Address> addresses) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.addresses = addresses;
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

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }
}
