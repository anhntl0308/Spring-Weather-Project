package com.lanh.projectweather.controller;

import com.lanh.projectweather.dto.CityDto;
import com.lanh.projectweather.entity.City;
import com.lanh.projectweather.mapper.MapMapperStruct;
import com.lanh.projectweather.model.CommonResponse;
import com.lanh.projectweather.service.CityService;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RestController
@RequestMapping("api/v1")
public class CityController {

    private final MapMapperStruct mapMapperStruct;

    public CityController(MapMapperStruct mapMapperStruct){
        this.mapMapperStruct = mapMapperStruct;
    }

    @Autowired
    private CityService cityService;

    @GetMapping("/city/list")
    public ResponseEntity<List<CityDto>> listCity(){
        return ResponseEntity.ok(mapMapperStruct.cityToListCityDto(cityService.list()));
    }

    @GetMapping("/city/search")
    public CommonResponse<CityDto> listCity(@RequestParam(value = "page", required = false) Integer page,
                                         @RequestParam(value = "size", required = false) Integer size,
                                         @RequestParam(value = "search", required = false) String search){
        Page<City> results = cityService.findByNameContaining(search, page, size);

        List<Integer> pages = null;
       int totalPages = results.getTotalPages();
//        if (totalPages > 0) {
//            int start = Math.max(1, page - 2);
//            int end = Math.min(page + 2, totalPages);
//            if (totalPages > 5) {
//                if (end == totalPages)
//                    start = end - 5;
//                else if (start == 1)
//                    end = start + 5;
//            }
            pages = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
//        }

        CommonResponse<CityDto> commonResponse =new CommonResponse<>();
        commonResponse.setList(mapMapperStruct.cityToListCityDto(results.getContent()));
        commonResponse.setPages(pages);

        return commonResponse;
    }


    @GetMapping("/city/{id}")
    public ResponseEntity<CityDto> getCity(@PathVariable("id") @NotNull Integer id){
        return ResponseEntity.ok(mapMapperStruct.cityToCityDto(cityService.getById(id)));
    }

    @DeleteMapping("/city/{id}")
    public ResponseEntity<String> deleteCity(@PathVariable("id") @NotNull Integer id){
        cityService.deleteById(id);
        return ResponseEntity.status(HttpStatus.CREATED).body("Success");
    }

    @PostMapping("/city")
    public ResponseEntity<CityDto> createCity(@RequestBody @Valid CityDto cityDto){
        cityService.save(mapMapperStruct.cityDtoToCity(cityDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(cityDto);
    }

    @PutMapping("/city/{id}")
    public ResponseEntity<CityDto> updateCityNoID(@PathVariable("id")  Integer id, @RequestBody @Valid CityDto cityDto){
        City city = cityService.getById(id);
        cityDto.setCityId(id);
        if(city!=null){
            cityService.save(mapMapperStruct.cityDtoToCity(cityDto));
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(cityDto);
    }

    @PutMapping("/city")
    public ResponseEntity<CityDto> updateCity(@RequestBody @Valid CityDto cityDto){
        if(cityDto.getCityId()==null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        cityService.save(mapMapperStruct.cityDtoToCity(cityDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(cityDto);
    }

}
