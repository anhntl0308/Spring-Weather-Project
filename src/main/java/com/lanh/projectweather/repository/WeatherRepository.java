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

//    @Query(value = "SELECT weathers.id, weathers.temp, weathers.temp_max, weathers.temp_min, weathers.date, weathers.city_id, weathers.weather_type_id FROM weather.weathers LEFT JOIN weather.cities on weathers.city_id = cities.city_id LEFT JOIN weather.weather_types on weathers.weather_type_id = weather_types.weather_type_id",nativeQuery = true)
//    Page<Weather> findWeatherByCityOrWeatherType(@Param("city") Integer city, @Param("type")Integer type, Pageable pageable);
}
