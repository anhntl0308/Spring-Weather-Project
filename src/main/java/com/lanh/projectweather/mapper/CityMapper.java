package com.lanh.projectweather.mapper;

import com.lanh.projectweather.dto.city.CityDto;
import com.lanh.projectweather.dto.weather.WeatherDto;
import com.lanh.projectweather.entity.City;
import com.lanh.projectweather.entity.Weather;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
@Mapper(componentModel = "spring")
public interface CityMapper {
    @Mapping(source = "weatherDtos", target = "weathers")
    City cityDtoToCity(CityDto CityDto);

    @Mapping(source = "weathers", target = "weatherDtos")
    CityDto cityToCityDto(City city);

    List<CityDto> cityToListCityDto(List<City> cities);
    @Mapping(source = "weatherType", target = "weatherTypeDto")
    WeatherDto weatherToWeatherDto(Weather weather);


}
