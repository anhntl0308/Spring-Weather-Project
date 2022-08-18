package com.lanh.projectweather.mapper;

import com.lanh.projectweather.dto.city.CityDto;
import com.lanh.projectweather.dto.city.CityOriginDto;
import com.lanh.projectweather.dto.weather.WeatherOriginNoCityDto;
import com.lanh.projectweather.dto.weatherType.WeatherTypeDto;
import com.lanh.projectweather.entity.City;
import com.lanh.projectweather.entity.Weather;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
@Mapper(componentModel = "spring")
public interface CityMapper {
    @Mapping(source = "weatherOriginNoCityDtos", target = "weathers")
    City cityDtoToCity(CityDto CityDto);

    @Mapping(source = "weathers", target = "weatherOriginNoCityDtos")
    CityDto cityToCityDto(City city);

    CityOriginDto cityToCityOriginDto(City city);
    List<CityDto> cityToListCityDto(List<City> cities);
    @Mapping(source = "weatherType", target = "weatherTypeOriginDto")
    WeatherOriginNoCityDto weatherToWeatherOriginNoCityDt(Weather weather);


}
