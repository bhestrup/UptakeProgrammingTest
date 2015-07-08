package people;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UptakeProgrammingTestApplication implements CommandLineRunner{

	@Autowired
    private PersonRepository personRepository;
	
	@Autowired
    private FamilyRepository familyRepository;
	
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
		
		Family family1 = new Family();
		family1 = familyRepository.save(family1);
		
		Family family2 = new Family();
//		family2.getFamilyMembers().add(person1);
//		family2.getFamilyMembers().add(person2);
		family2 = familyRepository.save(family2);
		
		family2.getFamilyMembers().add(person1);
		
		family2 = familyRepository.save(family2);
		
//		familyRepository.findAll();
	}
    
    
}
