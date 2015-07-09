package people.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import scala.annotation.meta.getter;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class FamilyMember {

	public enum FamilyRelationship {

		FATHER("Father"),
		MOTHER("Mother"),
		SON("Son"),
		DAUGHTER("Daughter");
		
		private String name;
		
		public String getName(){
			return name;
		}
		
		private FamilyRelationship(String name) {
			this.name = name;
		}
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "person_id")
	private Person person;
	
	@ManyToOne
	@JoinColumn(name = "family_id")
	@JsonIgnore
	private Family family;
	
	@Column(name="family_relationship")
	private FamilyRelationship familyRelationship;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Person getPerson() {
		return person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
	public Family getFamily() {
		return family;
	}
	public void setFamily(Family family) {
		this.family = family;
	}
	public FamilyRelationship getFamilyRelationship() {
		return familyRelationship;
	}
	public void setFamilyRelationship(FamilyRelationship familyRelationship) {
		this.familyRelationship = familyRelationship;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof FamilyMember){
			FamilyMember toCompare = (FamilyMember)obj;
			
			if(toCompare.getId().equals(toCompare.getId())){
				return true;
			}else{
				return false;
			}
//			
//			if(toCompare.getFamily().getId() == this.getFamily().getId() && toCompare.getPerson().getId() == this.getPerson().getId()){
//				return true;
//			}else{
//				return false;
//			}
		}else{
			return super.equals(obj);
		}
	}
}
