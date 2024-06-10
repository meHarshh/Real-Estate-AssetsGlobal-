package org.assetsglobal.repository;

import java.util.List;
import java.util.Optional;

import org.assetsglobal.entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PropertyRepository extends JpaRepository<Property, Integer>, JpaSpecificationExecutor<Property>{

	Optional<List<Property>> findByPropertyLocation(String propertyLocation);

}
