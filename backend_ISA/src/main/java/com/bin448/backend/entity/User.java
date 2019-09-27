package com.bin448.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "user",uniqueConstraints={@UniqueConstraint(columnNames={"username"})})

@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class User implements Serializable {

    @Column
    private String name;
    @Column
    private String lastName;
    @Column
    private String email;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(nullable = false, name = "username")
    private String username;
    @Column(nullable = false)
    private String password;
    @Column
    private String city;
    @Column
    private String telephone;
    @Column
    private String role;
    @Column
    private boolean active;
    @OneToMany(mappedBy = "sender", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Friendship> friends;
    @OneToMany(mappedBy = "receiver", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Friendship> requests;
    @OneToMany(mappedBy = "reservedBy", cascade = CascadeType.ALL)
    private List<PlaneTicket> reservedTicket;
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private PlaneSeat ps;
    @OneToMany(mappedBy = "inviting", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Invitation> poziva;
    @OneToMany(mappedBy = "receiving", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Invitation> prima;

    public User() {

    }

    public User(String username) {
        this.username = username;
    }

    public User(String name, String lastName, String email, String username, String password, String city, String telephone, String role, boolean active, List<Friendship> friends, List<Friendship> requests, List<PlaneTicket> reservedTicket) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.password = password;
        this.city = city;
        this.telephone = telephone;
        this.role = role;
        this.active = active;
        this.friends = friends;
        this.requests = requests;
        this.reservedTicket = reservedTicket;
    }

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

    public List<Friendship> getFriends() {
        return friends;
    }

    public void setFriends(List<Friendship> friends) {
        this.friends = friends;
    }

    public List<Friendship> getRequests() {
        return requests;
    }

    public void setRequests(List<Friendship> requests) {
        this.requests = requests;
    }

    public List<PlaneTicket> getReservedTicket() {
        return reservedTicket;
    }

    public void setReservedTicket(List<PlaneTicket> reservedTicket) {
        this.reservedTicket = reservedTicket;
    }

    public PlaneSeat getPs() {
        return ps;
    }

    public void setPs(PlaneSeat ps) {
        this.ps = ps;
    }

    public List<Invitation> getPoziva() {
        return poziva;
    }

    public void setPoziva(List<Invitation> poziva) {
        this.poziva = poziva;
    }

    public List<Invitation> getPrima() {
        return prima;
    }

    public void setPrima(List<Invitation> prima) {
        this.prima = prima;
    }
}
