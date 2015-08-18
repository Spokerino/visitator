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
import org.spok.visitator.data.enum_types.StudentTypes;
import org.spok.visitator.data.rowmappers.EducationGroupRowMapper;
import org.spok.visitator.data.rowmappers.StudentRowMapper;
import org.spok.visitator.educ_person.Student;
import org.spok.visitator.institution.EducationGroup;
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
		
		Map<Long, EducationGroup> groups = new HashMap<Long, EducationGroup>();
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
