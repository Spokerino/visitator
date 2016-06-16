package org.spok.visitator.data;

import java.util.List;
import java.util.Map;

import org.spok.visitator.entities.institution.CollegeFaculty;
import org.spok.visitator.entities.institution.EducationSpecialization;

public interface EducationSpecializationRepository {

	List<EducationSpecialization> findAllFaculties(Integer collegeId);
	EducationSpecialization findFacultyById(Long facultyId);
	void deleteFaculty(Long facultyId);
	void updateFaculty(CollegeFaculty faculty);
	void createFaculty(CollegeFaculty faculty, Integer collegeId);
	Map getFacultiesMap(Integer collegeId);

}
