package org.spok.visitator.data.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.spok.visitator.data.enum_types.EducationInstitutionTypes;
import org.spok.visitator.data.enum_types.EducationSpecializationTypes;
import org.spok.visitator.data.enum_types.TeacherTypes;
import org.spok.visitator.factories.TeacherFactory;
import org.spok.visitator.person.Teacher;
import org.springframework.jdbc.core.RowMapper;

public class TeacherRowMapper implements RowMapper<Teacher> {
	 
	private EducationInstitutionTypes institutionType;
	private EducationSpecializationTypes specizalizationType;
	private TeacherTypes teacherType;
	
	public TeacherRowMapper(TeacherTypes type,
							EducationInstitutionTypes institutionType,
							EducationSpecializationTypes specizalizationType) {
		
		this.teacherType = type;
		this.institutionType = institutionType;
		this.specizalizationType = specizalizationType;
	}

	public Teacher mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Teacher t = TeacherFactory.createTeacher(teacherType);
		
		      t.setId(rs.getLong("teacherId"));
	          t.setFirstName(rs.getString("teacherFirstName"));
	          t.setLastName(rs.getString("teacherLastName"));
	          t.setGender(rs.getInt("teacherGender"));
	          t.setBirthday(rs.getDate("teacherBirthday"));
	          t.setSpecialization(new EducationSpecializationRowMapper(
	        		  institutionType,
	        		  specizalizationType)
	        		  .mapRow(rs, rowNum));
	          t.setEmail(rs.getString("email"));
	          
	          
//	          if(rs.getLong("account_id") != 0) {
//		          Account account = new AccountRowMapper().mapRow(rs, rowNum);
//		          t.setAccount(account);
//		          t.setInstitution(new College());
//	          }
	         
	    return t;
	}
}
