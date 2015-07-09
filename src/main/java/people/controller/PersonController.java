package people.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import people.entity.Person;
import people.repository.PersonRepository;

@RestController
@RequestMapping("/people")
public class PersonController {

	@Autowired
	private PersonRepository personRepository;
	
	@RequestMapping(value="/person", method=RequestMethod.POST)
	public void addPerson(@RequestBody Person person){
		personRepository.save(person);
	}
	
	@RequestMapping(value="/person", method=RequestMethod.GET)
	public List<Person> list(){
		return personRepository.findAll();
	}
	
	@RequestMapping(value="/person/surname/{lastName}", method=RequestMethod.GET)
	public List<Person> findByLastName(@PathVariable("lastName") String lastName){
		return personRepository.findByLastName(lastName);
	}
	
	@RequestMapping(value="/person/{id}", method=RequestMethod.GET)
	public Person get(@PathVariable("id") long id){
		return personRepository.findOne(id);
	}
	
	@RequestMapping(value="/person/{id}", method=RequestMethod.PUT)
	public void update(@PathVariable("id") long id, @RequestBody Person person){
		person.setId(id);
		personRepository.save(person);
	}
	
	@RequestMapping(value="/person/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Boolean> delete(@PathVariable("id") long id){
		personRepository.delete(id);
		return new ResponseEntity<Boolean>(Boolean.TRUE, HttpStatus.OK);
	}
	
}
