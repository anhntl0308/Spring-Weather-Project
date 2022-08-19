package com.lanh.projectweather.dto.weather;


import com.lanh.projectweather.dto.city.CityDto;
import com.lanh.projectweather.dto.weatherType.WeatherTypeDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;


@Getter
@Setter
@ToString
public class WeatherDto {

    private Integer weatherId;
    private int weatherTemp;
    private int weatherTempMax;
    private int weatherTempMin;
    private Date weatherDate;
    private CityDto cityDto;
    private WeatherTypeDto weatherTypeDto;


}
