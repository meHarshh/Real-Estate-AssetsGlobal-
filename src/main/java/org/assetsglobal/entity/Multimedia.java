package org.assetsglobal.entity;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "multimedia")
public class Multimedia {

	@MongoId
	private String mediaId;
	
	private int developerId;
	
	private byte[] mediaBytes;
	
	private String contentType;
	
}
