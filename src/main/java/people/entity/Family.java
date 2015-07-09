package people.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Family implements Comparable<Family>{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@OneToMany
	private Set<FamilyMember> familyMembers = new HashSet<FamilyMember>();
	
	private String familyName;
	
	public Family(String familyName){
		this(familyName, new HashSet<FamilyMember>());
	}
	
	public Family(String familyName, Set<FamilyMember> familyMembers){
		this.familyMembers = familyMembers;
		this.familyName = familyName;
	}
	
	public Family(){
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

	public Set<FamilyMember> getFamilyMembers() {
		return familyMembers;
	}

	public void setFamilyMembers(Set<FamilyMember>  familyMembers) {
		this.familyMembers = familyMembers;
	}
	
	public void addFamilyMember(FamilyMember familyMember){
		this.familyMembers.add(familyMember);
	}
	
	public void removeFamilyMember(FamilyMember familyMember){
		this.familyMembers.remove(familyMember);
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Family){
			return ((Family)obj).getId() == this.getId();
		}else{
			return super.equals(obj);
		}
	}

	@Override
	public int compareTo(Family o) {
		return this.getId().compareTo(o.getId());
	}
}
