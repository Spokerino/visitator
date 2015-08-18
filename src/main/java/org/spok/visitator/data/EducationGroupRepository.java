package org.spok.visitator.data;

import java.util.List;

import org.spok.visitator.institution.CollegeGroup;
import org.spok.visitator.institution.EducationGroup;

public interface EducationGroupRepository {

	List<EducationGroup> findAllFacultyGroups(Long facultyId);
	EducationGroup findGroupById(Long groupId);
	EducationGroup findGroupByName(Long facultyId, String groupName);
	void createGroup(CollegeGroup group, Long facultyId);
	void updateGroup(CollegeGroup group);
	void deleteGroup(Long groupId);
}
