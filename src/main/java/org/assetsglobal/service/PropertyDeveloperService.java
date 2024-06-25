package org.assetsglobal.service;

import java.util.List;

import org.assetsglobal.dto.PropertyDeveloperRequest;
import org.assetsglobal.dto.PropertyDeveloperResponse;
import org.assetsglobal.utility.ResponseStructure;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface PropertyDeveloperService {

	ResponseEntity<ResponseStructure<PropertyDeveloperResponse>> addSellerWithProperty(
			PropertyDeveloperRequest sellerDashboardRequest, MultipartFile videoFile, MultipartFile photoFile);

	ResponseEntity<ResponseStructure<List<PropertyDeveloperResponse>>> fetchAllSellerWithProperty();

	ResponseEntity<ResponseStructure<PropertyDeveloperResponse>> fetchPropertyById(int sellerDaashboardId);

	ResponseEntity<ResponseStructure<List<String>>> findLocationByCity(String city);

	ResponseEntity<ResponseStructure<List<PropertyDeveloperResponse>>> findPropertyByLocation(String location);

}
