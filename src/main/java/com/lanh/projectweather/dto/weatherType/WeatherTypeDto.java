package com.lanh.projectweather.dto.weatherType;

import com.lanh.projectweather.dto.weather.WeatherDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class WeatherTypeDto {
    private Integer weatherTypeId;
    private String weatherTypeName;
    private List<WeatherDto> weatherDtos = new ArrayList<>() ;
}
