package com.lanh.projectweather.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Getter
@Setter
@ToString
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
    @JsonBackReference
    private City city;
    @ManyToOne
    @JoinColumn(name = "weather_type_id")
    @JsonBackReference
    private WeatherType weatherType;


}
