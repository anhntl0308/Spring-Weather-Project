package com.lanh.projectweather.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="weatherTypes")
public class WeatherType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="weatherType_id")
    private Integer weatherTypeId;

    @Column(name="weatherType_name",columnDefinition="nvarchar(100) not null")
    private String weatherTypeName;

    @OneToMany(mappedBy = "weatherType", cascade = CascadeType.ALL)
    private List<Weather> weathers;

    public Integer getWeatherTypeId() {
        return weatherTypeId;
    }

    public void setWeatherTypeId(Integer weatherTypeId) {
        this.weatherTypeId = weatherTypeId;
    }

    public String getWeatherTypeName() {
        return weatherTypeName;
    }

    public void setWeatherTypeName(String weatherTypeName) {
        this.weatherTypeName = weatherTypeName;
    }

    public List<Weather> getWeathers() {
        return weathers;
    }

    public void setWeathers(List<Weather> weathers) {
        this.weathers = weathers;
    }
}
