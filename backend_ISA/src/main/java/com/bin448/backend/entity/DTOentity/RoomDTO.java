package com.bin448.backend.entity.DTOentity;

import javax.validation.constraints.NotNull;

public class RoomDTO {

    private Long number;

    @NotNull(message = "Hotel field is required")
    private Long hotelId;

    @NotNull(message = "Price per night required")
    private Double pricePerNight;

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public Long getHotelId() {
        return hotelId;
    }

    public void setHotelId(Long hotelId) {
        this.hotelId = hotelId;
    }

    public Double getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(Double pricePerNight) {
        this.pricePerNight = pricePerNight;
    }
}
