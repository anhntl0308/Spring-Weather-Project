package com.lanh.projectweather.controller;

import com.lanh.projectweather.dto.weather.WeatherDto;
import com.lanh.projectweather.entity.City;
import com.lanh.projectweather.entity.Weather;
import com.lanh.projectweather.entity.WeatherType;
import com.lanh.projectweather.exception.NotFoundException;
import com.lanh.projectweather.mapper.CityMapper;
import com.lanh.projectweather.mapper.WeatherMapper;
import com.lanh.projectweather.model.CommonResponse;
import com.lanh.projectweather.repository.CityRepository;
import com.lanh.projectweather.repository.WeatherRepository;
import com.lanh.projectweather.repository.WeatherTypeRepository;
import com.lanh.projectweather.service.WeatherService;
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
@RequestMapping("api/v1/weather")
public class WeatherController {

    @Autowired
    CityMapper cityMapper;
    @Autowired
    WeatherMapper weatherMapper;
    @Autowired
    public WeatherService weatherService;
    @Autowired
    public WeatherRepository weatherRepository;
    @Autowired
    public WeatherTypeRepository weatherTypeRepository;
    @Autowired
    public CityRepository cityRepository;

    @GetMapping("/")
    public ResponseEntity<List<WeatherDto>> listWeather() {
        return ResponseEntity.ok(weatherMapper.weatherToListWeatherDto(weatherService.findAll()));
    }

    @GetMapping("")
    public CommonResponse<WeatherDto> listWeather(@PageableDefault(value = 3, page = 0) Pageable pageable,
                                                  @RequestParam(value = "city", required = false) Integer city,
                                                  @RequestParam(value = "type", required = false) Integer type) {
        Page<Weather> results = weatherService.findWeatherByCityOrWeatherType(city, type, pageable);
        if (results.getSize() == 0 ) throw new IndexOutOfBoundsException("Over pagination!");
        List<Integer> pages = IntStream.rangeClosed(1, results.getTotalPages()).boxed().collect(Collectors.toList());
        CommonResponse<WeatherDto> commonResponse = new CommonResponse<>();
        commonResponse.setList(weatherMapper.weatherToListWeatherDto(results.getContent()));
        commonResponse.setPages(pages);
        return commonResponse;
    }


    @GetMapping("/{id}")
    public ResponseEntity<WeatherDto> getWeather(@PathVariable("id") @NotNull Integer id) {
        return ResponseEntity.ok(weatherMapper.weatherToWeatherDto(weatherService.getById(id)));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteWeather(@PathVariable("id") @NotNull Integer id) {
        weatherService.deleteById(id);
        return ResponseEntity.status(HttpStatus.CREATED).body("Success");
    }

    @PostMapping("")
    public ResponseEntity<WeatherDto> createCity(@RequestBody WeatherDto weatherDto) {
        Weather weather = weatherMapper.weatherDtoToWeather(weatherDto);
         Weather weather1 = weatherService.save(weather);
        WeatherDto weatherDto1 = weatherMapper.weatherToWeatherDto(weatherService.getById(weather1.getWeatherId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(weatherDto1);
    }
    @PutMapping("/")
    public ResponseEntity<WeatherDto> updateWeather(@RequestBody @Valid WeatherDto weatherDto) {
        if (weatherDto.getWeatherId() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        weatherService.save(weatherMapper.weatherDtoToWeather(weatherDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(weatherDto);
    }
}
