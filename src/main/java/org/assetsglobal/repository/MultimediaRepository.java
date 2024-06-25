package org.assetsglobal.repository;

import java.util.Optional;

import org.assetsglobal.entity.Multimedia;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MultimediaRepository extends MongoRepository<Multimedia, String>{

	Optional<Multimedia> findByDeveloperId(int developerId);

}
