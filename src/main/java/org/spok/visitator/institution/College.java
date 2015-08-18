package org.spok.visitator.institution;

import java.util.List;

public class College extends EducationInstitution {
	
//	private Set<CollegeFaculty> faculties;

	public College(){}
	
	public College(Integer id, String name, String address, List<EducationSpecialization> specializations) {
		super(id, name, address, specializations);

	}
	
	public College(String name, String address, List<EducationSpecialization> specializations) {
		super(name, address, specializations);

	}

}
