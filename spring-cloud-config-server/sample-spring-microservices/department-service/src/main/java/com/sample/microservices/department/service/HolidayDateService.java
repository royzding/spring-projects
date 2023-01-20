package com.sample.microservices.department.service;

import java.util.List;

import com.sample.microservices.common.model.HolidayDate;
import com.sample.microservices.department.model.dto.HolidayDateDto;

public interface HolidayDateService {

    HolidayDate getHolidayDateById(final Long id);
    
    List<HolidayDate> getHolidayDateByName(final String name);
    
    List<HolidayDate> getAllHolidayDates();
    
    HolidayDate createHolidayDate(final HolidayDateDto holidayDateDto);
    
    List<HolidayDate> createHolidayDates(final List<HolidayDateDto> holidayDateDtos);
    
    void deleteHolidayDateById(final Long id);
    
    void deleteAllHolidayDates();
    
    void updateHolidayDate(final Long id, final HolidayDateDto holidayDateDto)  throws Exception;
    
    Long getCacheableTime();
}
