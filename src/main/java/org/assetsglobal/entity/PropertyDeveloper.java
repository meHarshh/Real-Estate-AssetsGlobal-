package org.assetsglobal.entity;

import java.time.LocalDate;

import org.assetsglobal.enums.BrockrageCharge;
import org.assetsglobal.enums.ConstructionStatus;
import org.assetsglobal.enums.Facing;
import org.assetsglobal.enums.FurnishedType;
import org.assetsglobal.enums.LandType;
import org.assetsglobal.enums.Parking;
import org.assetsglobal.enums.PropertyType;
import org.assetsglobal.enums.TransactionType;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class PropertyDeveloper {

	// Seller Details
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int propertyDeveloperId;
	
	private String developerName;
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
	private double area;
	private LandType landType;
	private double builtUpArea;
	private double carpetArea;
	private Facing facing;
	private String propertyAddress;
	private String reraId;
	private String propertyDescription;
	private String city;
	private String propertyLocation;
	private String state;
	
	
//	media
//	@Lob
//	private byte[] video;
//	
//	@Lob
//	private byte[] photo;
	

}
