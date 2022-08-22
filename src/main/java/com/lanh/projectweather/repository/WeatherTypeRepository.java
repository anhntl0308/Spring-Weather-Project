package com.lanh.projectweather.repository;

import com.lanh.projectweather.entity.WeatherType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeatherTypeRepository extends JpaRepository<WeatherType, Integer> {
    Page<WeatherType> findByWeatherTypeNameContaining(String search, Pageable pageable);
}
