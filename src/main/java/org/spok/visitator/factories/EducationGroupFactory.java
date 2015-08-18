package org.spok.visitator.factories;

import org.spok.visitator.data.enum_types.EducationGroupTypes;
import org.spok.visitator.institution.CollegeGroup;
import org.spok.visitator.institution.EducationGroup;

public class EducationGroupFactory {

	public static EducationGroup createGroup(EducationGroupTypes type){
		if(type.equals(EducationGroupTypes.COLLEGE_GROUP))
			return new CollegeGroup();
		else 
			return null;
	}
}
