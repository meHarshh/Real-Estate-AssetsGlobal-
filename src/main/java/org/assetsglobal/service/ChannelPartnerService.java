package org.assetsglobal.service;

import org.assetsglobal.dto.ChannelPartnerRequest;
import org.assetsglobal.dto.ChannelPartnerResponse;
import org.assetsglobal.utility.ResponseStructure;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface ChannelPartnerService {


	ResponseEntity<ResponseStructure<ChannelPartnerResponse>> addChannelPartner(
			ChannelPartnerRequest channelPartnerRequest);
}
