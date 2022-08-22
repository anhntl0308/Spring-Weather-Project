package com.lanh.projectweather.repository;

import com.lanh.projectweather.entity.Weather;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface WeatherRepository extends JpaRepository<Weather, Integer> {
    Page<Weather> findWeatherByCity_CityIdAndWeatherType_WeatherTypeId(Integer cityId,Integer weatherTypeId, Pageable pageable);
    Page<Weather> findWeatherByCity_CityIdOrOrWeatherType_WeatherTypeId(Integer cityId,Integer weatherTypeId, Pageable pageable);

}
