package people;

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
