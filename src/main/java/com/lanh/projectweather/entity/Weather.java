package com.lanh.projectweather.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name="weathers")
public class Weather {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer weatherId;

    @Column(name="temp")
    private int weatherTemp;

    @Column(name="temp_max")
    private int weatherTempMax;

    @Column(name="temp_min")
    private int weatherTempMin;

    @Column(name="date")
    private Date weatherDate;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    @ManyToOne
    @JoinColumn(name = "weatherType_id")
    private WeatherType weatherType;

    public Integer getWeatherId() {
        return weatherId;
    }

    public void setWeatherId(Integer weatherId) {
        this.weatherId = weatherId;
    }

    public int getWeatherTemp() {
        return weatherTemp;
    }

    public void setWeatherTemp(int weatherTemp) {
        this.weatherTemp = weatherTemp;
    }

    public int getWeatherTempMax() {
        return weatherTempMax;
    }

    public void setWeatherTempMax(int weatherTempMax) {
        this.weatherTempMax = weatherTempMax;
    }

    public int getWeatherTempMin() {
        return weatherTempMin;
    }

    public void setWeatherTempMin(int weatherTempMin) {
        this.weatherTempMin = weatherTempMin;
    }

    public Date getWeatherDate() {
        return weatherDate;
    }

    public void setWeatherDate(Date weatherDate) {
        this.weatherDate = weatherDate;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public WeatherType getWeatherType() {
        return weatherType;
    }

    public void setWeatherType(WeatherType weatherType) {
        this.weatherType = weatherType;
    }

    @Override
    public String toString() {
        return "Weather{" +
                "weatherId=" + weatherId +
                ", weatherTemp=" + weatherTemp +
                ", weatherTempMax=" + weatherTempMax +
                ", weatherTempMin=" + weatherTempMin +
                ", weatherDate=" + weatherDate +
                ", city=" + city +
                ", weatherType=" + weatherType +
                '}';
    }
}
