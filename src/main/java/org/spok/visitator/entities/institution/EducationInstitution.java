package org.spok.visitator.entities.institution;

import java.util.List;

import javax.validation.constraints.Size;


public abstract class EducationInstitution {
	
	private Integer id;
	
	@Size(min=2, max=40, message="Length of name must be between {min} and {max} characters")
	private String name;
	@Size(min=7, max=50, message="Length of address must be between {min} and {max} characters")
	private String address;
	private List<EducationSpecialization> specializations;
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<EducationSpecialization> getSpecializations() {
		return specializations;
	}

	public void setSpecializations(List<EducationSpecialization> specializations) {
		this.specializations = specializations;
	}
	
	
	
}
