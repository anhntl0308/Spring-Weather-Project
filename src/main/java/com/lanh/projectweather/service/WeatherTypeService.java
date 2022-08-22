package com.lanh.projectweather.service;

import com.lanh.projectweather.dto.weatherType.WeatherTypeDto;
import com.lanh.projectweather.entity.WeatherType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface WeatherTypeService {

    List<WeatherType> findAll();
   WeatherType save(WeatherType entity);
   WeatherType getById(Integer id);
    Page<WeatherType> findAll(Pageable pageable);
    void deleteById(Integer id);
    void checkValid(WeatherTypeDto weatherTypeDto);

    Page<WeatherType> findByNameContaining(String search, Pageable pageable);
}
