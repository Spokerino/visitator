package org.spok.visitator.data.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.spok.visitator.entities.enum_types.EducationInstitutionTypes;
import org.spok.visitator.entities.enum_types.EducationSpecializationTypes;
import org.spok.visitator.entities.enum_types.TeacherTypes;
import org.spok.visitator.factories.LessonFactory;
import org.spok.visitator.entities.lesson.Lesson;
import org.springframework.jdbc.core.RowMapper;

public class LessonRowMapper implements RowMapper<Lesson>{

	private EducationInstitutionTypes institutionType;
	private EducationSpecializationTypes specizalizationType;
	private TeacherTypes teacherType;
	
	public LessonRowMapper(TeacherTypes teacherType,
							EducationInstitutionTypes institutionType,
							EducationSpecializationTypes specizalizationType) {
		
		this.teacherType = teacherType;
		this.institutionType = institutionType;
		this.specizalizationType = specizalizationType;
	}


	@Override
	public Lesson mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Lesson lesson = LessonFactory.createLesson(rs.getInt("lsnType"));
		
		lesson.setId(rs.getLong("lsnId"));
		lesson.setDate(rs.getDate("date"));
		lesson.setStart(rs.getTime("start"));
		lesson.setEnd(rs.getTime("end"));
		lesson.setSubject(new SubjectRowMapper().mapRow(rs, rowNum));
		lesson.setTeacher(new TeacherRowMapper(teacherType,
											   institutionType,
											   specizalizationType).mapRow(rs, rowNum));
		
		return lesson;
	}
	
	
	
}
