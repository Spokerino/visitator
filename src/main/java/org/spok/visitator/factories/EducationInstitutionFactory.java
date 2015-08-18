package org.spok.visitator.factories;

import org.spok.visitator.data.enum_types.EducationInstitutionTypes;
import org.spok.visitator.institution.College;
import org.spok.visitator.institution.EducationInstitution;

public class EducationInstitutionFactory {
	
	public static EducationInstitution createInstitution(EducationInstitutionTypes type){
		if(type.equals(EducationInstitutionTypes.COLLEGE))
			return new College();
		else 
			return null;
	}
}
