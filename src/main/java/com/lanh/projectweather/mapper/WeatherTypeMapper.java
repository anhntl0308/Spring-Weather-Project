package com.lanh.projectweather.mapper;

import com.lanh.projectweather.dto.weather.WeatherDto;
import com.lanh.projectweather.dto.weatherType.WeatherTypeDto;
import com.lanh.projectweather.entity.Weather;
import com.lanh.projectweather.entity.WeatherType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;


@Mapper(componentModel = "spring")
public interface WeatherTypeMapper {

    List<WeatherTypeDto> weatherTypeToListWeatherTypeDto(List<WeatherType> weatherTypes);
    @Mapping(source = "weathers", target = "weatherDtos") //ok
    WeatherTypeDto weatherTypeToWeatherTypeDto(WeatherType weatherType);
    @Mapping(source = "weatherDtos", target = "weathers")
    WeatherType weatherTypeDtoToWeatherType(WeatherTypeDto weatherTypeDto);
    @Mapping(source = "city", target = "cityDto")
    WeatherDto weatherToWeatherDto(Weather weather);

    @Mapping(source = "cityDto", target = "city")
    Weather weatherDtoToWeather(WeatherDto weatherDto);
}
