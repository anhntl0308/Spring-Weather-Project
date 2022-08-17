package com.lanh.projectweather.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.lanh.projectweather.entity.Weather;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CityDto {
    private Integer cityId;

    private String cityName;
    private List<WeatherDto> weatherDtos;

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public List<WeatherDto> getWeatherDtos() {
        return weatherDtos;
    }

    public void setWeatherDtos(List<WeatherDto> weatherDtos) {
        this.weatherDtos = weatherDtos;
    }
}
