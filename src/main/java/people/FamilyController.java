package people;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/family")
public class FamilyController {

	@Autowired
	private FamilyRepository familyRepository;
	
	@Autowired
	private PersonRepository personRepository;
	
	@Autowired
	private FamilyMembersRepository familyMembersRepository;

	@RequestMapping(method=RequestMethod.POST)
	public void addFamily(@RequestBody Family family){
		familyRepository.save(family);
		
		FamilyMembers familyMembers = new FamilyMembers();
		familyMembers.setFamily(family);
		familyMembersRepository.save(familyMembers);
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public List<Family> list(){
		return familyRepository.findAll();
	}
		
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public Family get(@PathVariable("id") long id){
		return familyRepository.findOne(id);
		
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public void update(@PathVariable("id") long id, @RequestBody Family family){
		family.setId(id);
		familyRepository.save(family);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Boolean> delete(@PathVariable("id") long id){
		
		//Delete the family members entry as well
		Family family = familyRepository.findOne(id);
		FamilyMembers familyMembers = familyMembersRepository.findByFamily(family);
		familyMembersRepository.delete(familyMembers.getId());
		
		familyRepository.delete(id);
		
		return new ResponseEntity<Boolean>(Boolean.TRUE, HttpStatus.OK);
	}
	
//	@RequestMapping(value="/{id}", method=RequestMethod.POST)
//	public void addPersonToFamily(@PathVariable("id") long id, @RequestBody Person person){
//		Family family = familyRepository.findOne(id);
//		
//		if(person.getId() == null){
//			return;
//		}
//		
//		person = personRepository.findOne(person.getId());
//		
//		if(person == null){
//			return;
//		}
//		
//		if(family != null && !family.getFamilyMembers().contains(person)){
//			family.addFamilyMember(person);
//			familyRepository.save(family);
//			
//		}
//	}
	
//	@RequestMapping(value="/{familyId}/{personId}", method=RequestMethod.DELETE)
//	public void removePersonFromFamily(@PathVariable("familyId") long familyId, @PathVariable("personId") long personId){
//		Family family = familyRepository.findOne(familyId);
//		Person personToRemove = personRepository.findOne(personId);
//		
//		if(family != null){
//			family.getFamilyMembers().remove(personToRemove);
//			familyRepository.save(family);
//		}
//	}
	
	@RequestMapping(value="/{id}/members", method=RequestMethod.POST)
	public void addFamilyMember(@PathVariable("id") long id, @RequestBody Person person){
		if(person.getId() == null){
			return;
		}
		
		Family family = familyRepository.findOne(id);
		FamilyMembers familyMembers = familyMembersRepository.findByFamily(family);
		
		person = personRepository.findOne(person.getId());
		
		if(person == null){
			return;
		}
		
		familyMembers.getMembers().add(person);
		familyMembersRepository.save(familyMembers);
	}
	
	@RequestMapping(value="/{id}/members", method=RequestMethod.GET)
	public Set<Person> getFamilyMembers(@PathVariable("id") long id){
		Family family = familyRepository.findOne(id);
		FamilyMembers familyMembers = familyMembersRepository.findByFamily(family);
		
		if(familyMembers == null){
			return null;
		}
		
		return familyMembers.getMembers();
	}
	
	@RequestMapping(value="/{family_id}/members{person_id}", method=RequestMethod.DELETE)
	public ResponseEntity<Boolean> removeFamilyMember(@PathVariable("family_id") long family_id, @PathVariable("person_id") long person_id){
		Family family = familyRepository.findOne(family_id);
		Person person = personRepository.findOne(person_id);
		
		if(family == null || person == null){
			return new ResponseEntity<Boolean>(Boolean.FALSE, HttpStatus.BAD_REQUEST);
		}
		
		FamilyMembers familyMembers = familyMembersRepository.findByFamily(family);
		familyMembers.getMembers().remove(person);
		familyMembersRepository.save(familyMembers);
		
		return new ResponseEntity<Boolean>(Boolean.TRUE, HttpStatus.OK);
	}
		

}
