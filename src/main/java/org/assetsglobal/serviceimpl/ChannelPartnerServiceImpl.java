package org.assetsglobal.serviceimpl;

import org.assetsglobal.dto.ChannelPartnerRequest;
import org.assetsglobal.dto.ChannelPartnerResponse;
import org.assetsglobal.entity.ChannelPartner;
import org.assetsglobal.repository.ChannelPartnerRepository;
import org.assetsglobal.service.ChannelPartnerService;
import org.assetsglobal.utility.ResponseStructure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ChannelPartnerServiceImpl implements ChannelPartnerService {

	@Autowired
	private ChannelPartnerRepository channelPartnerRepository;

	@Autowired
	private ResponseStructure<ChannelPartnerResponse> responseStructure;

	@Override
	public ResponseEntity<ResponseStructure<ChannelPartnerResponse>> addChannelPartner(
			ChannelPartnerRequest channelPartnerRequest) {
		ChannelPartner channelPartner = mapToEntity(channelPartnerRequest);
		ChannelPartnerResponse channelPartnerResponse = mapToResponse(channelPartnerRepository.save(channelPartner));
		return ResponseEntity.ok(responseStructure
				.setMessage("Hey " + channelPartnerRequest.getChannelPartnerName() + " "
						+ "Congratulations on becoming our partner our team will connet you soon")
				.setData(channelPartnerResponse).setStatusCode(HttpStatus.OK.value()));

	}

	private ChannelPartnerResponse mapToResponse(ChannelPartner channelPartner) {
		return ChannelPartnerResponse.builder().channelPartnerId(channelPartner.getChannelPartnerId())
				.channelPartnerName(channelPartner.getChannelPartnerName())
				.channelPartnerEmail(channelPartner.getChannelPartnerEmail())
				.channelPartnerPhone(channelPartner.getChannelPartnerPhone())
				.channelPartnerOwner(channelPartner.getChannelPartnerOwner())
				.channelPartnerType(channelPartner.getChannelPartnerType()).build();
	}

	private ChannelPartner mapToEntity(ChannelPartnerRequest channelPartnerRequest) {
		ChannelPartner channelPartner = new ChannelPartner();
		channelPartner.setChannelPartnerName(channelPartnerRequest.getChannelPartnerName());
		channelPartner.setChannelPartnerEmail(channelPartnerRequest.getChannelPartnerEmail());
		channelPartner.setChannelPartnerAddress(channelPartnerRequest.getChannelPartnerAddress());
		channelPartner.setChannelPartnerOwner(channelPartnerRequest.getChannelPartnerOwner());
		channelPartner.setChannelPartnerPhone(channelPartnerRequest.getChannelPartnerPhone());
		channelPartner.setChannelPartnerType(channelPartnerRequest.getChannelPartnerType());
		return channelPartner;

	}

}
