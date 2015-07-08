package people;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class FamilyMembers {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="family_id")
	private Family family;
	
	@ManyToMany
	@JoinColumn(name="person_id")
	private Set<Person> members = new HashSet<Person>();
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public Family getFamily() {
		return family;
	}
	public void setFamily(Family family) {
		this.family = family;
	}
	
	public Set<Person> getMembers() {
		return members;
	}
	public void setMembers(Set<Person> members) {
		this.members = members;
	}
	
	public void addMember(Person person){
		this.members.add(person);
	}
}
