package com.lanh.projectweather.entity;

import com.fasterxml.jackson.annotation.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@Table(name="weathers")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class , property = "weatherId")
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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "city_id")
    private City city;

    @ManyToOne( fetch = FetchType.EAGER)
    @JoinColumn(name = "weather_type_id")
    private WeatherType weatherType;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name="weather_status",
            joinColumns = @JoinColumn(name="id"),
            inverseJoinColumns = @JoinColumn(name="weather_type_id")
    )
    private List<WeatherType> weatherStatus = new ArrayList<>();

}
