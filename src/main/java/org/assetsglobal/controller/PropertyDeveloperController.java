package org.assetsglobal.controller;

import java.util.List;

import org.assetsglobal.dto.PropertyDeveloperRequest;
import org.assetsglobal.dto.PropertyDeveloperResponse;
import org.assetsglobal.service.PropertyDeveloperService;
import org.assetsglobal.utility.ResponseStructure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class PropertyDeveloperController {

	@Autowired
	private PropertyDeveloperService propertyDeveloperService;

	@PostMapping(value = "addSellerWithProperty")
	ResponseEntity<ResponseStructure<PropertyDeveloperResponse>> addSellerWithProperty(
			@RequestBody PropertyDeveloperRequest sellerDashboardRequest,
			@RequestParam(value = "video", required = false) MultipartFile videoFile,
			@RequestParam(value = "photo", required = false) MultipartFile photoFile) {
		return propertyDeveloperService.addSellerWithProperty(sellerDashboardRequest, videoFile, photoFile);
	}

	@GetMapping(value = "fetchSellerWithProperty")
	ResponseEntity<ResponseStructure<List<PropertyDeveloperResponse>>> fetchAllSellerWithProperty() {
		return propertyDeveloperService.fetchAllSellerWithProperty();
	}

	@GetMapping(value = "fetchPropertySingle/{sellerDashboardId}")
	ResponseEntity<ResponseStructure<PropertyDeveloperResponse>> fetchPropertyById(
			@PathVariable int sellerDashboardId) {
		return propertyDeveloperService.fetchPropertyById(sellerDashboardId);
	}

	@GetMapping(value = "fetchLocationByCity/{city}")
	ResponseEntity<ResponseStructure<List<String>>> findLocationByCity(@PathVariable String city) {
		return propertyDeveloperService.findLocationByCity(city);
	}

	@GetMapping(value = "fetchPropertyByLocation/{location}")
	ResponseEntity<ResponseStructure<List<PropertyDeveloperResponse>>> findPropoertyByLocation(
			@PathVariable String location) {
		return propertyDeveloperService.findPropertyByLocation(location);

	}
}
