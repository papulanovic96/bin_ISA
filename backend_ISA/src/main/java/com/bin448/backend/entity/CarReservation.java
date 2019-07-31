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
    private String username;
    @Column(nullable = false)
    private String carREG;

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


    public String getCarREG() {
        return carREG;
    }

    public void setCarREG(String carREG) {
        this.carREG = carREG;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
