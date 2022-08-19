package com.lanh.projectweather.mapper;

import com.lanh.projectweather.dto.weather.WeatherDto;
import com.lanh.projectweather.entity.Weather;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;


@Mapper(componentModel = "spring")
public interface WeatherMapper {
    @Mapping(source = "weatherType", target = "weatherTypeDto")
    @Mapping(source = "city", target = "cityDto")
    WeatherDto weatherToWeatherDto(Weather weather);
    @Mapping(source = "weatherTypeDto", target = "weatherType")
    @Mapping(source = "cityDto", target = "city")
    Weather weatherDtoToWeather(WeatherDto weatherDto);

    List<WeatherDto> weatherToListWeatherDto(List<Weather> weathers);

}
