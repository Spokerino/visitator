package org.spok.visitator.data;

import java.util.List;

import org.spok.visitator.entities.institution.College;
import org.spok.visitator.entities.institution.EducationInstitution;

public interface EducationInstitutionRepository {

	List<EducationInstitution> findAllInstitutions();
	EducationInstitution findInstitutionById(Integer institutionId);
	EducationInstitution findInstitutionByName(String name);
	void createInstitution(EducationInstitution institution);
	void updateInstitution(EducationInstitution institution);
	void deleteInstitution(Integer institutionId);

}
