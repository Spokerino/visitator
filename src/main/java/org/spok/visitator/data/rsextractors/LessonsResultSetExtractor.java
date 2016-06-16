package org.spok.visitator.data.rsextractors;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.spok.visitator.entities.enum_types.EducationGroupTypes;
import org.spok.visitator.entities.enum_types.EducationInstitutionTypes;
import org.spok.visitator.entities.enum_types.EducationSpecializationTypes;
import org.spok.visitator.entities.enum_types.StudentTypes;
import org.spok.visitator.entities.enum_types.TeacherTypes;
import org.spok.visitator.data.rowmappers.EducationGroupRowMapper;
import org.spok.visitator.data.rowmappers.LessonRowMapper;
import org.spok.visitator.data.rowmappers.StudentRowMapper;
import org.spok.visitator.entities.institution.EducationGroup;
import org.spok.visitator.entities.lesson.Lesson;
import org.spok.visitator.entities.person.Student;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

public class LessonsResultSetExtractor implements ResultSetExtractor<List<Lesson>>{

	private StudentTypes studentType;
	private TeacherTypes teacherType;
	private EducationInstitutionTypes institutionType;
	private EducationSpecializationTypes specializationType;
	private EducationGroupTypes groupType;
	
	public LessonsResultSetExtractor(StudentTypes studentType,
									 TeacherTypes teacherType,
									 EducationInstitutionTypes institutionType,
									 EducationSpecializationTypes specializationType,
									 EducationGroupTypes groupType) {
		this.studentType = studentType;
		this.teacherType = teacherType;
		this.institutionType = institutionType;
		this.specializationType = specializationType;
		this.groupType = groupType;
	}

	@Override
	public List<Lesson> extractData(ResultSet rs) throws SQLException,
			DataAccessException {

		Map<Long, Lesson> lessons = new HashMap<Long, Lesson>();
		int rowNum = 0;
		
		while(rs.next()) {
			
			Long lessonId = rs.getLong("lsnId");
			Lesson lesson = lessons.get(lessonId);
			if(lesson == null) {
				lesson = new LessonRowMapper(teacherType,
											 institutionType,
											 specializationType).mapRow(rs, rowNum);
				
				lessons.put(lessonId, lesson);
			}
			
			List<EducationGroup> groups = lesson.getGroups();
			if(groups == null) {
				groups = new ArrayList<EducationGroup>();
				lesson.setGroups(groups);
			}
			
			EducationGroup group = new EducationGroupRowMapper(
					institutionType,
					specializationType,
					groupType).mapRow(rs, rowNum);
			
			
			
			boolean same = false;
			
			for(EducationGroup g : groups){
				if(g.getId().equals(group.getId()))
					same = true;
			}
			
			if(!same) groups.add(group);

//			List<Student> students = lesson.getAbsentStudents();
//			if(students == null) {
//				students = new ArrayList<>();
//				lesson.setAbsentStudents(students);
//			}
//			
//			Student student = new StudentRowMapper(StudentTypes.COLLEGE_STUDENT).mapRow(rs, rowNum);
//			
//			same = false;
//			
//			for(Student s : students) {
//				if(s.getId().equals(student.getId()))
//					same = true;
//			}
//			
//			if(!same) students.add(student);
			
			
			
			for(EducationGroup g : groups) {
				List<Student> students = g.getStudents();
				if(students == null) {
					students = new ArrayList<>();
					group.setStudents(students);
				}
			
			
				Student student = new StudentRowMapper(studentType).mapRow(rs, rowNum);
			
				same = false;
				
				for(Student s : students) {
					if(s.getId().equals(student.getId()))
						same = true;
				}
				
				if(!same && student.getGroup().getId().equals(g.getId())) students.add(student);
			}
		}
		
		List<Lesson> lessonList = new ArrayList<>(lessons.values());
		
		return lessonList;
	}
	
	

}
