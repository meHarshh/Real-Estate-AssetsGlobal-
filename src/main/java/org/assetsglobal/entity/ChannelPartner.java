package org.assetsglobal.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ChannelPartner {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int channelPartnerId;
	private String channelPartnerType;
	private String channelPartnerName;
	private String channelPartnerEmail;
	private long channelPartnerPhone;
	private String channelPartnerOwner;
	private String channelPartnerAddress;
	
}
