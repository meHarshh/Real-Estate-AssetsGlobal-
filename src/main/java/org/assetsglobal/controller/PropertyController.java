package org.assetsglobal.controller;


import java.util.List;

import org.assetsglobal.dto.PropertyRequest;
import org.assetsglobal.dto.PropertyResponse;
import org.assetsglobal.dto.SearchFilter;
import org.assetsglobal.service.PropertyService;
import org.assetsglobal.utility.ResponseStructure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PropertyController {

	@Autowired
	private PropertyService propertyService;

	@PostMapping(value = "addProperty")
	public ResponseEntity<ResponseStructure<PropertyResponse>> addProperty(@RequestBody PropertyRequest propertyRequest) {
		return propertyService.addProperty(propertyRequest);
	}
	
	@GetMapping(value = "filter")
	public ResponseEntity<ResponseStructure<List<PropertyResponse>>> findByFilter(@RequestBody SearchFilter searchFilter){
		return propertyService.findByFilter(searchFilter);
	}
}
