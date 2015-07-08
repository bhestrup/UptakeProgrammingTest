package people;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/family")
public class FamilyMembersController {

	
	@Autowired
	private FamilyRepository familyRepository;
	
	@Autowired
	private PersonRepository personRepository;
	
	@Autowired
	private FamilyMembersRepository familyMembersRepository;

}
