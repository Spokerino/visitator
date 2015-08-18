package org.spok.visitator.data.rsextractors;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.spok.visitator.data.enum_types.EducationGroupTypes;
import org.spok.visitator.data.enum_types.EducationInstitutionTypes;
import org.spok.visitator.data.enum_types.EducationSpecializationTypes;
import org.spok.visitator.data.enum_types.TeacherTypes;
import org.spok.visitator.data.rowmappers.EducationGroupRowMapper;
import org.spok.visitator.data.rowmappers.EducationSpecializationRowMapper;
import org.spok.visitator.data.rowmappers.SubjectRowMapper;
import org.spok.visitator.data.rowmappers.TeacherRowMapper;
import org.spok.visitator.educ_person.Teacher;
import org.spok.visitator.education.Subject;
import org.spok.visitator.institution.EducationGroup;
import org.spok.visitator.institution.EducationSpecialization;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

public class EducationSpecializationsResultSetExtractor implements ResultSetExtractor<List<EducationSpecialization>>{

	private EducationInstitutionTypes institutionType;
	private EducationSpecializationTypes specializationType;
	private EducationGroupTypes groupType;
	private TeacherTypes teacherType;
	
	public EducationSpecializationsResultSetExtractor(
			EducationInstitutionTypes institutionType,
			EducationSpecializationTypes specializationType,
			EducationGroupTypes groupType,
			TeacherTypes teacherType) {
		this.institutionType = institutionType;
		this.specializationType = specializationType;
		this.groupType = groupType;
		this.teacherType = teacherType;
	}

	@Override
	public List<EducationSpecialization> extractData(ResultSet rs) throws SQLException, DataAccessException {
		
		Map<Long, EducationSpecialization> specializations = new HashMap<Long, EducationSpecialization>();
		
		int noOneCaresAboutMe = 0;

		while(rs.next()) {
			
			Long specializationId = rs.getLong("facultyId");
			EducationSpecialization specialization = specializations.get(specializationId);
			if(specialization == null) 
			{				
				specialization = new EducationSpecializationRowMapper(institutionType,
					specializationType)
					.mapRow(rs, noOneCaresAboutMe);
				specializations.put(specializationId, specialization);
			}
			
						
				List<Teacher> teacherList = specialization.getTeachers();
				if(teacherList == null) {
					teacherList = new ArrayList<Teacher>();
					specialization.setTeachers(teacherList);
				} 
				
				Teacher teacher = new TeacherRowMapper(teacherType,
														institutionType,
														specializationType).mapRow(rs, noOneCaresAboutMe);
				
				boolean same = false;
				for(Teacher t : teacherList) {
				if(teacher.getId().equals(t.getId()))
					same = true;
				}
				if (!same) teacherList.add(teacher);
				
				//
				List<Subject> subjectList = specialization.getSubjects();
				if(subjectList == null) {
					subjectList = new ArrayList<Subject>();
					specialization.setSubjects(subjectList);
				} 
				
				Subject subject = new SubjectRowMapper().mapRow(rs, noOneCaresAboutMe);
				
				same = false;
				for(Subject s : subjectList) {
				if(subject.getId().equals(s.getId()))
					same = true;
				}
				if (!same) subjectList.add(subject);
				//
				
				List<EducationGroup> groupList = specialization.getGroups();
				if(groupList == null) {
					groupList = new ArrayList<EducationGroup>();
					specialization.setGroups(groupList);
				}
				
				EducationGroup group = new EducationGroupRowMapper(
						institutionType,
						specializationType,
						groupType).mapRow(rs, noOneCaresAboutMe);
				
				same = false;
				for(EducationGroup g : groupList) {
					if(group.getId().equals(g.getId()))
						same = true;
				}
				if (!same) specialization.addGroup(group);
			
			
		}	
		
		List<EducationSpecialization> specializationsList = 
				new ArrayList<EducationSpecialization>(specializations.values());
		
		return specializationsList;
	}

}
