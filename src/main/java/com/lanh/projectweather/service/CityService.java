package com.lanh.projectweather.service;

import com.lanh.projectweather.entity.City;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


public interface CityService {
    void save(City entity);

    City getById(Integer id);

    List<City> list();
    Page<City> list(Pageable pageable);

    void deleteById(Integer id);
    Page<City> findByNameContaining(String name, Pageable pageable);

    Page<City> findByNameContaining(String name, Integer currentPage, Integer  pageSize);
}
