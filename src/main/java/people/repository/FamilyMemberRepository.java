package people.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import people.entity.Family;
import people.entity.FamilyMember;

public interface FamilyMemberRepository extends JpaRepository<FamilyMember, Long>{
	Set<FamilyMember> findByFamilyId(@Param("family_id") Long id);
	Set<Family> findByPersonId(@Param("person_id") Long id);
//	FamilyMember findByFamilyIdAndPersonId(@Param("family_id") Long family_id, @Param("person_id") Long person_id);
}
