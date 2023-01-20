package com.sample.microservices.department.service;

import java.util.List;

import org.mapstruct.factory.Mappers;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StopWatch;

import com.sample.microservices.common.annotation.EventType;
import com.sample.microservices.common.annotation.Loggable;
import com.sample.microservices.common.annotation.Loggable.Level;
import com.sample.microservices.common.annotation.LoggableEvents;
import com.sample.microservices.common.annotation.LoggableType;
import com.sample.microservices.common.model.HolidayDate;
import com.sample.microservices.department.data.model.HolidayDateEntity;
import com.sample.microservices.department.map.HolidayDateMapper;
import com.sample.microservices.department.model.dto.HolidayDateDto;
import com.sample.microservices.department.repository.HolidayDateRepository;

@Service
@LoggableType
public class HolidayDateServiceImpl implements HolidayDateService {
	
	private final HolidayDateMapper mapper;
	private final HolidayDateRepository repository;
		
	public HolidayDateServiceImpl(HolidayDateMapper mapper, HolidayDateRepository repository) {
		this.mapper = Mappers.getMapper(HolidayDateMapper.class);
		this.repository = repository;
	}
	
	@Override
	public HolidayDate getHolidayDateById(final Long id) {
		
		return this.mapper.entityToHolidayDate(this.repository.findById(id).get());
	}

	@Override
	public List<HolidayDate> getHolidayDateByName(final String name) {
		
		return this.mapper.entityToHolidayDate(this.repository.getHolidayDateByName(name));
	}

	@Override
	@Cacheable("all-holidayDates")
	@Loggable()
	@LoggableEvents(type=EventType.READ)
	public List<HolidayDate> getAllHolidayDates() {
		
		List<HolidayDateEntity> entities = this.repository.findAll();
		
		
		return this.mapper.entityToHolidayDate(entities);
	}

	@Override
	@LoggableEvents(type=EventType.DELETE)
	public void deleteHolidayDateById(final Long id) {
        this.repository.deleteById(id);
	}

	@Override
	public void deleteAllHolidayDates() {
        this.repository.deleteAll();;
	}

	@Override
	@Cacheable("cacheable-time")
	public Long getCacheableTime() {
		
		StopWatch sw = new StopWatch();
		
		sw.start();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		sw.stop();
		
		System.out.println("time taking in getCacheableTime:" + sw.getTotalTimeMillis());
		
		return sw.getTotalTimeMillis();
	}

	@Override
	@Loggable(level=Level.WARN)
	@LoggableEvents(type=EventType.CREATE)
	@Transactional
	public HolidayDate createHolidayDate(HolidayDateDto holidayDateDto) {
		HolidayDateEntity entity = this.mapper.holidayDateDtoToEntity(holidayDateDto);
		entity.setId(null);
		
		this.repository.save(entity);
		
		return this.mapper.entityToHolidayDate(entity);
	}

	@Override
	public List<HolidayDate> createHolidayDates(List<HolidayDateDto> holidayDateDtos) {

		List<HolidayDateEntity> entities = this.mapper.holidayDateDtoToEntity(holidayDateDtos);
		
		entities = this.repository.saveAll(entities);

		return this.mapper.entityToHolidayDate(entities);
	}

	@Override
	@Loggable(level=Level.INFO)
	@LoggableEvents(type=EventType.UPDATE)
	public void updateHolidayDate(Long id, HolidayDateDto holidayDateDto) throws Exception {
		
		HolidayDateEntity entity = this.repository.findById(id).orElseThrow(
				()-> new Exception("HolidayDate not found with the given id:" + id));
		
		entity = this.mapper.holidayDateDtoToEntity(holidayDateDto);
		
		this.repository.save(entity);
	}

}
