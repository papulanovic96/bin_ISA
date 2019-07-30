package com.bin448.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "user",uniqueConstraints={@UniqueConstraint(columnNames={"username"})})

@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class User {
    @Column
    private String name;
    @Column
    private String lastName;
    @Column
    private String email;
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "id" , nullable = false)
    private Long id;
    @Column(nullable = false,name = "username")
    private String username;
    @Column(nullable = false)
    private String password;
    @Column
    private String city;
    @Column
    private String telephone;
    private String role;
    private boolean active;



    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
