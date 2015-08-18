package org.spok.visitator.data.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.spok.visitator.data.enum_types.EducationGroupTypes;
import org.spok.visitator.data.enum_types.EducationInstitutionTypes;
import org.spok.visitator.data.enum_types.EducationSpecializationTypes;
import org.spok.visitator.data.enum_types.StudentTypes;
import org.spok.visitator.educ_person.Student;
import org.spok.visitator.factories.StudentFactory;
import org.springframework.jdbc.core.RowMapper;

public class StudentRowMapper implements RowMapper<Student>{
	
	private StudentTypes type;
	
	public StudentRowMapper(StudentTypes type) {
		this.type = type;
	}

	public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Student student = StudentFactory.createStudent(type);
		
		student.setId(rs.getLong("id"));
//		student.setUsername(rs.getString("userName"));
//		student.setPassword(null);
		student.setFirstName(rs.getString("firstName"));
		student.setLastName(rs.getString("lastName"));
		student.setGender(rs.getInt("gender"));
		student.setBirthday(rs.getDate("birthday"));
//		student.setEmail(rs.getString("email"));
	        	       
		student.setGroup(new EducationGroupRowMapper(
				EducationInstitutionTypes.COLLEGE,
				EducationSpecializationTypes.COLLEGE_FACULTY,
				EducationGroupTypes.COLLEGE_GROUP).mapRow(rs, rowNum));
		
	    return student;
	    }
}
