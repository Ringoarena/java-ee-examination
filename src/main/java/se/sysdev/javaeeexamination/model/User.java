package se.sysdev.javaeeexamination.model;

import se.sysdev.javaeeexamination.security.UserRole;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String password;
    @Column(unique = true)
    private String email;
    @ManyToMany(cascade = CascadeType.PERSIST)
    private List<Address> addresses;
    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    public User() {
    }

    public User(String name, String password, String email, List<Address> addresses, UserRole userRole) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.addresses = addresses;
        this.userRole = userRole;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public UserRole getUserRole() {
        return userRole;
    }
}
