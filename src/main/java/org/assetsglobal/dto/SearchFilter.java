package org.assetsglobal.dto;


import org.assetsglobal.enums.AgeOfProperty;
import org.assetsglobal.enums.ListedBy;
import org.assetsglobal.enums.Possesion;
import org.assetsglobal.enums.PropertyConfiguration;
import org.assetsglobal.enums.PropertyPurpose;
import org.assetsglobal.enums.PurchaseType;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

import org.assetsglobal.enums.PropertyType;

@Getter
@Setter
@Component
public class SearchFilter {

	long minPrice;
	long maxPrice;
	PropertyPurpose propertyPurpose;
	PropertyConfiguration configuration;
	PurchaseType purchaseType;
	Possesion possesion;
	ListedBy listedBy;
	AgeOfProperty ageOfProperty;
	PropertyType propertyType;
}
