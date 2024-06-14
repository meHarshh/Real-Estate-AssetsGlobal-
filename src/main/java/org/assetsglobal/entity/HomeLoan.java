package org.assetsglobal.entity;

import org.assetsglobal.enums.BankingPartner;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "home_loan_client")
public class HomeLoan {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int loadId;
	private String name;
	private String email;
	private long phoneNumber;
	private BankingPartner bankingPartner;
}
