package org.assetsglobal.dto;

import java.time.LocalDate;

import org.assetsglobal.enums.BrockrageCharge;
import org.assetsglobal.enums.ConstructionStatus;
import org.assetsglobal.enums.Facing;
import org.assetsglobal.enums.FurnishedType;
import org.assetsglobal.enums.LandType;
import org.assetsglobal.enums.Parking;
import org.assetsglobal.enums.PropertyType;
import org.assetsglobal.enums.TransactionType;

import lombok.Data;

@Data
public class PropertyDeveloperRequest {

	private String sellerName;
	private long sellerMobileNumber;
	private String sellerEmail;
	private String sellerLocation;

	// Property Details from the seller that he wants to list and sell
	private PropertyType propertyType;
	private TransactionType transactionType;
	private ConstructionStatus constructionStatus;
	private int bhk;
	private int bathroom;
	private int balcony;
	private FurnishedType furnishedType;
	private Parking parking;
	private int openParking;
	private LocalDate possessionDate;
	private long cost;
	private int maintainceCharge;
	private BrockrageCharge brockrageCharge;
	private double builtUpArea;
	private double carpetArea;
	private double area;
	private LandType landType;
	private Facing facing;
	private String propertyAddress;
	private String reraId;
	private String propertyDescription;
	private String city;
	private String propertyLocation;
	private String state;
	
}
