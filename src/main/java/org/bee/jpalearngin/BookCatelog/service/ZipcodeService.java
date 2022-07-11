package org.bee.jpalearngin.BookCatelog.service;

import java.util.List;
import org.bee.jpalearngin.BookCatelog.dto.requestDto.ZipcodeDTO;
import org.bee.jpalearngin.BookCatelog.entitt.Zipcode;
import org.springframework.stereotype.Service;

@Service
public interface ZipcodeService {
    public Zipcode addZipcode(ZipcodeDTO zipcodeDTO);
    public List<Zipcode> getZipcodes();
    public Zipcode getZipcode(Long zipcodeId);
    public Zipcode deleteZipcode(Long zipcodeId);
    public Zipcode editZipcode(Long zipcodeId, ZipcodeDTO zipcodeDTO);
    public Zipcode addCityToZipcode(Long zipcodeId, Long cityId);
    public Zipcode removeCityToZipcode(Long zipcodeId);

}
