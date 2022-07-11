package org.bee.jpalearngin.BookCatelog.service;

import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.bee.jpalearngin.BookCatelog.dto.requestDto.CityDTO;
import org.bee.jpalearngin.BookCatelog.entitt.City;
import org.bee.jpalearngin.BookCatelog.repository.CiyRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CityServiceImpl implements CityService {

    private final CiyRepository cityRepository;

    @Override public City addCity(CityDTO cityDTO) {
        City city = City.builder()
            .name(cityDTO.getName()).build();
        return cityRepository.save(city);
    }

    @Override public List<City> getCities() {
        return new ArrayList<>(cityRepository.findAll());
    }

    @Override public City getCity(Long cityId) {
        return cityRepository.findById(cityId).orElseThrow(() -> new IllegalArgumentException("city not available "+ cityId));
    }

    @Override public City deleteCity(Long cityId) {
        City city = getCity(cityId);
        cityRepository.delete(city);
        return city;
    }

    @Transactional
    @Override public City updateCity(Long cityId, CityDTO cityDTO) {
        City citytoEdit = getCity(cityId);
        citytoEdit.setName(cityDTO.getName());
        cityRepository.save(citytoEdit);
        return citytoEdit;
    }
}
