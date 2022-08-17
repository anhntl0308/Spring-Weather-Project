package com.lanh.projectweather.repository;

import com.lanh.projectweather.entity.Weather;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface WeatherRepository extends JpaRepository<Weather, Integer> {
//    @Query(value = "select weathers.*, cities.*, w from weathers LEFT JOIN cities on weathers.city_id = cities.city_id LEFT JOIN weather_types on weathers.weather_type_id = weather_types.weather_type_id\n" +
//            "        wHERE( ( weathers.city_id = :city_id_pa ) and ( weathers.weather_type_id = :weather_id_pa ))", nativeQuery = true)
    Page<Weather> findWeatherByCity_CityIdAndAndWeatherType_WeatherTypeId(Integer cityId,Integer weatherTypeId, Pageable pageable);

}
