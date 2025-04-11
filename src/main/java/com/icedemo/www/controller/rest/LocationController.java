
package com.icedemo.www.controller.rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.icedemo.www.dto.LocationDto;
import com.icedemo.www.enums.DataTables;
import com.icedemo.www.service.LocationService;


/**
 * 
 */
@RestController
@SuppressWarnings("unchecked")
@RequestMapping("/location")
public class LocationController extends SuperController {

	@Autowired
	private LocationService locationService;
	
	
	@PostMapping("add")
	public ResponseEntity<String> add(@RequestBody LocationDto locationDto) {

		System.out.println(locationDto.toString());
		HashMap<String, String> result = new HashMap<String, String>();
		try {
			if(locationDto.getCity()!=null && !"".equals(locationDto.getCity().trim())) {
				locationService.add(locationDto);
				result.put("status", "location added successfully");
			}
		} catch (DataIntegrityViolationException e) {
			e.printStackTrace();
			result.put("status", locationDto.getZipcode()+" is already available");
		}catch (Exception e) {
			e.printStackTrace();
			result.put("status", "location failed to add");
		}
		return _getJsonresponse(result, true);
	}
	
	@GetMapping("fetchall")
	public ResponseEntity<String> fetchall() {
		HashMap<String, HashMap<String, Object>> result = null;
		List<LocationDto> locations = new ArrayList<LocationDto>();
		try {
			locations = locationService.getAll();
			result = _getDataTableContent(DataTables.Location, locations, "alllocations");
		} catch (Exception e) {
			e.printStackTrace();	
		}
		return _getJsonresponse(result, true);
	}
	
	@GetMapping("zipcodes")
	public ResponseEntity<String> fetchzipcodes() {
		HashMap<String, List<String>> result = new HashMap<String, List<String>>();
		List<String> zipcodes = new ArrayList<String>();
		try {
			zipcodes = locationService.getAllZipcodes();
			result.put("zipcodes", zipcodes);
			System.out.println("zc = "+result.toString());
		} catch (Exception e) {
			e.printStackTrace();	
		}
		return _getJsonresponse(result, true);
	}
	@GetMapping("/zipcode/{zc}")
	public ResponseEntity<HashMap<String,String>> fetchLocationByZipCode(@PathVariable("zc") String zc) {
		HashMap<String,String> response = new HashMap<String,String>();
		try {
			System.out.println("zipcode called "+zc);
			LocationDto dto = locationService.get(zc);
			response.put("homecity", dto.getCity());
			response.put("homecountry", dto.getCountry());
			response.put("homecounty", dto.getCounty());
			response.put("homestate", dto.getState());
		} catch (Exception e) {
			e.printStackTrace();	
		}
		return _getJsonresponse(response, true);
	}
}
