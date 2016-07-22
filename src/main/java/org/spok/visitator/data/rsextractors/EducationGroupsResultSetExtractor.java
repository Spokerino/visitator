package org.spok.visitator.data.rsextractors;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import org.spok.visitator.entities.enum_types.EducationGroupTypes;
import org.spok.visitator.entities.enum_types.EducationInstitutionTypes;
import org.spok.visitator.entities.enum_types.EducationSpecializationTypes;
import org.spok.visitator.entities.enum_types.StudentTypes;
import org.spok.visitator.data.rowmappers.EducationGroupRowMapper;
import org.spok.visitator.data.rowmappers.StudentRowMapper;
import org.spok.visitator.entities.institution.EducationGroup;
import org.spok.visitator.entities.person.Student;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

public class EducationGroupsResultSetExtractor implements ResultSetExtractor<List<EducationGroup>>{

	private EducationInstitutionTypes institutionType;
	private EducationSpecializationTypes specializationType;
	private EducationGroupTypes groupType;
	
	public EducationGroupsResultSetExtractor(
			   EducationInstitutionTypes institutionType,
			   EducationSpecializationTypes specializationType,
			   EducationGroupTypes groupType) {
		
		this.institutionType = institutionType;
		this.specializationType = specializationType;
		this.groupType = groupType;
	}


	@Override
	public List<EducationGroup> extractData(ResultSet rs) throws SQLException,
			DataAccessException {
		
		Map<Long, EducationGroup> groups = new LinkedHashMap<>();
		int rowNum = 0;
		
		while(rs.next()) {
			
			Long groupId = rs.getLong("groupId");
			EducationGroup group = groups.get(groupId);
			if(group == null) {
				group = new EducationGroupRowMapper(
							institutionType,
							specializationType,
							groupType).mapRow(rs, rowNum);
				groups.put(groupId, group);
			}
			
			List<Student> students = group.getStudents();
			if(students == null) {
				students = new ArrayList<Student>();
				group.setStudents(students);
			}
			
			Student student = new StudentRowMapper(StudentTypes.COLLEGE_STUDENT).mapRow(rs, rowNum);
			
			boolean same = false;
			
			for(Student st : students) {
				if(st.getId().equals(student.getId()))
					same = true;
			}
			
			if(!same) students.add(student);
			
		}
		
		
		List<EducationGroup> groupList = new ArrayList<EducationGroup>(groups.values());
		
		return groupList;
	}

	
}
