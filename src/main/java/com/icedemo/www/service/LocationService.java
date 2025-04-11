
package com.icedemo.www.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icedemo.www.dto.LocationDto;
import com.icedemo.www.entity.LocationDetails;
import com.icedemo.www.repository.LocationRepo;

/**
 * 
 */
@Service
public class LocationService {

	@Autowired
	private LocationRepo locationRepo;
	
	public List<LocationDto> getAll() {
		List<LocationDetails> details = locationRepo.findAll();
		return details.stream().map(location->getLocationDto(location)).collect(Collectors.toList());
	}
	
	public LocationDto get(String zipcode) {
		return getLocationDto(locationRepo.findByZipcode(zipcode));
	}
	
	public void add(LocationDto locationDto) {
		LocationDetails details = getLocationDetails(locationDto);
		System.out.println("details = "+details.getZipcode());
		locationRepo.save(details);
	}

	/**
	 * @param locationDto
	 * @return
	 */
	private LocationDetails getLocationDetails(LocationDto locationDto) {
		ModelMapper modelMapper = new ModelMapper();
        modelMapper.typeMap(LocationDto.class, LocationDetails.class);
        return modelMapper.map(locationDto, LocationDetails.class);
	}
	
	private LocationDto getLocationDto(LocationDetails locationDetails) {
		ModelMapper modelMapper = new ModelMapper();
        modelMapper.typeMap(LocationDetails.class, LocationDto.class);
        return modelMapper.map(locationDetails, LocationDto.class);
	}

	/**
	 * @return
	 */
	public List<String> getAllZipcodes() {
		return locationRepo.getAllZipcodes();
	}
}
