package org.assetsglobal.dto;

import org.assetsglobal.enums.AgeOfProperty;
import org.assetsglobal.enums.ConstructionStatus;
import org.assetsglobal.enums.ListedBy;
import org.assetsglobal.enums.Possesion;
import org.assetsglobal.enums.PropertyConfiguration;
import org.assetsglobal.enums.PropertyPurpose;
import org.assetsglobal.enums.PropertyType;
import org.assetsglobal.enums.PurchaseType;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class PropertyRequest {
	private String nameOfTheProperty;
	private String propertyLocation;
	private PropertyPurpose propertyPurpose; 
	private String developer;
	private PropertyConfiguration configuration;
	private PurchaseType purchseType;
	private Possesion possesion;
	private ListedBy listedBy;
	private AgeOfProperty ageOfProperty; 
	private String area;
	private String priceRange;
	private long price;
	private ConstructionStatus constructionStatus;
	private PropertyType propertyType;
	private String numberOfFloors;

}
