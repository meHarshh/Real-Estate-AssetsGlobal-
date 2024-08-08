package org.assetsglobal.controller;

import org.assetsglobal.dto.ChannelPartnerRequest;
import org.assetsglobal.dto.ChannelPartnerResponse;
import org.assetsglobal.service.ChannelPartnerService;
import org.assetsglobal.utility.ResponseStructure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(value = "http://localhost:3000" , allowCredentials = "true")
@RestController
public class ChannelPartnerController {

	@Autowired
	private ChannelPartnerService channelPartnerService;
	
	@PostMapping(value = "addChannelPartner")
	public ResponseEntity<ResponseStructure<ChannelPartnerResponse>> addChannelPartner(@RequestBody ChannelPartnerRequest channelPartnerRequest){
		
		return channelPartnerService.addChannelPartner(channelPartnerRequest);
	}

}
