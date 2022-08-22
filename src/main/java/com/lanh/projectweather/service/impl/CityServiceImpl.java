package com.lanh.projectweather.service.impl;

import com.lanh.projectweather.dto.city.CityDto;
import com.lanh.projectweather.entity.City;
import com.lanh.projectweather.entity.Weather;
import com.lanh.projectweather.exception.NotFoundException;
import com.lanh.projectweather.repository.CityRepository;
import com.lanh.projectweather.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CityServiceImpl implements CityService {

    @Autowired
    private CityRepository cityRepository;

    @Override
    public City save(City entity) {

        return cityRepository.save(entity);
    }

    @Override
    public City getById(Integer id) {
        return cityRepository.findById(id).orElseThrow(() -> new NotFoundException("City khong ton tai"));
    }

    @Override
    public List<City> list() {
        return cityRepository.findAll();
    }

    @Override
    public Page<City> list(Pageable pageable) {
        return  cityRepository.findAll(pageable);
    }

    @Override
    public void deleteById(Integer id) {
        City city = cityRepository.findById(id).orElseThrow(() -> new NotFoundException("City khong ton tai"));
        cityRepository.deleteById(id);
    }

    @Override
    public Page<City> findByNameContaining(String name, Pageable pageable) {

        Page<City> results = null;
        if (name == null ) {
            results = cityRepository.findAll(pageable);
        } else if (name!=null) {
            results = cityRepository.findByCityNameContaining(name, pageable);
        }
        return results;

    }

    @Override
    public Page<City> findByNameContaining(String name, Integer currentPage, Integer pageSize) {
        if(name==null) name = "";
        if(currentPage<=1){
            currentPage =1 ;
        }
        if(pageSize <=1){
            pageSize = 1 ;
        }

        Pageable pageable = PageRequest.of(currentPage - 1, pageSize,Sort.by("cityName").ascending());
        Page<City> results = cityRepository.findByCityNameContaining(name, pageable);
        return results;
    }


}
