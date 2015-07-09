package people.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import people.entity.Family;

@RepositoryRestResource
public interface FamilyRepository extends JpaRepository<Family, Long>{
	List<Family> findByFamilyName(@Param("family_name") String familyName);
//	List<Family> findByFamilyMembersPersonId(@Param("person_id") Long id);
}
