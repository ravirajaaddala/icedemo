
package com.icedemo.www.controller.rest;

import java.util.HashMap;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.icedemo.www.enums.DataTables;

/**
 * 
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public class SuperController {


	
	
	public ResponseEntity _getJsonresponse(Object res, boolean isSuccess) {
		ResponseEntity re = null;
		if (isSuccess) {
			re = new ResponseEntity(res, _getJsonHeader(), HttpStatus.OK);
		} else {
			re = new ResponseEntity(res, _getJsonHeader(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return re;

	}
	
	public HttpHeaders _getJsonHeader() {
		HttpHeaders resHeader = new HttpHeaders();
		resHeader.setContentType(MediaType.APPLICATION_JSON);
		return resHeader;
	}

	protected HashMap<String,HashMap<String,Object>> _getDataTableContent(DataTables dt, List leagues, String tableKey) {
		HashMap<String,HashMap<String,Object>> result = new HashMap<String,HashMap<String,Object>>();
		HashMap<String, Object> rObject = _getDataTableContent(dt, leagues);
		result.put(tableKey, rObject);
		return result;
	}

	protected HashMap<String, Object> _getDataTableContent(DataTables dt, List leagues) {
		HashMap<String,Object> rObject = new HashMap<String,Object>();
		rObject.put("tablecolumns", DataTables.getColumns(dt));
		rObject.put("tablecontent", leagues);
		return rObject;
	}

}
