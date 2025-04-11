
package com.icedemo.www.controller.ui;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 */
@Controller
public class LocationUIController {

	@RequestMapping(value = "/index")
	public String employerdashboard() {
		return "index";
	}
}
