
package com.icedemo.www.controller.rest;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.icedemo.www.dto.HomePropertyDto;
import com.icedemo.www.enums.HomeTypeEnum;
import com.icedemo.www.service.HomePropertyService;

/**
 * 
 */
@RestController
@SuppressWarnings("unchecked")
@RequestMapping("/property")
public class PropertyController extends SuperController {

	@Autowired
	private HomePropertyService homePropertyService;
	
	@GetMapping("/homes/{zipcode}")
	public ResponseEntity<List<HomePropertyDto>> getHomes(@PathVariable("zipcode") String zipcode){
		List<HomePropertyDto> hps = homePropertyService.getAll(zipcode);
		return _getJsonresponse(hps, true);
	}
	
	
	@PostMapping("/home/add")
	public ResponseEntity<List<HomePropertyDto>> addHome(@RequestBody HomePropertyDto homePropertyDto){
		System.out.println(homePropertyDto.toString());
		HashMap<String, String> result = new HashMap<String, String>();
		try {
			String type = homePropertyDto.getType();
			HomeTypeEnum homeTypeEnum = HomeTypeEnum.get(type);
			if(HomeTypeEnum.UNKNOWN.getCode() == homeTypeEnum.getCode()) {
				result.put("status", type+" is not available yet!!");
			}else{
				homePropertyDto.setType(homeTypeEnum.getName());
				homePropertyService.add(homePropertyDto);
				result.put("status", "home added successfully");
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.put("status", "home failed to add");
		}
		return _getJsonresponse(result, true);
	}
}
