package people;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface FamilyMembersRepository extends JpaRepository<FamilyMembers, Long>{

	FamilyMembers findByFamily(@Param("family") Family family);
}
