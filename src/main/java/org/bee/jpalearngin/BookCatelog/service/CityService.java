package org.bee.jpalearngin.BookCatelog.service;

import java.util.List;
import org.bee.jpalearngin.BookCatelog.dto.requestDto.CityDTO;
import org.bee.jpalearngin.BookCatelog.entitt.City;
import org.springframework.stereotype.Service;

@Service
public interface CityService {

    public City addCity(CityDTO cityDTO);
    public List<City> getCities();
    public City getCity(Long cityId);
    public City deleteCity(Long cityId);
    public City updateCity(Long cityId,CityDTO cityDTO);
}
