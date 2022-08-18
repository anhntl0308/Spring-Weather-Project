package com.lanh.projectweather.dto.weather;

import com.lanh.projectweather.entity.City;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;

@Getter
@Setter
@ToString
public class WeatherOriginNoTypeDto {
    private Integer weatherId;
    private int weatherTemp;
    private int weatherTempMax;
    private int weatherTempMin;
    private Date weatherDate;
    private City city;

}
