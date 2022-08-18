package com.lanh.projectweather.mapper;

import com.lanh.projectweather.dto.weather.WeatherDto;
import com.lanh.projectweather.entity.Weather;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;


@Mapper(componentModel = "spring")
public interface WeatherMapper {
    @Mapping(source = "weatherType", target = "weatherTypeOriginDto")
    @Mapping(source = "city", target = "cityOriginDto")
    WeatherDto weatherToWeatherDto(Weather weather);
    @Mapping(source = "weatherTypeOriginDto", target = "weatherType")
    @Mapping(source = "cityOriginDto", target = "city")
    Weather weatherDtoToWeather(WeatherDto weatherDto);

    List<WeatherDto> weatherToListWeatherDto(List<Weather> weathers);

}
