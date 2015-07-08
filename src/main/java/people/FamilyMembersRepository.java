package people;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface FamilyMembersRepository extends JpaRepository<FamilyMembers, Long>{

	FamilyMembers findByFamilyId(@Param("id") Long id);
}
