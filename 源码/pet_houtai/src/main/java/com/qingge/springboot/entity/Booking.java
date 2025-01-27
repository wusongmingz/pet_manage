package com.qingge.springboot.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String serviceTitle;
    private String timeRange;
    private String location;
    private String petInfo;

    // 构造函数、Getter 和 Setter 方法
    public Booking() {
    }

    public Booking(String serviceTitle,String name, String timeRange, String location, String petInfo) {
        this.serviceTitle = serviceTitle;
        this.name = name;
        this.timeRange = timeRange;
        this.location = location;
        this.petInfo = petInfo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getName(){
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getServiceTitle() {
        return serviceTitle;
    }

    public void setServiceTitle(String serviceTitle) {
        this.serviceTitle = serviceTitle;
    }

    public String getTimeRange() {
        return timeRange;
    }

    public void setTimeRange(String timeRange) {
        this.timeRange = timeRange;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPetInfo() {
        return petInfo;
    }

    public void setPetInfo(String petInfo) {
        this.petInfo = petInfo;
    }
}
