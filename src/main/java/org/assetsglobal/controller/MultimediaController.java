package org.assetsglobal.controller;

import java.io.IOException;

import org.assetsglobal.service.MultimediaService;
import org.assetsglobal.utility.SimpleResponseStructure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class MultimediaController {
	
	@Autowired
	private MultimediaService multimediaService; 
	
	@PostMapping(value = "addMedia/{developerID}")
	private ResponseEntity<SimpleResponseStructure> saveImage(MultipartFile file , @PathVariable int developerID) throws IOException{
		return multimediaService.saveImage(file, developerID);
	}
	
	@GetMapping(value = "getMedia/{mediaId}")
	private ResponseEntity<byte[]> getMedia(@PathVariable int mediaId){
		return multimediaService.getMedia(mediaId);
	}

	@GetMapping(value = "getMedias/{developerId}")
	private ResponseEntity<byte[]> getMedias(@PathVariable int developerId){
		return multimediaService.getMedias(developerId);
	}
	
}
