package org.spok.visitator.factories;

import org.spok.visitator.DataTypeException;
import org.spok.visitator.entities.enum_types.EducationGroupTypes;
import org.spok.visitator.entities.institution.CollegeGroup;
import org.spok.visitator.entities.institution.EducationGroup;
import org.spok.visitator.entities.institution.ScoolClass;

public class EducationGroupFactory {

	public static EducationGroup createGroup(EducationGroupTypes type) throws DataTypeException {
		if(type.equals(EducationGroupTypes.COLLEGE_GROUP))
			return new CollegeGroup();
		else if(type.equals(EducationGroupTypes.SCHOOL_CLASS))
			return new ScoolClass();
		else
			throw new DataTypeException("Unknown type!");
	}
}
