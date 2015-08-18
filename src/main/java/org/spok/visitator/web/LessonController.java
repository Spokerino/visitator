package org.spok.visitator.web;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.spok.visitator.data.EducationGroupRepository;
import org.spok.visitator.data.EducationSpecializationRepository;
import org.spok.visitator.data.LessonRepository;
import org.spok.visitator.data.StudentRepository;
import org.spok.visitator.data.SubjectRepository;
import org.spok.visitator.data.TeacherRepository;
import org.spok.visitator.data.enum_types.CollegeFacultyGroup;
import org.spok.visitator.education.Subject;
import org.spok.visitator.factories.LessonFactory;
import org.spok.visitator.institution.CollegeGroup;
import org.spok.visitator.institution.EducationGroup;
import org.spok.visitator.institution.EducationSpecialization;
import org.spok.visitator.lesson.Lesson;
import org.spok.visitator.lesson.Mark;
import org.spok.visitator.lesson.Markable;
import org.spok.visitator.person.CollegeStudent;
import org.spok.visitator.person.Student;
import org.spok.visitator.person.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/colleges/{collegeId}")
public class LessonController {

	private LessonRepository lessonRepository;
	private SubjectRepository subjectRepository;
	private StudentRepository studentRepository;
	private TeacherRepository teacherRepository;
	private EducationSpecializationRepository facultyRepository;
	private EducationGroupRepository groupRepository;
	
	@Autowired
	public LessonController(LessonRepository lessonRepository,
							SubjectRepository subjectRepository,
							StudentRepository studentRepository,
							TeacherRepository teacherRepository,
							EducationSpecializationRepository facultyRepository,
							EducationGroupRepository groupRepository) {
		this.lessonRepository = lessonRepository;
		this.subjectRepository = subjectRepository;
		this.studentRepository = studentRepository;
		this.teacherRepository = teacherRepository;
		this.facultyRepository = facultyRepository;
		this.groupRepository = groupRepository;
	}
	
	@RequestMapping(value="/lessons", method=RequestMethod.GET)
	public ModelAndView showCollegeLessons(@PathVariable Integer collegeId){
  		
		ModelAndView model = new ModelAndView("college/collegeLessons");
		List<Lesson> lessons = lessonRepository.findLessons(CollegeFacultyGroup.COLLEGE,
															collegeId);
		
		for(Lesson l : lessons) {
			l.setStudents(lessonRepository.findStudentsOnLesson(l.getId()));
		}
		
		Collections.sort(lessons);
		model.addObject("lessonList", lessons);
		
		return model;
	}
	
	@RequestMapping(value="/faculties/{facultyId}/lessons", method=RequestMethod.GET)
	public ModelAndView showFacultyLessons(@PathVariable Integer collegeId,
										   @PathVariable Long facultyId){
  		
		ModelAndView model = new ModelAndView("faculty/facultyLessons");
		
		List<Lesson> lessons = lessonRepository.findLessons(CollegeFacultyGroup.FACULTY,
															facultyId);
		
		for(Lesson l : lessons) {
			l.setStudents(lessonRepository.findStudentsOnLesson(l.getId()));
		}
		
		
		Collections.sort(lessons);
		model.addObject("lessonList", lessons);
		
		return model;
	}
	
	@RequestMapping(value="/faculties/{facultyId}/groups/{groupId}/lessons", method=RequestMethod.GET)
	public ModelAndView showGroupLessons(@PathVariable Integer collegeId,
			   							 @PathVariable Long facultyId,
			   							 @PathVariable Long groupId){
  		
		ModelAndView model = new ModelAndView("group/groupLessons");
		
		CollegeGroup group = (CollegeGroup) groupRepository.findGroupById(groupId);
		List<Lesson> lessons = lessonRepository.findLessons(CollegeFacultyGroup.GROUP,
															groupId);
		
		for(Lesson l : lessons) {
			l.setStudents(lessonRepository.findStudentsOnLesson(l.getId()));
		}
		
		Collections.sort(lessons);
		model.addObject("lessonList", lessons);
		model.addObject("group", group);
		
		return model;
	}
	
	@RequestMapping(value="/lessons/{lessonId}", method=RequestMethod.GET)
	public ModelAndView showLessonCollege(@PathVariable Integer collegeId,
										  @PathVariable Long lessonId) {
									  		
		ModelAndView model = new ModelAndView("college/collegeLesson");
		Lesson lesson = lessonRepository.findLessonById(lessonId);
		List<EducationGroup> groups = lessonRepository.showLessonGroupsById(lessonId);
		List<Mark> marks = lessonRepository.showLessonMarks(lessonId);
		List<Student> students = lessonRepository.findStudentsOnLesson(lessonId);
		lesson.setGroups(groups);
		lesson.setStudents(students);
		model.addObject("lesson", lesson);
		model.addObject("marks", marks);
		
		return model;
	}
	
	@RequestMapping(value="faculties/{facultyId}/lessons/{lessonId}", method=RequestMethod.GET)
	public ModelAndView showLessonFaculty(@PathVariable Integer collegeId,
										  @PathVariable Long facultyId,
								   		  @PathVariable Long lessonId) {
									  		
		ModelAndView model = showLessonCollege(collegeId, lessonId);
		model.setViewName("faculty/facultyLesson");
		
		return model;
	}
	
	@RequestMapping(value="/lessons/{lessonId}/edit", method=RequestMethod.GET)
	public ModelAndView showEditableLessonCollege(@PathVariable Integer collegeId,
										  		  //@PathVariable Long facultyId,
										  		  @PathVariable Long lessonId) {
									  		
		ModelAndView model = showLessonCollege(collegeId, lessonId);
		model.setViewName("college/editCollegeLesson");
		
		return model;
	}
	
	@RequestMapping(value="faculties/{facultyId}/lessons/{lessonId}/edit", method=RequestMethod.GET)
	public ModelAndView showEditableLessonFaculty(@PathVariable Integer collegeId,
										  		  @PathVariable Long facultyId,
										  		  @PathVariable Long lessonId) {
									  		
		ModelAndView model = showLessonCollege(collegeId, lessonId);
		model.setViewName("faculty/editFacultyLesson");
		
		return model;
	}
	
	@RequestMapping(value="/lessons/{lessonId}/edit", method=RequestMethod.POST)
	public ModelAndView showEditedLessonCollege(@PathVariable Integer collegeId,
										  		@PathVariable Long lessonId,
										  		@RequestParam(required=false, value="absence") Long[] absenceChange,
												@RequestParam(required=false, value="marks") String[] freshMarks,
												@RequestParam(required=false, value="check") String isDeleted) {
		
		if(isDeleted == null) 
		{
			List<Student> absentStudents = lessonRepository.findStudentsOnLesson(lessonId);
			List<Long> modifiedStudentsId = new ArrayList<>();
			
			if(absenceChange != null) {
				for(Long l : absenceChange)
					modifiedStudentsId.add(l);
				
				for(Student s : absentStudents) {
					for(Long l : absenceChange) {
						if(s.getId().equals(l)) {
							lessonRepository.removeStudentFromLesson(lessonId, l);
							modifiedStudentsId.remove(l);
						}
					}
				}
				
				for(Long l : modifiedStudentsId) {
					lessonRepository.addStudentToLesson(lessonId, l);
				}
			}
			
			if(freshMarks != null) {
				List<Mark> marksFromDb = lessonRepository.showLessonMarks(lessonId);
				
				for(String s : freshMarks) {
					boolean isMatch = false;
					Long id = Long.parseLong(s.split(" ")[0]);
					for(Mark m : marksFromDb) {
						if(m.getStudent().getId().equals(id)) {
							isMatch = true;
							if(s.split(" ").length > 1) {
								int freshMark = Integer.parseInt(s.split(" ")[1]);
								if(m.getMark() != freshMark)
									lessonRepository.updateMark(lessonId, id, freshMark);
							}
							else
								lessonRepository.deleteMark(lessonId, id);
						}
					}
					if(!isMatch && s.split(" ").length > 1 && !modifiedStudentsId.contains(id))
						lessonRepository.createMark(lessonId, id, Integer.parseInt(s.split(" ")[1]));
				}
			}
		
			ModelAndView model = showEditableLessonCollege(collegeId, lessonId); 
			
			return model;
		}
		else {
			lessonRepository.deleteLesson(lessonId);
			ModelAndView model = showCollegeLessons(collegeId);
			return model;
		}
		
	}
	
	@RequestMapping(value="faculties/{facultyId}/lessons/{lessonId}/edit", method=RequestMethod.POST)
	public ModelAndView showEditedLessonFaculty(@PathVariable Integer collegeId,
										  		@PathVariable Long facultyId,
										  		@PathVariable Long lessonId,
										  		@RequestParam(required=false, value="absence") Long[] absenceChange,
												@RequestParam(required=false, value="marks") String[] freshMarks,
												@RequestParam(required=false, value="check") String isDeleted) {
		
		if(isDeleted == null) 
		{
			List<Student> absentStudents = lessonRepository.findStudentsOnLesson(lessonId);
			List<Long> modifiedStudentsId = new ArrayList<>();
			
			if(absenceChange != null) {
				for(Long l : absenceChange)
					modifiedStudentsId.add(l);
				
				for(Student s : absentStudents) {
					for(Long l : absenceChange) {
						if(s.getId().equals(l)) {
							lessonRepository.removeStudentFromLesson(lessonId, l);
							modifiedStudentsId.remove(l);
						}
					}
				}
				
				for(Long l : modifiedStudentsId) {
					lessonRepository.addStudentToLesson(lessonId, l);
				}
			}
			
			if(freshMarks != null) {
				List<Mark> marksFromDb = lessonRepository.showLessonMarks(lessonId);
				
				for(String s : freshMarks) {
					boolean isMatch = false;
					Long id = Long.parseLong(s.split(" ")[0]);
					for(Mark m : marksFromDb) {
						if(m.getStudent().getId().equals(id)) {
							isMatch = true;
							if(s.split(" ").length > 1) {
								int freshMark = Integer.parseInt(s.split(" ")[1]);
								if(m.getMark() != freshMark)
									lessonRepository.updateMark(lessonId, id, freshMark);
							}
							else
								lessonRepository.deleteMark(lessonId, id);
						}
					}
					if(!isMatch && s.split(" ").length > 1 && !modifiedStudentsId.contains(id))
						lessonRepository.createMark(lessonId, id, Integer.parseInt(s.split(" ")[1]));
				}
			}
		
			ModelAndView model = showEditableLessonFaculty(collegeId, facultyId, lessonId);
			
			return model;
			
		} else {
			lessonRepository.deleteLesson(lessonId);
			ModelAndView model = showFacultyLessons(collegeId, facultyId);
			model.setViewName("redirect:/colleges/{collegeId}/faculties/{facultyId}/lessons");
			return model;
		}
	}
	
	@RequestMapping(value="faculties/{facultyId}/groups/{groupId}/lessons/{lessonId}", method=RequestMethod.GET)
	public ModelAndView showLessonGroup(@PathVariable Integer collegeId,
										  @PathVariable Long facultyId,
										  @PathVariable Long groupId,
								   		  @PathVariable Long lessonId) {
		
		CollegeGroup group = (CollegeGroup) groupRepository.findGroupById(groupId);
		ModelAndView model = showLessonFaculty(collegeId, facultyId, lessonId);
		model.setViewName("group/groupLesson");
		model.addObject("group", group);
		
		return model;
	}
	
	@RequestMapping(value="faculties/{facultyId}/groups/{groupId}/lessons/{lessonId}/edit", method=RequestMethod.GET)
	public ModelAndView showEditableLessonGroup(@PathVariable Integer collegeId,
										  @PathVariable Long facultyId,
										  @PathVariable Long groupId,
								   		  @PathVariable Long lessonId) {
		
		CollegeGroup group = (CollegeGroup) groupRepository.findGroupById(groupId);
		ModelAndView model = showEditableLessonFaculty(collegeId, facultyId, lessonId);
		model.setViewName("group/editGroupLesson");
		model.addObject("group", group);
		
		return model;
	}
	
	@RequestMapping(value="faculties/{facultyId}/groups/{groupId}/lessons/{lessonId}/edit", method=RequestMethod.POST)
	public ModelAndView showEditedLessonGroup(@PathVariable Integer collegeId,
										  		@PathVariable Long facultyId,
										  		@PathVariable Long lessonId,
										  		@PathVariable Long groupId,
										  		@RequestParam(required=false, value="absence") Long[] absenceChange,
												@RequestParam(required=false, value="marks") String[] freshMarks,
												@RequestParam(required=false, value="check") String isDeleted) {
		
		if(isDeleted == null) 
		{	
			CollegeGroup group = (CollegeGroup) groupRepository.findGroupById(groupId);
			ModelAndView model = showEditedLessonFaculty(collegeId, facultyId, lessonId, absenceChange, freshMarks, isDeleted);
			model.setViewName("group/editGroupLesson");
			model.addObject("group", group);
			return model;
		} else {
			lessonRepository.deleteLesson(lessonId);
			ModelAndView model = showGroupLessons(collegeId, facultyId, groupId);
			model.setViewName("redirect:/colleges/{collegeId}/faculties/{facultyId}/groups/{groupId}/lessons");
			return model;
		}
	}
	
  	@RequestMapping(value="/lessons", method=RequestMethod.POST)
  	public ModelAndView collegeLessonsForDate(@RequestParam("date") String date,
  									   		  @PathVariable Integer collegeId){
  		
  		ModelAndView model = new ModelAndView("college/collegeLessons");
  		List<Lesson> lessons = lessonRepository.findLessonsForDate(
  										CollegeFacultyGroup.COLLEGE,
  										collegeId, date);
  				
  		if(lessons.size() == 0)
  			lessons = lessonRepository.findLessons(CollegeFacultyGroup.COLLEGE,
  												   collegeId);
  		
  		for(Lesson l : lessons) {
			l.setStudents(lessonRepository.findStudentsOnLesson(l.getId()));
		}
  		
  		Collections.sort(lessons);
  		model.addObject("lessonList", lessons);
  			
  		return model;  			
  	}
  	
  	@RequestMapping(value="/faculties/{facultyId}/lessons", method=RequestMethod.POST)
  	public ModelAndView facultyLessonsForDate(@RequestParam("date") String date,
  			 								  @PathVariable Integer collegeId,
  									   		  @PathVariable Long facultyId){
  		
  		ModelAndView model = new ModelAndView("faculty/facultyLessons");
  		List<Lesson> lessons = lessonRepository.findLessonsForDate(
  										CollegeFacultyGroup.FACULTY,
  										facultyId, date);
  				
  		if(lessons.size() == 0)
  			lessons = lessonRepository.findLessons(CollegeFacultyGroup.FACULTY,
  												   facultyId);
  		
  		for(Lesson l : lessons) {
			l.setStudents(lessonRepository.findStudentsOnLesson(l.getId()));
		}
  		Collections.sort(lessons);  			
  		model.addObject("lessonList", lessons);
  			
  		return model;  			
  	}
  	
  	@RequestMapping(value="/faculties/{facultyId}/groups/{groupId}/lessons", method=RequestMethod.POST)
  	public ModelAndView groupLessonsForDate(@RequestParam("date") String date,
								  			@PathVariable Integer collegeId,
									   		@PathVariable Long facultyId,
  									   		@PathVariable Long groupId){
  		
  		ModelAndView model = new ModelAndView("group/groupLessons");
  		List<Lesson> lessons = lessonRepository.findLessonsForDate(
  										CollegeFacultyGroup.GROUP,
  										groupId, date);
  				
  		if(lessons.size() == 0)
  			lessons = lessonRepository.findLessons(CollegeFacultyGroup.GROUP,
  												   groupId);
  		
  		for(Lesson l : lessons) {
			l.setStudents(lessonRepository.findStudentsOnLesson(l.getId()));
		}
  		Collections.sort(lessons);  			
  		model.addObject("lessonList", lessons);
  		  			
  		return model;  			
  	}
  	
  	@RequestMapping(value="/faculties/{facultyId}/groups/{groupId}/students/{studentId}", method=RequestMethod.POST)
  	public ModelAndView groupStudentLessonsForDate(@RequestParam("date") String date,
								  				@PathVariable Integer collegeId,
								  				@PathVariable Long facultyId,
								  				@PathVariable Long groupId,
								  				@PathVariable Long studentId){
  		
  		ModelAndView model = new ModelAndView("group/groupStudent");
  		List<Lesson> lessons = lessonRepository.findLessonsForDate(
  										CollegeFacultyGroup.GROUP,
  										groupId, date);
  				
  		if(lessons.size() == 0)
  			lessons = lessonRepository.findLessons(CollegeFacultyGroup.GROUP,
  												   groupId);
  		
  		for(Lesson l : lessons) {
			l.setStudents(lessonRepository.findStudentsOnLesson(l.getId()));
			if(l instanceof Markable) {
				((Markable) l).setMarks(lessonRepository.showLessonMarks(l.getId()));
			}
		}
  		Collections.sort(lessons);  			
  		CollegeStudent student = (CollegeStudent) studentRepository.findStudentById(studentId);
  		
  		model.addObject("lessonList", lessons);
  		model.addObject("student", student);

  		return model;  			
  	}
  	
  	@RequestMapping(value="lessons/new", method=RequestMethod.GET)
	public ModelAndView newLessonFromCollege(@PathVariable Integer collegeId){
  		
		ModelAndView model = new ModelAndView("college/collegeLessonForm");
	
		
		List<EducationSpecialization> faculties = facultyRepository.findAllFaculties(collegeId);
		List<Teacher> teachers = teacherRepository.getCollegeTeachers(collegeId);

		model.addObject("faculties", faculties);
		model.addObject("teachers", teachers);
		model.addObject("duration", Lesson.getLessonDuration());
				
		return model;
	}
  	
  	@RequestMapping(value="faculties/{facultyId}/lessons/new", method=RequestMethod.GET)
	public ModelAndView newLessonFromFaculty(@PathVariable Integer collegeId,
								  			 @PathVariable Long facultyId){
  		
		ModelAndView model = new ModelAndView("faculty/facultyLessonForm");
	
		EducationSpecialization faculty = facultyRepository.findFacultyById(facultyId);
		List<Subject> subjects = subjectRepository.getFacultySubjects(facultyId);
		List<Teacher> teachers = teacherRepository.getCollegeTeachers(collegeId);
		List<EducationGroup> groups = groupRepository.findAllFacultyGroups(facultyId);
		faculty.setSubjects(subjects);
		faculty.setTeachers(teachers);
		faculty.setGroups(groups);
		
		model.addObject("faculty", faculty);
		model.addObject("duration", Lesson.getLessonDuration());
				
		return model;
	}
  	
  	@RequestMapping(value="faculties/{facultyId}/groups/{groupId}/lessons/new", method=RequestMethod.GET)
	public ModelAndView newLessonFromGroup(@PathVariable Integer collegeId,
								  		   @PathVariable Long facultyId,
								  		   @PathVariable Long groupId){
  		
		ModelAndView model = newLessonFromFaculty(collegeId, facultyId);
		model.setViewName("group/groupLessonForm");	
		
		return model;
	}
  	
  	@RequestMapping(value="/lessons/new", method=RequestMethod.POST)
	public ModelAndView saveLessonFromCollege(@PathVariable Integer collegeId,
							 @RequestParam("faculties") Long facultyId,
							 @RequestParam("date") String lessonDate,
							 @RequestParam("start") String lessonStart,
							 @RequestParam("end") String lessonEnd,
							 @RequestParam("subjects") String subjectName,
							 @RequestParam("teachers") Long teacherId,
							 @RequestParam("types") int type,
							 @RequestParam("groups") String[] groupNames){
			
  		Lesson lesson = LessonFactory.createLesson(type);
  		Subject subject = subjectRepository.getSubjectForName(subjectName);
  		Teacher teacher = teacherRepository.getTeacherForId(teacherId);
  		
  		SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
		
		Date date= null, start = null, end = null;
				
		try {
			date = dateTimeFormat.parse(lessonDate);
			start = timeFormat.parse(lessonStart);
			end = timeFormat.parse(lessonEnd);
		} catch (ParseException e) {
			e.printStackTrace();
		}
  		
  		lesson.setDate(date);
  		lesson.setStart(start);
  		lesson.setEnd(end);
  		lesson.setSubject(subject);
  		lesson.setTeacher(teacher);
  		lessonRepository.saveToLesson(lesson, type);
  		
  		Long lessonId = lessonRepository.getLastLesson().getId();
  		for(String s : groupNames) {
  			EducationGroup group = groupRepository.findGroupByName(facultyId, s);
  			lessonRepository.saveToLessonGroup(lessonId, group.getId());
  			List<Student> students = studentRepository.findAllStudentsInGroup(group.getId());
  				for(Student st : students)
  					lessonRepository.addStudentToLesson(lessonId, st.getId());
  		}
  		
  		
  		
  		ModelAndView model = new ModelAndView();
  		model = showLessonCollege(collegeId, lessonId);
  		
  		
		return model;
	}
  	
  	@RequestMapping(value="faculties/{facultyId}/lessons/new", method=RequestMethod.POST)
	public ModelAndView saveLessonFromFaculty(@PathVariable Integer collegeId,
							 @PathVariable Long facultyId,
							 @RequestParam("date") String lessonDate,
							 @RequestParam("start") String lessonStart,
							 @RequestParam("end") String lessonEnd,
							 @RequestParam("subjects") Integer subjectId,
							 @RequestParam("teachers") Long teacherId,
							 @RequestParam("types") int type,
							 @RequestParam("groups") Long[] groupIds){
			
  		Lesson lesson = LessonFactory.createLesson(type);
  		Subject subject = subjectRepository.getSubjectForId(subjectId);
  		Teacher teacher = teacherRepository.getTeacherForId(teacherId);
  		
  		SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
		
		Date date= null, start = null, end = null;
				
		try {
			date = dateTimeFormat.parse(lessonDate);
			start = timeFormat.parse(lessonStart);
			end = timeFormat.parse(lessonEnd);
		} catch (ParseException e) {
			e.printStackTrace();
		}
  		
  		lesson.setDate(date);
  		lesson.setStart(start);
  		lesson.setEnd(end);
  		lesson.setSubject(subject);
  		lesson.setTeacher(teacher);
  		lessonRepository.saveToLesson(lesson, type);
  		
  		Long lessonId = lessonRepository.getLastLesson().getId();
  		for(Long g : groupIds) {
  			lessonRepository.saveToLessonGroup(lessonId, g);
  			List<Student> students = studentRepository.findAllStudentsInGroup(g);
				for(Student st : students)
					lessonRepository.addStudentToLesson(lessonId, st.getId());
  		}
  		
  		ModelAndView model = new ModelAndView();
  		model = showLessonFaculty(collegeId, facultyId, lessonId);
  		
  		
		return model;
	}
  					
  	@RequestMapping(value="faculties/{facultyId}/groups/{groupId}/lessons/new", method=RequestMethod.POST)
	public ModelAndView saveLessonFromGroup(@PathVariable Integer collegeId,
											 @PathVariable Long facultyId,
											 @PathVariable Long groupId,
											 @RequestParam("date") String lessonDate,
											 @RequestParam("start") String lessonStart,
											 @RequestParam("end") String lessonEnd,
											 @RequestParam("subjects") Integer subjectId,
											 @RequestParam("teachers") Long teacherId,
											 @RequestParam("types") int type){
			
  		Lesson lesson = LessonFactory.createLesson(type);
  		Subject subject = subjectRepository.getSubjectForId(subjectId);
  		Teacher teacher = teacherRepository.getTeacherForId(teacherId);
  		
  		SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
		
		Date date= null, start = null, end = null;
				
		try {
			date = dateTimeFormat.parse(lessonDate);
			start = timeFormat.parse(lessonStart);
			end = timeFormat.parse(lessonEnd);
		} catch (ParseException e) {
			e.printStackTrace();
		}
  		
  		lesson.setDate(date);
  		lesson.setStart(start);
  		lesson.setEnd(end);
  		lesson.setSubject(subject);
  		lesson.setTeacher(teacher);
  		lessonRepository.saveToLesson(lesson, type);
  		
  		Long lessonId = lessonRepository.getLastLesson().getId();
  		lessonRepository.saveToLessonGroup(lessonId, groupId);
  		List<Student> students = studentRepository.findAllStudentsInGroup(groupId);
		for(Student st : students)
			lessonRepository.addStudentToLesson(lessonId, st.getId());
  		
  		ModelAndView model = new ModelAndView();
  		model = showLessonGroup(collegeId, facultyId, groupId, lessonId);
  		
  		
		return model;
	}
	
}
