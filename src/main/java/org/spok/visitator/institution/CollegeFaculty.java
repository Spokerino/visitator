package org.spok.visitator.institution;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;



public class CollegeFaculty extends EducationSpecialization {

	@NotNull
	@Min(3)
	@Max(6)
	private int yearsToBecomeBachelor = 4;
	@NotNull
	@Min(4)
	@Max(7)
	private int yearsToBecomeMaster = 5;
		
	public CollegeFaculty() {}
	
//	public CollegeFaculty(Long id, String name, List<Subject> subjects, List<EducationGroup> groups
//			, List<Teacher> teachers) {
//		super(id, name, teachers, groups);
//	}
//
//	public CollegeFaculty(String name, List<Subject> subjects, List<EducationGroup> groups
//			, List<Teacher> teachers) {
//		super(name, teachers, groups);
//	}
	
	public int getYearsToBecomeBachelor() {
		return yearsToBecomeBachelor;
	}

	public void setYearsToBecomeBachelor(int yearsToBecomeBachelor) {
		this.yearsToBecomeBachelor = yearsToBecomeBachelor;
	}

	public int getYearsToBecomeMaster() {
		return yearsToBecomeMaster;
	}

	public void setYearsToBecomeMaster(int yearsToBecomeMaster) {
		this.yearsToBecomeMaster = yearsToBecomeMaster;
	}
	
}
