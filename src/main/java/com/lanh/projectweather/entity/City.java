package com.lanh.projectweather.entity;

import com.fasterxml.jackson.annotation.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@Table(name="cities")

public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="city_id")
    private Integer cityId;

    @Column(name="city_name",columnDefinition="nvarchar(100) not null")
    private String cityName;

    @OneToMany(mappedBy = "city", fetch = FetchType.LAZY)
    private List<Weather> weathers;


}
