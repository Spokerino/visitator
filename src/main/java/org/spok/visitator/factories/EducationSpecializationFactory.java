package org.spok.visitator.factories;

import org.spok.visitator.data.enum_types.EducationSpecializationTypes;
import org.spok.visitator.institution.CollegeFaculty;
import org.spok.visitator.institution.EducationSpecialization;


public class EducationSpecializationFactory {

	public static EducationSpecialization createSpecialization(EducationSpecializationTypes type){
		if(type.equals(EducationSpecializationTypes.COLLEGE_FACULTY))
			return new CollegeFaculty();
		else 
			return null;
	}
}
