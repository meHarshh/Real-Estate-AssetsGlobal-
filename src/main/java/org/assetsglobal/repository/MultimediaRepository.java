package org.assetsglobal.repository;

import java.util.Optional;

import org.assetsglobal.entity.Multimedia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MultimediaRepository extends JpaRepository<Multimedia, Integer>{

	Optional<Multimedia> findByDeveloperId(int developerId);

}
