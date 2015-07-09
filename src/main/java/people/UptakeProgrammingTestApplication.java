package people;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import people.entity.Family;
import people.entity.Person;
import people.repository.FamilyMemberRepository;
import people.repository.FamilyRepository;
import people.repository.PersonRepository;

@SpringBootApplication
public class UptakeProgrammingTestApplication implements CommandLineRunner{

	@Autowired
    private PersonRepository personRepository;
	
	@Autowired
    private FamilyRepository familyRepository;
	
	@Autowired
    private FamilyMemberRepository familyMembersRepository;
	
    public static void main(String[] args) {
        SpringApplication.run(UptakeProgrammingTestApplication.class, args);
    }

	@Override
	public void run(String... args) throws Exception {
		Person person1 = new Person();
		person1.setFirstName("First");
		person1.setLastName("Person");
		person1 = personRepository.save(person1);
		
		Person person2 = new Person();
		person2.setFirstName("Second");
		person2.setLastName("Person");
		person2 = personRepository.save(person2);
		
		Person person3 = new Person();
		person3.setFirstName("Third");
		person3.setLastName("Person");
		person3 = personRepository.save(person3);
		
		Family family1 = new Family("TEST_FAMILY_1");
		family1 = familyRepository.save(family1);
		Family family2 = new Family("TEST_FAMILY_2");
		family2 = familyRepository.save(family2);
		
//		FamilyMember member1 = new FamilyMember();
//		member1.setFamily(family1);
//		member1.setPerson(person1);
//		member1.setFamilyRelationship(FamilyRelationship.SON);
//		member1 = familyMembersRepository.save(member1);
//		
//		FamilyMember member2 = new FamilyMember();
//		member2.setFamily(family1);
//		member2.setPerson(person2);
//		member2.setFamilyRelationship(FamilyRelationship.FATHER);
//		member2 = familyMembersRepository.save(member2);
		
//		FamilyMembers familyMembers = new FamilyMembers();
//		familyMembers.setFamily(family2);
//		familyMembers.addMember(person1);
//		familyMembers.addMember(person3);
//		
//		familyMembersRepository.save(familyMembers);
		
//		familyRepository.findAll();
	}
    
    
}
