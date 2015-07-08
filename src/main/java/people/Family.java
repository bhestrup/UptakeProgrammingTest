package people;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class Family {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@OneToMany(mappedBy="family", cascade=CascadeType.ALL)
	private Set<Person> familyMembers = new HashSet<Person>();
	
	private String familyName;
	
	public Family(String familyName, Set<Person> familyMembers){
		this.setFamilyName(familyName);
		this.familyMembers = familyMembers;
	}
	
	public Family(){
	}
	
	
	public void setFamilyMembers(Set<Person> familyMembers){
		this.familyMembers = familyMembers;
	}
	
	public void addFamilyMember(Person person){
		getFamilyMembers().add(person);
		person.setFamily(this);
	}
	
	public Set<Person> getFamilyMembers() {
		return familyMembers;
	}
	
	public Long getId(){
		return id;
	}
	
	public void setId(Long id){
		this.id = id;
	}

	public String getFamilyName() {
		return familyName;
	}

	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}
	
//	@OneToMany(mappedBy = "family")
//	private Map<Person, FamilyRelationship> familyMembers;
//
//	public Map<Person, FamilyRelationship>  getFamilyMembers() {
//		return familyMembers;
//	}
//
//	public void setFamilyMembers(Map<Person, FamilyRelationship>  familyMembers) {
//		this.familyMembers = familyMembers;
//	}
//	
//	public void addFamilyMember(Person person, FamilyRelationship familyRelationship){
//		this.familyMembers.put(person, familyRelationship);
//	}
//	
//	public void removeFamilyMember(Person person){
//		this.familyMembers.remove(person);
//	}
	
//	@Override
//	public String toString() {
//		StringBuilder sb = new StringBuilder();
//		sb.append("family id:");
//		sb.append(id);
//		sb.append(", familyMembers: ");
//		for(Person p : familyMembers){
//			sb.append(p.getLastName());
//			sb.append(", ");
//			sb.append(p.getFirstName());
//			sb.append(" : ");
//		}
//		return super.toString();
//	}
}
