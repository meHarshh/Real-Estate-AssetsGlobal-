package org.assetsglobal.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Entity
public class Multimedia {

	
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private int mediaId;
	
	private int developerId;
	
	private byte[] mediaBytes;
	
	private String contentType;
	
}
