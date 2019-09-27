package com.bin448.backend.entity.DTOentity;

import java.util.List;

public class UserDTO {
    private Long id;
    private String name;
    private String lastName;
    private String city;
    private String email;
    private String telephone;
    private String username;
    private String password;
    private String role;
    private boolean active;
    private List<String> usernameOfFriend;
    private List<String> usernameOfRequests;
    private List<Long> planeTicket;
    private boolean activeAdmin;
    private Long planeSeat;
    private List<Long> invites;
    private List<Long> receiveInivite;

    public UserDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public List<String> getUsernameOfFriend() {
        return usernameOfFriend;
    }

    public void setUsernameOfFriend(List<String> usernameOfFriend) {
        this.usernameOfFriend = usernameOfFriend;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<String> getUsernameOfRequests() {
        return usernameOfRequests;
    }

    public void setUsernameOfRequests(List<String> usernameOfRequests) {
        this.usernameOfRequests = usernameOfRequests;
    }

    public List<Long> getPlaneTicket() {
        return planeTicket;
    }

    public void setPlaneTicket(List<Long> planeTicket) {
        this.planeTicket = planeTicket;
    }

    public boolean isActiveAdmin() {
        return activeAdmin;
    }

    public void setActiveAdmin(boolean activeAdmin) {
        this.activeAdmin = activeAdmin;
    }

    public Long getPlaneSeat() {
        return planeSeat;
    }

    public void setPlaneSeat(Long planeSeat) {
        this.planeSeat = planeSeat;
    }

    public List<Long> getReceiveInivite() {
        return receiveInivite;
    }

    public void setReceiveInivite(List<Long> receiveInivite) {
        this.receiveInivite = receiveInivite;
    }

    public List<Long> getInvites() {
        return invites;
    }

    public void setInvites(List<Long> invites) {
        this.invites = invites;
    }
}
