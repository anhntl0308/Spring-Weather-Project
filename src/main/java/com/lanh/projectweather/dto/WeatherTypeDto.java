package com.lanh.projectweather.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class WeatherTypeDto {

    private Integer weatherTypeId;
    private String weatherTypeName;

    public String getWeatherTypeName() {
        return weatherTypeName;
    }

    public void setWeatherTypeName(String weatherTypeName) {
        this.weatherTypeName = weatherTypeName;
    }

    public Integer getWeatherTypeId() {
        return weatherTypeId;
    }

    public void setWeatherTypeId(Integer weatherTypeId) {
        this.weatherTypeId = weatherTypeId;
    }
}
