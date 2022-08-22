package com.lanh.projectweather.controller;

import com.lanh.projectweather.dto.city.CityDto;
import com.lanh.projectweather.entity.City;
import com.lanh.projectweather.mapper.CityMapper;
import com.lanh.projectweather.mapper.WeatherMapper;
import com.lanh.projectweather.model.CommonResponse;
import com.lanh.projectweather.service.CityService;
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
@RequestMapping("api/v1/city")
public class CityController {
    @Autowired
    CityMapper cityMapper;

    @Autowired
    WeatherMapper weatherMapper;
    @Autowired
    private CityService cityService;

    @GetMapping("/list")
    public ResponseEntity<List<CityDto>> listCity() {
        return ResponseEntity.ok(cityMapper.cityToListCityDto(cityService.list()));
    }

    @GetMapping("")
    public CommonResponse<CityDto> listCity(@PageableDefault(value = 3, page = 0) Pageable pageable,
                                            @RequestParam(value = "search", required = false) String search) {
        Page<City> results = cityService.findByNameContaining(search, pageable);
        List<Integer> pages = IntStream.rangeClosed(1, results.getTotalPages()).boxed().collect(Collectors.toList());
        CommonResponse<CityDto> commonResponse = new CommonResponse<>();
        commonResponse.setList(cityMapper.cityToListCityDto(results.getContent()));
        commonResponse.setPages(pages);
        return commonResponse;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CityDto> getCity(@PathVariable("id") @NotNull Integer id) {
        return ResponseEntity.ok(cityMapper.cityToCityDto(cityService.getById(id)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCity(@PathVariable("id") @NotNull Integer id) {
        cityService.deleteById(id);
        return ResponseEntity.status(HttpStatus.CREATED).body("Success");
    }

    @PostMapping("")
    public ResponseEntity<CityDto> createCity(@RequestBody CityDto cityDto) {
        City city = cityService.save(cityMapper.cityDtoToCity(cityDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(cityMapper.cityToCityDto(city));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CityDto> updateCityNoID(@PathVariable("id") Integer id, @RequestBody @Valid CityDto cityDto) {
        City city = cityService.getById(id);
        cityDto.setCityId(id);
        cityService.save(cityMapper.cityDtoToCity(cityDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(cityDto);
    }
}
