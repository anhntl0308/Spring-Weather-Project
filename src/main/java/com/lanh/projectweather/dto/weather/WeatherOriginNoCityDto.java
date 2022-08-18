package com.lanh.projectweather.dto.weather;

import com.lanh.projectweather.dto.weatherType.WeatherTypeOriginDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;

@Getter
@Setter
@ToString
public class WeatherOriginNoCityDto {
    private Integer weatherId;
    private int weatherTemp;
    private int weatherTempMax;
    private int weatherTempMin;
    private Date weatherDate;
    private WeatherTypeOriginDto weatherTypeOriginDto;

}