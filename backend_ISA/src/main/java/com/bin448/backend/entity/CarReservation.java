package com.bin448.backend.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class CarReservation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long carResId;
    private Date startDate;
    private Date endDate;
    @Column(nullable = false)
    private Long userId;
    @Column(nullable = false)
    private Long carId;

    public Long getCarResId() {
        return carResId;
    }

    public void setCarResId(Long carResId) {
        this.carResId = carResId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }


    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
