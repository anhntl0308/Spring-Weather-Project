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
    private List<WeatherOriginDto> weatherOriginDtos;

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

    public List<WeatherOriginDto> getWeatherOriginDtos() {
        return weatherOriginDtos;
    }

    public void setWeatherOriginDtos(List<WeatherOriginDto> weatherOriginDtos) {
        this.weatherOriginDtos = weatherOriginDtos;
    }
}
