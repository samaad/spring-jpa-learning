package org.bee.jpalearngin.BookCatelog.service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import lombok.RequiredArgsConstructor;
import org.bee.jpalearngin.BookCatelog.dto.requestDto.ZipcodeDTO;
import org.bee.jpalearngin.BookCatelog.entitt.City;
import org.bee.jpalearngin.BookCatelog.entitt.Zipcode;
import org.bee.jpalearngin.BookCatelog.repository.ZipcodeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ZipcodeServiceImpl implements ZipcodeService {

    private final ZipcodeRepository zipcodeRepository;
    private final CityService cityService;

    @Override public Zipcode addZipcode(ZipcodeDTO zipcodeDTO) {
        Zipcode zipcode = new Zipcode();
        zipcode.setName(zipcodeDTO.getName());
        if(zipcodeDTO.getCityId() == null){
            return zipcodeRepository.save(zipcode);
        }

        City city = cityService.getCity(zipcodeDTO.getCityId());
        zipcode.setCity(city);
        return zipcodeRepository.save(zipcode);
    }

    @Override public List<Zipcode> getZipcodes() {
        return StreamSupport.stream(zipcodeRepository.findAll().spliterator(), false)
            .collect(Collectors.toList());
    }

    @Override public Zipcode getZipcode(Long zipcodeId) {
        return zipcodeRepository.findById(zipcodeId).orElseThrow(() ->
            new IllegalArgumentException("ZipCode with id: " + zipcodeId + " could not found"));
    }

    @Override public Zipcode deleteZipcode(Long zipcodeId) {
        Zipcode zipcode = getZipcode(zipcodeId);
        zipcodeRepository.delete(zipcode);
        return zipcode;
    }

    @Transactional
    @Override public Zipcode editZipcode(Long zipcodeId, ZipcodeDTO zipcodeDTO) {
        Zipcode zipcodetoEdit = getZipcode(zipcodeId);
        zipcodetoEdit.setName(zipcodeDTO.getName());
        if(zipcodeDTO.getCityId() != null ){
            return zipcodetoEdit;
        }
        City city = cityService.getCity(zipcodeDTO.getCityId());
        zipcodetoEdit.setCity(city);
        return zipcodetoEdit;
    }

    @Transactional
    @Override public Zipcode addCityToZipcode(Long zipcodeId, Long cityId) {
        Zipcode zipcode = getZipcode(zipcodeId);
        City city = cityService.getCity(cityId);
        if(Objects.nonNull(zipcode.getCity())){
            throw new IllegalStateException("zipcode already has a city");
        }
        zipcode.setCity(city);
        return zipcode;
    }

    @Transactional
    @Override public Zipcode removeCityToZipcode(Long zipcodeId) {
        Zipcode zipcode = getZipcode(zipcodeId);
        if(!Objects.nonNull(zipcode.getCity())){
            throw new IllegalStateException("zipcode does not have a city");
        }
        zipcode.setCity(null);
        return zipcode;
    }
}
