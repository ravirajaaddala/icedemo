
package com.icedemo.www.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icedemo.www.dto.HomePropertyDto;
import com.icedemo.www.entity.HomeProperty;
import com.icedemo.www.entity.LocationDetails;
import com.icedemo.www.repository.HomeProperyRepo;
import com.icedemo.www.repository.LocationRepo;

/**
 * 
 */
@Service
public class HomePropertyService {

	@Autowired
	private HomeProperyRepo homeProperyRepo;
	
	@Autowired
	private LocationRepo locationRepo;
	
	public List<HomePropertyDto> getAll(String zipCode){
		List<HomeProperty> homePropList = homeProperyRepo.findByZipcode(zipCode);
		System.out.println("homePropList = "+homePropList.size());
		return homePropList.stream().map(homeProperty->  getHomePropertyDto(homeProperty,zipCode)).collect(Collectors.toList());
	}

	

	/**
	 * @param homePropertyDto
	 */
	public void add(HomePropertyDto homePropertyDto) {
		LocationDetails ld = locationRepo.findByZipcode(homePropertyDto.getZipcode());
		homeProperyRepo.save(getHomeProperty(homePropertyDto,ld));
	}
	
	private HomeProperty getHomeProperty(HomePropertyDto homePropertyDto, LocationDetails locationDetails) {
		ModelMapper modelMapper = new ModelMapper();
        modelMapper.typeMap(HomePropertyDto.class, HomeProperty.class);
        HomeProperty homeProperty = modelMapper.map(homePropertyDto, HomeProperty.class);
        homeProperty.setLocationDetails(locationDetails);
        return homeProperty;
	}
	
	private HomePropertyDto getHomePropertyDto(HomeProperty homeProperty, String zipcode) {
		ModelMapper modelMapper = new ModelMapper();
        modelMapper.typeMap(HomeProperty.class, HomePropertyDto.class);
        HomePropertyDto dto = modelMapper.map(homeProperty, HomePropertyDto.class);
        dto.setZipcode(zipcode);
        return dto;
	}
}
