package com.lanh.projectweather.dto.city;


import com.lanh.projectweather.dto.weather.WeatherOriginNoCityDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
@Getter
@Setter
@ToString
public class CityDto {
    private Integer cityId;

    private String cityName;
    private List<WeatherOriginNoCityDto> weatherOriginNoCityDtos;


}
