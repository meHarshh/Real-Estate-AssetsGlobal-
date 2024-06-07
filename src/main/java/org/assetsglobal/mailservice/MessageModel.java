package org.assetsglobal.mailservice;

import lombok.Builder;
import lombok.Data;

@Data  
@Builder
public class MessageModel {
	private String to;
	private String subject;
	private String text;

}
