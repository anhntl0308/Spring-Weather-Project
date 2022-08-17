package com.lanh.projectweather.mapper;

import com.lanh.projectweather.dto.CityDto;
import com.lanh.projectweather.dto.WeatherDto;
import com.lanh.projectweather.dto.WeatherTypeDto;
import com.lanh.projectweather.entity.City;
import com.lanh.projectweather.entity.Weather;
import com.lanh.projectweather.entity.WeatherType;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MapMapperStruct {

    City cityDtoToCity(CityDto CityDto);

    CityDto cityToCityDto(City city);

    List<CityDto> cityToListCityDto(List<City> cities);

    List<WeatherDto> weatherToListWeatherDto(List<Weather> weathers);

    WeatherDto weatherToWeatherDto(Weather weather);

    Weather weatherDtoToWeather(WeatherDto weatherDto);

    WeatherTypeDto weatherTypeToWeatherTypeDto(WeatherType weatherType);
}
