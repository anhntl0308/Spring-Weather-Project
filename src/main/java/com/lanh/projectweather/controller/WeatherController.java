package com.lanh.projectweather.controller;

import com.lanh.projectweather.dto.WeatherDto;
import com.lanh.projectweather.dto.WeatherTypeDto;
import com.lanh.projectweather.entity.City;
import com.lanh.projectweather.entity.Weather;
import com.lanh.projectweather.entity.WeatherType;
import com.lanh.projectweather.mapper.MapMapperStruct;
import com.lanh.projectweather.model.CommonResponse;
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
@RequestMapping("api/v1")
public class WeatherController {

    private final MapMapperStruct mapMapperStruct;

    public WeatherController(MapMapperStruct mapMapperStruct){
        this.mapMapperStruct = mapMapperStruct;
    }

    @Autowired
    public WeatherService weatherService;

    @GetMapping("/weather/list")
    public ResponseEntity<List<WeatherDto>> listCity(){
        return ResponseEntity.ok(mapMapperStruct.weatherToListWeatherDto(weatherService.findAll()));
    }

    @GetMapping("/weather/search")
    public CommonResponse<WeatherDto> listCity(@RequestParam(value = "page", defaultValue = "1", required = false) Integer page,
                                               @RequestParam(value = "size",defaultValue = "3", required = false) Integer size,
                                               @RequestParam(value = "city", required = false) Integer city,
                                               @RequestParam(value = "type", required = false) Integer type){

            Page<Weather> results = weatherService.findWeatherByCitAndAndWeatherType(city, type, page, size);
            if(results==null) throw new IndexOutOfBoundsException("Over pagination!");
            List<Integer> pages = null;
//            int totalPages = results.getTotalPages();
//            if (totalPages > 0) {
//                int start = Math.max(1, page - 2);
//                int end = Math.min(page + 2, totalPages);
//                if (totalPages > 5) {
//                    if (end == totalPages)
//                        start = end - 5;
//                    else if (start == 1)
//                        end = start + 5;
//                }
                pages = IntStream.rangeClosed(1, results.getTotalPages()).boxed().collect(Collectors.toList());
//            }

            CommonResponse<WeatherDto> commonResponse =new CommonResponse<>();
            commonResponse.setList(mapMapperStruct.weatherToListWeatherDto(results.getContent()));
            commonResponse.setPages(pages);



        return commonResponse;
    }


    @GetMapping("/weather/{id}")
    public ResponseEntity<WeatherDto> getCity(@PathVariable("id") @NotNull Integer id){
        return ResponseEntity.ok(mapMapperStruct.weatherToWeatherDto(weatherService.getById(id)));
    }


    @DeleteMapping("/weather/{id}")
    public ResponseEntity<String> deleteCity(@PathVariable("id") @NotNull Integer id){
        weatherService.deleteById(id);
        return ResponseEntity.status(HttpStatus.CREATED).body("Success");
    }

    @PostMapping("/weather")
    public ResponseEntity<WeatherDto> createCity(@RequestBody @Valid WeatherDto weatherDto){
        Weather weather = mapMapperStruct.weatherDtoToWeather(weatherDto);
        City city = new City();
        city.setCityId(weatherDto.getCityDto().getCityId());
        weather.setCity(city);
        WeatherType weatherType = new WeatherType();
        weatherType.setWeatherTypeId(weatherDto.getWeatherTypeDto().getWeatherTypeId());
        weather.setWeatherType(weatherType);
        weatherService.save(weather);
        return ResponseEntity.status(HttpStatus.CREATED).body(weatherDto);
    }

    @PutMapping("/weather")
    public ResponseEntity<WeatherDto> updateCity(@RequestBody @Valid WeatherDto weatherDto){
        if(weatherDto.getWeatherId()==null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        weatherService.save(mapMapperStruct.weatherDtoToWeather(weatherDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(weatherDto);
    }
}
