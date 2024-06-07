package org.assetsglobal.entity;

import org.assetsglobal.enums.AgeOfProperty;
import org.assetsglobal.enums.ConstructionStatus;
import org.assetsglobal.enums.ListedBy;
import org.assetsglobal.enums.Possesion;
import org.assetsglobal.enums.PropertyConfiguration;
import org.assetsglobal.enums.PropertyPurpose;
import org.assetsglobal.enums.PurchaseType;
import org.assetsglobal.enums.PropertyType;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Property {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int propertyId;
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



