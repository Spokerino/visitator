package org.spok.visitator.data.rsextractors;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import org.spok.visitator.entities.enum_types.EducationInstitutionTypes;
import org.spok.visitator.entities.enum_types.EducationSpecializationTypes;
import org.spok.visitator.entities.enum_types.TeacherTypes;
import org.spok.visitator.data.rowmappers.SubjectRowMapper;
import org.spok.visitator.data.rowmappers.TeacherRowMapper;
import org.spok.visitator.entities.lesson.Subject;
import org.spok.visitator.entities.person.Teacher;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

public class TeachersResultSetExtractor implements ResultSetExtractor<List<Teacher>>{

	private EducationInstitutionTypes institutionType;
	private EducationSpecializationTypes specizalizationType;
	private TeacherTypes teacherType;
	
	public TeachersResultSetExtractor(TeacherTypes type,
							EducationInstitutionTypes institutionType,
							EducationSpecializationTypes specizalizationType) {
		
		this.teacherType = type;
		this.institutionType = institutionType;
		this.specizalizationType = specizalizationType;
	}

	@Override
	public List<Teacher> extractData(ResultSet rs) throws SQLException, DataAccessException {
		
		Map<Long, Teacher> teachers = new LinkedHashMap<>();
		
			
		while(rs.next()) {
			
			Long teacherId = rs.getLong("teacherId");
			Teacher teacher = teachers.get(teacherId);
			if(teacher == null) {
				teacher = new TeacherRowMapper(teacherType,
											   institutionType,
											   specizalizationType).mapRow(rs, 0);
				
				teachers.put(teacherId, teacher);
			}
			
			Subject subject = new SubjectRowMapper().mapRow(rs, 0);
			teacher.addSubject(subject);
	
		}
		
		List<Teacher> teachersList = new ArrayList<Teacher>(teachers.values());
		
		return teachersList;
	}

}
