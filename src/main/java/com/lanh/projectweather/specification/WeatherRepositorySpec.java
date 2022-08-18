package com.lanh.projectweather.specification;

import com.lanh.projectweather.entity.Weather;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface WeatherRepositorySpec extends JpaRepository<Weather, Integer>, JpaSpecificationExecutor<Weather> {
}
