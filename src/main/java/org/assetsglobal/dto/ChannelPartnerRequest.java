package org.assetsglobal.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ChannelPartnerRequest {

	private String channelPartnerType;
	private String channelPartnerName;
	private String channelPartnerEmail;
	private long channelPartnerPhone;
	private String channelPartnerOwner;
	private String channelPartnerAddress;	
}
