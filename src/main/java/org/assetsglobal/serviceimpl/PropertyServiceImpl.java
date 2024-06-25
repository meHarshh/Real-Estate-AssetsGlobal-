package org.assetsglobal.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.assetsglobal.dto.PropertyRequest;
import org.assetsglobal.dto.PropertyResponse;
import org.assetsglobal.dto.SearchFilter;
import org.assetsglobal.entity.Property;
import org.assetsglobal.repository.PropertyRepository;
import org.assetsglobal.service.PropertyService;
import org.assetsglobal.utility.ResponseStructure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PropertyServiceImpl implements PropertyService {

	@Autowired
	private PropertyRepository propertyRepository;

	@Autowired
	private PropertySpecification propertySpecification;

	@Autowired
	private ResponseStructure<PropertyResponse> responseStructure;

	@Autowired
	private ResponseStructure<List<PropertyResponse>> structure;

	@Override
	public ResponseEntity<ResponseStructure<PropertyResponse>> addProperty(PropertyRequest propertyRequest) {

		Property property = propertyRepository.save(mapToProperty(propertyRequest));
		PropertyResponse propertyResponse = mapToResponse(property);

		return ResponseEntity.ok(responseStructure.setData(propertyResponse).setMessage("Data saved in the db")
				.setStatusCode(HttpStatus.OK.value()));
	}

	@SuppressWarnings("rawtypes")
	@Override
	public ResponseEntity<ResponseStructure<List<PropertyResponse>>> findByFilter(SearchFilter searchFilter) {
		List<Property> filteredProperties = propertyRepository
				.findAll(propertySpecification.buildSpecification(searchFilter));
		@SuppressWarnings("unchecked")
		List<PropertyResponse> filteredResponses = new ArrayList();
		for (Property property : filteredProperties) {
			filteredResponses.add(mapToResponse(property));
		}
		if ((filteredResponses.size() != 0))
			return ResponseEntity.ok(structure.setData(filteredResponses)
					.setMessage("The list of the property is mentioned below").setStatusCode(HttpStatus.OK.value()));

		else
			return ResponseEntity
					.ok(structure.setData(null).setMessage("No properties with the filters , try something else")
							.setStatusCode(HttpStatus.OK.value()));
	}

	private PropertyResponse mapToResponse(Property property) {

		return PropertyResponse.builder().propertyId(property.getPropertyId())
				.nameOfTheProperty(property.getNameOfTheProperty()).propertyLocation(property.getPropertyLocation())
				.propertyPurpose(property.getPropertyPurpose()).developer(property.getDeveloper())
				.configuration(property.getConfiguration()).purchseType(property.getPurchseType())
				.possesion(property.getPossesion()).listedBy(property.getListedBy())
				.ageOfProperty(property.getAgeOfProperty()).area(property.getArea()).price(property.getPrice())
				.priceRange(property.getPriceRange()).constructionStatus(property.getConstructionStatus())
				.propertyType(property.getPropertyType()).numberOfFloors(property.getNumberOfFloors()).build();
	}

	private Property mapToProperty(PropertyRequest propertyRequest) {
		Property property = new Property();
		property.setAgeOfProperty(propertyRequest.getAgeOfProperty());
		property.setArea(propertyRequest.getArea());
		property.setConfiguration(propertyRequest.getConfiguration());
		property.setConstructionStatus(propertyRequest.getConstructionStatus());
		property.setDeveloper(propertyRequest.getDeveloper());
		property.setListedBy(propertyRequest.getListedBy());
		property.setNameOfTheProperty(propertyRequest.getNameOfTheProperty());
		property.setNumberOfFloors(propertyRequest.getNumberOfFloors());
		property.setPossesion(propertyRequest.getPossesion());
		property.setPrice(propertyRequest.getPrice());
		property.setPriceRange(propertyRequest.getPriceRange());
		property.setPropertyLocation(propertyRequest.getPropertyLocation());
		property.setPropertyPurpose(propertyRequest.getPropertyPurpose());
		property.setPropertyType(propertyRequest.getPropertyType());
		property.setPurchseType(propertyRequest.getPurchseType());

		return property;

	}

	@Override
	public ResponseEntity<ResponseStructure<List<PropertyResponse>>> findPropertyByCity(String propertyLocation) {
		Optional<List<Property>> optionalProperties = propertyRepository.findByPropertyLocation(propertyLocation);

		if (optionalProperties.isPresent() && !optionalProperties.get().isEmpty()) {
			List<PropertyResponse> propertyResponses = optionalProperties.get().stream()
					.map(property -> mapToResponse(property)).collect(Collectors.toList());

			ResponseStructure<List<PropertyResponse>> responseStructure = new ResponseStructure<>();
			responseStructure.setData(propertyResponses);
			responseStructure.setMessage("Properties found in " + propertyLocation);
			responseStructure.setStatusCode(HttpStatus.OK.value());

			return ResponseEntity.ok(responseStructure);
		} else {
			throw new RuntimeException();

		}
	}

	@Override
	public ResponseEntity<ResponseStructure<List<PropertyResponse>>> findAllProperty() {
		List<Property> properties = propertyRepository.findAll();

		List<PropertyResponse> propertiesResponses = properties.stream().map(property -> mapToResponse(property))
				.collect(Collectors.toList());

		ResponseStructure<List<PropertyResponse>> responseStructure = new ResponseStructure<>();
		responseStructure.setData(propertiesResponses);
		responseStructure.setMessage("The numbers of property fetched is " + propertiesResponses.size());
		responseStructure.setStatusCode(HttpStatus.OK.value());

		return ResponseEntity.ok(responseStructure);
	}
}
