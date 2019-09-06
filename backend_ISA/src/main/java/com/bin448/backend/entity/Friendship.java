package com.bin448.backend.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "friendship")
public class Friendship{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(referencedColumnName = "username")
    private User sender;
    @ManyToOne
    @JoinColumn(referencedColumnName = "username")
    private User receiver;
    @Column
    private boolean areFriends;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public boolean isAreFriends() {
        return areFriends;
    }

    public void setAreFriends(boolean areFriends) {
        this.areFriends = areFriends;
    }






}
