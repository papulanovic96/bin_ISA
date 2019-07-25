package com.bin448.backend.entity.DTOentity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;

public class HotelDTO {

    private Long hotel_id;

    @NotBlank(message = "Name of hotel is required")
    private String name;

    @NotBlank(message = "Address of hotel is required")
    private String address;

    private String description;

    private HashMap<String, Double> menu;

    private List<Long> configuration;

    @NotNull(message = "TV field is required")
    private Boolean TV;

    @NotNull(message = "Wifi field is required")
    private Boolean WiFi;

    @NotNull(message = "Room service field is required")
    private Boolean roomService;

    @NotNull(message = "Restaurant field is required")
    private Boolean restaurant;

    @NotNull(message = "Pool field is required")
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

    public List<Long> getConfiguration() {
        return configuration;
    }

    public void setConfiguration(List<Long> configuration) {
        this.configuration = configuration;
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

    public Boolean getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Boolean restaurant) {
        this.restaurant = restaurant;
    }

    public Boolean getPool() {
        return pool;
    }

    public void setPool(Boolean pool) {
        this.pool = pool;
    }
}
