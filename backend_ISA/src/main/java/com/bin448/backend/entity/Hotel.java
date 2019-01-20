package com.bin448.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashMap;

@Entity
@Table(name = "hotel")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long hotel_id;
    @Column
    private String name;
    @Column
    private String address;
    @Column
    private String description;
    @Column
    private HashMap<String, Double> menu;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "hotel", cascade = CascadeType.ALL)
    private Collection<Room> configuration;

    private Double avgGrade;
    private Boolean TV;
    private Boolean WiFi;
    private Boolean roomService;
    private Boolean restorant;
    private Boolean pool;

    public Long getHotel_id() {
        return hotel_id;
    }

    public void setHotel_id(Long hotel_id) {
        this.hotel_id = hotel_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public HashMap<String, Double> getMenu() {
        return menu;
    }

    public void setMenu(HashMap<String, Double> menu) {
        this.menu = menu;
    }

    public Collection<Room> getConfiguration() {
        return configuration;
    }

    public void setConfiguration(Collection<Room> configuration) {
        this.configuration = configuration;
    }

    public Double getAvgGrade() {
        return avgGrade;
    }

    public void setAvgGrade(Double avgGrade) {
        this.avgGrade = avgGrade;
    }

    public Boolean getTV() {
        return TV;
    }

    public void setTV(Boolean TV) {
        this.TV = TV;
    }

    public Boolean getWiFi() {
        return WiFi;
    }

    public void setWiFi(Boolean wiFi) {
        WiFi = wiFi;
    }

    public Boolean getRoomService() {
        return roomService;
    }

    public void setRoomService(Boolean roomService) {
        this.roomService = roomService;
    }

    public Boolean getRestorant() {
        return restorant;
    }

    public void setRestorant(Boolean restorant) {
        this.restorant = restorant;
    }

    public Boolean getPool() {
        return pool;
    }

    public void setPool(Boolean pool) {
        this.pool = pool;
    }
}
