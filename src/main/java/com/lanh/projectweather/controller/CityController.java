package com.lanh.projectweather.controller;

import com.lanh.projectweather.dto.city.CityDto;
import com.lanh.projectweather.dto.city.CityOriginDto;
import com.lanh.projectweather.entity.City;
import com.lanh.projectweather.mapper.CityMapper;
import com.lanh.projectweather.mapper.WeatherMapper;
import com.lanh.projectweather.model.CommonResponse;
import com.lanh.projectweather.service.CityService;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RestController
@RequestMapping("api/v1")
public class CityController {

    @Autowired
    CityMapper cityMapper;

    @Autowired
    WeatherMapper weatherMapper;
    @Autowired
    private CityService cityService;

    @GetMapping("/city/list")
    public ResponseEntity<List<CityDto>> listCity(){
        return ResponseEntity.ok(cityMapper.cityToListCityDto(cityService.list()));
    }

    @GetMapping("/city/search")
    public CommonResponse<CityDto> listCity(@RequestParam(value = "page", required = false) Integer page,
                                         @RequestParam(value = "size", required = false) Integer size,
                                         @RequestParam(value = "search", required = false) String search){
        Page<City> results = cityService.findByNameContaining(search, page, size);

        List<Integer> pages = null;
       int totalPages = results.getTotalPages();
            pages = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());


        CommonResponse<CityDto> commonResponse =new CommonResponse<>();
        commonResponse.setList(cityMapper.cityToListCityDto(results.getContent()));
        commonResponse.setPages(pages);

        return commonResponse;
    }


    @GetMapping("/city/{id}")
    public ResponseEntity<CityDto> getCity(@PathVariable("id") @NotNull Integer id){
        return ResponseEntity.ok(cityMapper.cityToCityDto(cityService.getById(id)));
    }

    @DeleteMapping("/city/{id}")
    public ResponseEntity<String> deleteCity(@PathVariable("id") @NotNull Integer id){
        cityService.deleteById(id);
        return ResponseEntity.status(HttpStatus.CREATED).body("Success");
    }

    @PostMapping("/city")
    public ResponseEntity<CityOriginDto> createCity(@RequestBody @Valid CityDto cityDto){
        City city = cityService.save(cityMapper.cityDtoToCity(cityDto));
        CityOriginDto cityOriginDto = cityMapper.cityToCityOriginDto(city);
        return ResponseEntity.status(HttpStatus.CREATED).body(cityOriginDto);
    }

    @PutMapping("/city/{id}")
    public ResponseEntity<CityDto> updateCityNoID(@PathVariable("id")  Integer id, @RequestBody @Valid CityDto cityDto){
        City city = cityService.getById(id);
        cityDto.setCityId(id);
        if(city!=null){
            cityService.save(cityMapper.cityDtoToCity(cityDto));
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(cityDto);
    }

    @PutMapping("/city")
    public ResponseEntity<CityDto> updateCity(@RequestBody @Valid CityDto cityDto){
        if(cityDto.getCityId()==null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        cityService.save(cityMapper.cityDtoToCity(cityDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(cityDto);
    }

}
