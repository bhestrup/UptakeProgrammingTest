package people.controller;

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

import people.entity.Family;
import people.entity.FamilyMember;
import people.entity.Person;
import people.repository.FamilyMemberRepository;
import people.repository.FamilyRepository;
import people.repository.PersonRepository;

@RestController
@RequestMapping("/family")
public class FamilyController {

	@Autowired
	private FamilyRepository familyRepository;
	
	@Autowired
	private PersonRepository personRepository;
	
	@Autowired
	private FamilyMemberRepository familyMemberRepository;

	@RequestMapping(method=RequestMethod.POST)
	public void addFamily(@RequestBody Family family){
		familyRepository.save(family);
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
		Set<FamilyMember> familyMembers = familyMemberRepository.findByFamilyId(id);
		
		//Remove the family from the Set of families for each FamilyMember
		for(FamilyMember familyMember : familyMembers){
			familyMemberRepository.delete(familyMember.getId());
		}
		
		familyRepository.delete(id);
		
		return new ResponseEntity<Boolean>(Boolean.TRUE, HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}/members", method=RequestMethod.POST)
	public void addFamilyMember(@PathVariable("id") long id, @RequestBody FamilyMember familyMember){
		
		//If the passed FamilyMember does not have a valid person or FamilyRelationship, return
		if(familyMember.getPerson() == null || familyMember.getPerson().getId() == null || familyMember.getFamilyRelationship() == null){
			return;
		}
		
		Family family = familyRepository.findOne(id);
		if(family == null){
			return;
		}
		
		Set<FamilyMember> familyMembers = familyMemberRepository.findByFamilyId(id);
		Person person = personRepository.findOne(familyMember.getPerson().getId());
		
		//If the person does not exist in the data store, return
		if(person == null){
			return;
		}
		
		familyMember.setPerson(person);
		familyMember.setFamily(family);
		
		if(familyMembers.contains(familyMember)){
			return;
		}
		
		FamilyMember savedMember = familyMemberRepository.save(familyMember);
		
		person.getFamilies().add(savedMember);
		personRepository.save(person);
		
		family.getFamilyMembers().add(savedMember);
		familyRepository.save(family);
		
		
		
	}
	
	@RequestMapping(value="/{id}/members", method=RequestMethod.GET)
	public Set<FamilyMember> getFamilyMembers(@PathVariable("id") long id){
		Family family = familyRepository.getOne(id);
		if(family == null){
			return null;
		}
		return family.getFamilyMembers();
	}
	
	@RequestMapping(value="/{family_id}/members/{person_id}", method=RequestMethod.DELETE)
	public ResponseEntity<Boolean> removeFamilyMember(@PathVariable("family_id") long family_id, @PathVariable("person_id") long person_id){
		Family family = familyRepository.findOne(family_id);
		Person person = personRepository.findOne(person_id);
		
		if(family == null || person == null){
			return new ResponseEntity<Boolean>(Boolean.FALSE, HttpStatus.BAD_REQUEST);
		}
		
		Set<FamilyMember> familyMembers = familyMemberRepository.findByFamilyId(family_id);
		FamilyMember memberToRemove = null;
		
		for(FamilyMember member : familyMembers){
			if(member.getPerson().getId() == person_id){
				memberToRemove = member;
				break;
			}
		}
		
		if(memberToRemove == null){
			return new ResponseEntity<Boolean>(Boolean.FALSE, HttpStatus.BAD_REQUEST);	
		}
		
		familyMemberRepository.delete(memberToRemove);

		return new ResponseEntity<Boolean>(Boolean.TRUE, HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}/members", method=RequestMethod.PUT)
	public void updateFamilyMember(@PathVariable("id") long id, @RequestBody FamilyMember familyMember){

		//If the passed FamilyMember does not have a valid person or FamilyRelationship, return
		if(familyMember.getPerson() == null || familyMember.getPerson().getId() == null || familyMember.getId() == null || familyMember.getFamilyRelationship() == null){
			return;
		}
		
		Family family = familyRepository.findOne(id);
		if(family == null){
			return;
		}
		
		FamilyMember familyMemberToUpdate = familyMemberRepository.findOne(familyMember.getId());

		if(familyMemberToUpdate == null){
			return;
		}
		
		//The FamilyRelationship is currently the only property of FamilyMember that can be changed
		familyMemberToUpdate.setFamilyRelationship(familyMember.getFamilyRelationship());
		familyMemberRepository.save(familyMemberToUpdate);
	}
		

}
