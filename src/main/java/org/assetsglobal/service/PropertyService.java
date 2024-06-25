package org.assetsglobal.service;

import java.util.List;

import org.assetsglobal.dto.PropertyRequest;
import org.assetsglobal.dto.PropertyResponse;
import org.assetsglobal.dto.SearchFilter;
import org.assetsglobal.utility.ResponseStructure;
import org.springframework.http.ResponseEntity;

public interface PropertyService {

	ResponseEntity<ResponseStructure<PropertyResponse>> addProperty(PropertyRequest propertyRequest);

	ResponseEntity<ResponseStructure<List<PropertyResponse>>> findByFilter(SearchFilter searchFilter);

	ResponseEntity<ResponseStructure<List<PropertyResponse>>> findPropertyByCity(String propertyLocation);

	ResponseEntity<ResponseStructure<List<PropertyResponse>>> findAllProperty();

}
