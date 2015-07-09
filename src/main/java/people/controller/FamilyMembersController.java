package people.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import people.repository.FamilyMemberRepository;
import people.repository.FamilyRepository;
import people.repository.PersonRepository;

@RestController
@RequestMapping("/family")
public class FamilyMembersController {

	
	@Autowired
	private FamilyRepository familyRepository;
	
	@Autowired
	private PersonRepository personRepository;
	
	@Autowired
	private FamilyMemberRepository familyMemberRepository;

}
