package com.lanh.projectweather.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@Table(name="weatherTypes")
public class WeatherType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="weather_type_id")
    private Integer weatherTypeId;

    @Column(name="weather_type_name",columnDefinition="nvarchar(100) not null")
    private String weatherTypeName;

    @OneToMany(mappedBy = "weatherType", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Weather> weathers;


}
