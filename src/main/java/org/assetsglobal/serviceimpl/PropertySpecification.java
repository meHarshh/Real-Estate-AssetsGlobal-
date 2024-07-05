package org.assetsglobal.serviceimpl;
import java.util.ArrayList;
import java.util.List;
import org.assetsglobal.dto.SearchFilter;
import org.assetsglobal.entity.Property;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import jakarta.persistence.criteria.Predicate;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Component
public class PropertySpecification {
	@SuppressWarnings("unused")
	@Autowired
	private SearchFilter searchFilter;

//	this is the method used to fetch the property based on the filters applied 
	public Specification<Property> buildSpecification(SearchFilter searchFilter){

		return (root , query , criteriaBuilder)->{
			List<Predicate> predicates = new ArrayList<Predicate>();

			if(searchFilter.getMinPrice()>0) {
				predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("price"), criteriaBuilder.literal(searchFilter.getMinPrice())));
			}
			if(searchFilter.getMaxPrice()>0) {
				predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("price"), criteriaBuilder.literal(searchFilter.getMaxPrice())));
			}
			if(searchFilter.getPropertyPurpose()!=null) {
				predicates.add(criteriaBuilder.equal(root.get("propertyPurpose"), searchFilter.getPropertyPurpose()));
			}
			if(searchFilter.getConfiguration()!=null) {
				predicates.add(criteriaBuilder.equal(root.get("configuration"), searchFilter.getConfiguration()));
			}
			if(searchFilter.getPurchaseType()!=null) {
				predicates.add(criteriaBuilder.equal(root.get("purchseType"), searchFilter.getPurchaseType()));
			}
			if(searchFilter.getPossesion()!=null) {
				predicates.add(criteriaBuilder.equal(root.get("possesion"), searchFilter.getPossesion()));
			}
			if(searchFilter.getListedBy()!=null) {
				predicates.add(criteriaBuilder.equal(root.get("listedBy"), searchFilter.getListedBy()));
			}
			if(searchFilter.getAgeOfProperty()!=null) {
				predicates.add(criteriaBuilder.equal(root.get("ageOfProperty"), searchFilter.getAgeOfProperty()));
			}
			if(searchFilter.getPropertyType()!=null) {
				predicates.add(criteriaBuilder.equal(root.get("propertyType"), searchFilter.getPropertyType()));
			}
			return criteriaBuilder.and(predicates.toArray(new Predicate[0]));

		};
	}

}
