package org.assetsglobal.entity;

import org.assetsglobal.enums.ServiceType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "service_page_lead")
public class Service {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO                                                                             )
	@Column(name = "service_id")
	private int serviceId;
	private String name;
	private String email;
	private long phoneNumber;
	private String message;
	private ServiceType serviceType;
	

}
