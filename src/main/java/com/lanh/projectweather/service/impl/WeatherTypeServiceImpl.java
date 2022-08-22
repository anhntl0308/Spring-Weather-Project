package com.lanh.projectweather.service.impl;

import com.lanh.projectweather.dto.weatherType.WeatherTypeDto;
import com.lanh.projectweather.entity.City;
import com.lanh.projectweather.entity.WeatherType;
import com.lanh.projectweather.exception.NotFoundException;
import com.lanh.projectweather.repository.WeatherTypeRepository;
import com.lanh.projectweather.service.WeatherTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class WeatherTypeServiceImpl implements WeatherTypeService {

    private final WeatherTypeRepository weatherTypeRepository;
    @Override
    public WeatherType save(WeatherType entity) {
        return weatherTypeRepository.save(entity) ;
    }

    @Override
    public WeatherType getById(Integer id) {
        return weatherTypeRepository.findById(id).get();
    }

    @Override
    public List<WeatherType> findAll() {
        return weatherTypeRepository.findAll();
    }

    @Override
    public Page<WeatherType> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public void deleteById(Integer id) {
        WeatherType weatherType = weatherTypeRepository.findById(id).orElseThrow(() -> new NotFoundException("Weather Type khong ton tai"));
        weatherTypeRepository.deleteById(id);
    }

    @Override
    public void checkValid(WeatherTypeDto weatherTypeDto) {

    }

    @Override
    public Page<WeatherType> findByNameContaining(String search, Pageable pageable) {
        Page<WeatherType> results = null;
        if (search == null ) {
            results = weatherTypeRepository.findAll(pageable);
        } else if (search!=null) {
            results = weatherTypeRepository.findByWeatherTypeNameContaining(search, pageable);
        }
        return results;
    }
}
