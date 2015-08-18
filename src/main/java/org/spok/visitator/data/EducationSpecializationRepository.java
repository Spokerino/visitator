package org.spok.visitator.data;

import java.util.List;

import org.spok.visitator.institution.CollegeFaculty;
import org.spok.visitator.institution.EducationSpecialization;

public interface EducationSpecializationRepository {

	List<EducationSpecialization> findAllFaculties(Integer collegeId);
	EducationSpecialization findFacultyById(Long facultyId);
	void deleteFaculty(Long facultyId);
	void updateFaculty(CollegeFaculty faculty);
	void createFaculty(CollegeFaculty faculty, Integer collegeId);

}
