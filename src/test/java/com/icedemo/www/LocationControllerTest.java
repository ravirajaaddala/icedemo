
package com.icedemo.www;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.icedemo.www.controller.rest.LocationController;
import com.icedemo.www.service.LocationService;


/**
 * 
 */
@WebMvcTest(LocationController.class)
public class LocationControllerTest {

	@Autowired
	private MockMvc mockmvc;
	
	@MockBean
	private LocationService locationService;
	
	
	@Test
	void testFetchLocationByZipCode() throws Exception {
		String[] ldArray = {"32256", "324506", "22345"};

		List<String> ldList = Arrays.asList(ldArray);

		when(locationService.getAllZipcodes()).thenReturn(ldList);
		String response = mockmvc.perform(get("/location/zipcodes")).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		ObjectMapper objectMapper = new ObjectMapper();

		Map<String, Object> responseBody = objectMapper.readValue(response, Map.class);
		System.out.println(responseBody.toString());
		boolean check = responseBody.containsKey("zipcodes");
		assertThat(check ).isEqualTo(true);
		
		List<String> zipcodes = (List<String>) responseBody.get("zipcodes");
		 

		check = zipcodes.contains("32256");
		assertThat(check ).isEqualTo(true);
		
		check = zipcodes.contains("999");
		assertThat(check ).isEqualTo(false);

	}
}
