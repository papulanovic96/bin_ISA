package com.bin448.backend.entity.DTOentity;

import javax.validation.constraints.NotBlank;
import java.util.HashMap;

public class HotelDTO {

    private Long hotel_id;

    @NotBlank(message = "Name of hotel is required")
    private String name;

    @NotBlank(message = "Address of hotel is required")
    private String address;

    private String description;

    private HashMap<String, Double> menu;

    private Double avgGrade;

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

    public Double getAvgGrade() {
        return avgGrade;
    }

    public void setAvgGrade(Double avgGrade) {
        this.avgGrade = avgGrade;
    }
}
