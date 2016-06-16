package org.spok.visitator.factories;

import org.spok.visitator.DataTypeException;
import org.spok.visitator.entities.enum_types.EducationInstitutionTypes;
import org.spok.visitator.entities.institution.College;
import org.spok.visitator.entities.institution.EducationInstitution;
import org.spok.visitator.entities.institution.School;

public class EducationInstitutionFactory {
	
	public static EducationInstitution createInstitution(EducationInstitutionTypes type)
			throws DataTypeException {
		if(type.equals(EducationInstitutionTypes.COLLEGE))
			return new College();
		else if(type.equals(EducationInstitutionTypes.SCHOOL))
			return new School();
		else
			throw new DataTypeException("Unknown type!");
	}
}
