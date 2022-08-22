package com.lanh.projectweather.controller;


import com.lanh.projectweather.dto.city.CityDto;
import com.lanh.projectweather.dto.weatherType.WeatherTypeDto;
import com.lanh.projectweather.entity.City;
import com.lanh.projectweather.entity.WeatherType;
import com.lanh.projectweather.mapper.CityMapper;
import com.lanh.projectweather.mapper.WeatherMapper;
import com.lanh.projectweather.mapper.WeatherTypeMapper;
import com.lanh.projectweather.model.CommonResponse;
import com.lanh.projectweather.service.WeatherService;
import com.lanh.projectweather.service.WeatherTypeService;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RestController
@RequestMapping("api/v1/weatherTypes")
public class WeatherTypeController {
    @Autowired
    CityMapper cityMapper;
    @Autowired
    WeatherMapper weatherMapper;
    @Autowired
    WeatherTypeMapper weatherTypeMapper;
    @Autowired
    public WeatherService weatherService;
    @Autowired
    public WeatherTypeService weatherTypeService;

    @RequestMapping("/list")
    public ResponseEntity<List<WeatherTypeDto>> listWeatherType() {
        return ResponseEntity.ok(weatherTypeMapper.weatherTypeToListWeatherTypeDto(weatherTypeService.findAll()));
    }

    @GetMapping("")
    public CommonResponse<WeatherTypeDto> listWeatherType(@PageableDefault(value = 3, page = 0) Pageable pageable,
                                            @RequestParam(value = "search", required = false) String search) {
        Page<WeatherType> results = weatherTypeService.findByNameContaining(search, pageable);
        List<Integer> pages = IntStream.rangeClosed(1, results.getTotalPages()).boxed().collect(Collectors.toList());
        CommonResponse<WeatherTypeDto> commonResponse = new CommonResponse<>();
        commonResponse.setList(weatherTypeMapper.weatherTypeToListWeatherTypeDto(results.getContent()));
        commonResponse.setPages(pages);
        return commonResponse;
    }

    @GetMapping("/{id}")
    public ResponseEntity<WeatherTypeDto> getWeatherType(@PathVariable("id") @NotNull Integer id) {
        return ResponseEntity.ok(weatherTypeMapper.weatherTypeToWeatherTypeDto(weatherTypeService.getById(id)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteWeatherType(@PathVariable("id") @NotNull Integer id) {
        weatherTypeService.deleteById(id);
        return ResponseEntity.status(HttpStatus.CREATED).body("Success");
    }

    @PostMapping("/add")
    public ResponseEntity<WeatherTypeDto> createWeatherType(@RequestBody WeatherTypeDto weatherTypeDto) {
                    WeatherType weatherType = weatherTypeService.save(weatherTypeMapper.weatherTypeDtoToWeatherType(weatherTypeDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(weatherTypeMapper.weatherTypeToWeatherTypeDto(weatherType));
    }

    @PutMapping("/{id}")
    public ResponseEntity<WeatherTypeDto> updateWeatherType(@PathVariable("id") Integer id, @RequestBody @Valid WeatherTypeDto weatherTypeDto) {
        WeatherType weatherType = weatherTypeService.getById(id);
        weatherTypeDto.setWeatherTypeId(id);
        weatherTypeService.save(weatherTypeMapper.weatherTypeDtoToWeatherType(weatherTypeDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(weatherTypeDto);
    }
}
