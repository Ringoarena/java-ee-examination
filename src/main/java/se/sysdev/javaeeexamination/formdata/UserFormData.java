package se.sysdev.javaeeexamination.formdata;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserFormData {
    @NotBlank(message = "Name is required")
    private String name;
    @Size(min = 3, message = "Password needs at least 3 characters")
    private String password;
    @Email(regexp = "^([a-z\\d\\.-]+)@([a-z\\d-]+)\\.([a-z]{2,8})(\\.[a-z]{2,8})?$", message = "Enter valid email")
    private String email;
    @NotBlank(message = "City is required")
    private String city;
    @NotBlank(message = "Street is required")
    private String street;

    public UserFormData() {
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }
}
