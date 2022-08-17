package com.lanh.projectweather.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.validation.constraints.NotBlank;
import java.sql.Date;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class WeatherDto {

    private Integer weatherId;
    private int weatherTemp;

    private int weatherTempMax;
    private int weatherTempMin;
    private Date weatherDate;
    private CityDto cityDto;
    private WeatherTypeDto weatherTypeDto;

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

    public CityDto getCityDto() {
        return cityDto;
    }

    public void setCityDto(CityDto cityDto) {
        this.cityDto = cityDto;
    }

    public WeatherTypeDto getWeatherTypeDto() {
        return weatherTypeDto;
    }

    public void setWeatherTypeDto(WeatherTypeDto weatherTypeDto) {
        this.weatherTypeDto = weatherTypeDto;
    }
}
