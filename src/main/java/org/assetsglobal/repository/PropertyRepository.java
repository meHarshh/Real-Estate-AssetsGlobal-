package org.assetsglobal.repository;

import org.assetsglobal.entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PropertyRepository extends JpaRepository<Property, Integer>, JpaSpecificationExecutor<Property>{

}
