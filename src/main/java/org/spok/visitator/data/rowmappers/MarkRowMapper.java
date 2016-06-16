package org.spok.visitator.data.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.spok.visitator.entities.enum_types.EducationInstitutionTypes;
import org.spok.visitator.entities.enum_types.EducationSpecializationTypes;
import org.spok.visitator.entities.enum_types.StudentTypes;
import org.spok.visitator.entities.enum_types.TeacherTypes;
import org.spok.visitator.entities.lesson.Mark;
import org.spok.visitator.entities.lesson.Markable;
import org.springframework.jdbc.core.RowMapper;

public class MarkRowMapper implements RowMapper<Mark>{
	
	private EducationInstitutionTypes institutionType;
	private EducationSpecializationTypes specizalizationType;
	private TeacherTypes teacherType;
	private StudentTypes studentType;
	
	public MarkRowMapper(TeacherTypes teacherType,
						 StudentTypes studentType,
						 EducationInstitutionTypes institutionType,
						 EducationSpecializationTypes specizalizationType) {
		
		this.teacherType = teacherType;
		this.studentType = studentType;
		this.institutionType = institutionType;
		this.specizalizationType = specizalizationType;
	}


	@Override
	public Mark mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Mark mark = new Mark();
		
		Markable lesson = (Markable) new LessonRowMapper(teacherType,
														 institutionType,
														 specizalizationType).mapRow(rs, rowNum);
		mark.setLesson(lesson);
		mark.setStudent(new StudentRowMapper(studentType).mapRow(rs, rowNum));
		mark.setMark(rs.getInt("mark"));
		
		return mark;
	}
	
}
