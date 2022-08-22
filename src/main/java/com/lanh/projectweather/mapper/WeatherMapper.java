package com.lanh.projectweather.mapper;

import com.lanh.projectweather.dto.weather.WeatherDto;
import com.lanh.projectweather.entity.Weather;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;


@Mapper(componentModel = "spring")
public interface WeatherMapper {
    @Mapping(source = "weatherStatus", target = "weatherTypes")
    @Mapping(source = "city", target = "cityDto")
    WeatherDto weatherToWeatherDto(Weather weather);

    @Mapping(source = "weatherTypes", target = "weatherStatus")
    @Mapping(source = "cityDto", target = "city")
    Weather weatherDtoToWeather(WeatherDto weatherDto);

    @Mapping(source = "weatherStatus", target = "weatherTypes")
    @Mapping(source = "city", target = "cityDto")
    List<WeatherDto> weatherToListWeatherDto(List<Weather> weathers);

}
