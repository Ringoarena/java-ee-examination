package se.sysdev.javaeeexamination.model;

import se.sysdev.javaeeexamination.dto.UserDto;

import javax.persistence.*;
import java.util.Collection;
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
    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private Collection<Role> roles;

    public User() {
    }

    public User(String name, String password, String email, List<Address> addresses, Collection<Role> roles) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.addresses = addresses;
        this.roles = roles;
    }

    public User(UserDto userDto) {
        this.name = userDto.getName();
        this.password = userDto.getPassword();
        this.email = userDto.getEmail();
        this.roles = userDto.getRoles();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }
}
