package com.lanh.projectweather.service.impl;

import com.lanh.projectweather.dto.weather.WeatherDto;
import com.lanh.projectweather.entity.Weather;
import com.lanh.projectweather.exception.NotFoundException;
import com.lanh.projectweather.repository.CityRepository;
import com.lanh.projectweather.repository.WeatherRepository;
import com.lanh.projectweather.repository.WeatherTypeRepository;
import com.lanh.projectweather.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WeatherServiceImpl implements WeatherService {
    @Autowired
    WeatherRepository weatherRepository;
    @Autowired
    CityRepository cityRepository;
    @Autowired
    WeatherTypeRepository weatherTypeRepository;
    @Override
    public Weather save(Weather entity) {

        return weatherRepository.save(entity);
    }

    @Override
    public Weather getById(Integer id) {
        Weather weather = weatherRepository.findById(id).orElse(null);
        if (weather == null) {
            throw new NotFoundException(("Weather khong ton tai"));
        }
        return weather;
    }

    @Override
    public List<Weather> getByWeatherTypeId(Integer id) {
        return weatherRepository.findAll().stream().filter(i->i.equals(id)).collect(Collectors.toList());
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
    public Page<Weather> findWeatherByCityOrWeatherType(Integer city, Integer type, Pageable pageable) {
        Page<Weather> results = null;
        if (city == null && type == null) {
            results = weatherRepository.findAll(pageable);
        } else if (city != null && type != null) {
            results = weatherRepository.findWeatherByCity_CityIdAndWeatherType_WeatherTypeId(city, type, pageable);
        } else {
            results = weatherRepository.findWeatherByCity_CityIdOrOrWeatherType_WeatherTypeId(city, type, pageable);
        }
        return results;
    }

    @Override
    public void checkValid(WeatherDto weatherDto) {
//        cityRepository.findById(weatherDto.getCityOriginDto())
//                .orElseThrow(() -> new NotFoundException("Not Found City"));
//        weatherTypeRepository.findById(weatherDto.getWeatherTypeOriginDto().getWeatherTypeId())
//                .orElseThrow(() -> new NotFoundException("Not Found Weather Type"));
    }


}
