package org.spok.visitator.data;

import java.util.List;

import org.spok.visitator.institution.College;
import org.spok.visitator.institution.EducationInstitution;

public interface EducationInstitutionRepository {

	List<EducationInstitution> findAllInstitutions();
	College findCollegeById(Integer collegeId);
	EducationInstitution findCollegeByName(String name);
	void createCollege(College college);
	void updateCollege(College college);
	void deleteCollege(Integer collegeId);

}
