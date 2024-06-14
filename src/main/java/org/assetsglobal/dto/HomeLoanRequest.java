package org.assetsglobal.dto;

import org.assetsglobal.enums.BankingPartner;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class HomeLoanRequest {
	private String name;
	private String email;
	private long phoneNumber;
	private BankingPartner bankingPartner;

}
