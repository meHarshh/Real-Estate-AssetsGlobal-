package org.assetsglobal.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class OtpRequest {
		private String email;
		private String otp;
}
