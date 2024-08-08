package org.assetsglobal.service;

import java.io.IOException;

import org.assetsglobal.utility.SimpleResponseStructure;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface MultimediaService {

	ResponseEntity<SimpleResponseStructure> saveImage(MultipartFile file, int developerID) throws IOException;

	ResponseEntity<byte[]> getMedia(int mediaId);

	ResponseEntity<byte[]> getMedias(int developerId);

}
