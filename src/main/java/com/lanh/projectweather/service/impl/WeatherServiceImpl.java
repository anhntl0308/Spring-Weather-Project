package com.lanh.projectweather.service.impl;

import com.lanh.projectweather.entity.City;
import com.lanh.projectweather.entity.Weather;
import com.lanh.projectweather.exception.NotFoundException;
import com.lanh.projectweather.repository.WeatherRepository;
import com.lanh.projectweather.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WeatherServiceImpl implements WeatherService {
    @Autowired
    WeatherRepository weatherRepository;

    @Override
    public void save(Weather entity) {
        weatherRepository.save(entity);
    }

    @Override
    public Weather getById(Integer id) {
        Weather weather = weatherRepository.findById(id).orElse(null);
        if(weather == null) {
            throw  new NotFoundException(("Weather khong ton tai"));
        }
        return weather;
    }

    @Override
    public List<Weather> findAll() {
        return weatherRepository.findAll();
    }

    @Override
    public Page<Weather> findAll(Pageable pageable) {
        return weatherRepository.findAll(pageable);
    }

    @Override
    public void deleteById(Integer id) {
        weatherRepository.deleteById(id);
    }

    @Override
    public Page<Weather> findWeatherByCitAndAndWeatherType(Integer city, Integer type, Integer currentPage, Integer pageSize) {
        Pageable pageable = PageRequest.of(currentPage - 1, pageSize);
        Page<Weather> results =null;
        if(city==null && type ==null){
             results = weatherRepository.findAll(pageable);
        }else{
             results = weatherRepository.findWeatherByCity_CityIdAndAndWeatherType_WeatherTypeId(city, type, pageable);
        }
        return results;
    }
}
