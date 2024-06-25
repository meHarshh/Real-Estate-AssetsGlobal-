package org.assetsglobal.repository;

import java.util.List;
import java.util.Optional;

import org.assetsglobal.entity.PropertyDeveloper;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropertyDeveloperRepository extends JpaRepository<PropertyDeveloper, Integer>{

	Optional<List<PropertyDeveloper>> findByCity(String city);

	Optional<List<PropertyDeveloper>> findByPropertyLocation(String location);

}
