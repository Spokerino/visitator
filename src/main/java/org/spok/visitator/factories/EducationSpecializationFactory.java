package org.spok.visitator.factories;

import org.spok.visitator.DataTypeException;
import org.spok.visitator.entities.enum_types.EducationSpecializationTypes;
import org.spok.visitator.entities.institution.CollegeFaculty;
import org.spok.visitator.entities.institution.EducationSpecialization;
import org.spok.visitator.entities.institution.SchoolMainSpecialization;


public class EducationSpecializationFactory {

	public static EducationSpecialization createSpecialization(EducationSpecializationTypes type)
			throws DataTypeException {
		if(type.equals(EducationSpecializationTypes.COLLEGE_FACULTY))
			return new CollegeFaculty();
		else if(type.equals(EducationSpecializationTypes.SCHOOL_MAIN_SPECIALIZATION))
			return new SchoolMainSpecialization();
		else
			throw new DataTypeException("Unknown type!");
	}
}
