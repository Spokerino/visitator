package org.spok.visitator.data;

import java.util.List;
import java.util.Map;

import org.spok.visitator.entities.institution.CollegeGroup;
import org.spok.visitator.entities.institution.EducationGroup;

public interface EducationGroupRepository {

	List<EducationGroup> findAllFacultyGroups(Long facultyId);
	EducationGroup findGroupById(Long groupId);
	EducationGroup findGroupByName(Long facultyId, String groupName);
	void createGroup(CollegeGroup group, Long facultyId);
	void updateGroup(CollegeGroup group);
	void deleteGroup(Long groupId);
	Map<Long, String> getCollegeGroupsMap(Integer collegeId);
}
