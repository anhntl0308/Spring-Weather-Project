package com.lanh.projectweather.service;

import com.lanh.projectweather.entity.Weather;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface WeatherService {
     void save(Weather entity);
     Weather getById(Integer id);
     List<Weather> findAll();
     Page<Weather> findAll(Pageable pageable);
     void deleteById(Integer id);

     Page<Weather> findWeatherByCitAndAndWeatherType(Integer city, Integer type, Integer currentPage, Integer pageSize);
}
