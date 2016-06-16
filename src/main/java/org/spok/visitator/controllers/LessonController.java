package org.spok.visitator.controllers;

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
import org.spok.visitator.entities.enum_types.CollegeFacultyGroup;
import org.spok.visitator.entities.lesson.*;
import org.spok.visitator.factories.LessonFactory;
import org.spok.visitator.entities.institution.CollegeGroup;
import org.spok.visitator.entities.institution.EducationGroup;
import org.spok.visitator.entities.institution.EducationSpecialization;
import org.spok.visitator.entities.person.CollegeStudent;
import org.spok.visitator.entities.person.Student;
import org.spok.visitator.entities.person.Teacher;
import org.spok.visitator.services.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("/colleges/{collegeId}")
public class LessonController {

	@Autowired
	private LessonService lessonService;

	@RequestMapping(value="/lessons", method=RequestMethod.GET)
	public ModelAndView showCollegeLessons(@PathVariable Integer collegeId){
		return lessonService.showLessons(collegeId, CollegeFacultyGroup.COLLEGE);
	}
	
	@RequestMapping(value="/faculties/{facultyId}/lessons", method=RequestMethod.GET)
	public ModelAndView showFacultyLessons(@PathVariable Integer collegeId,
										   @PathVariable Long facultyId){
		return lessonService.showLessons(facultyId, CollegeFacultyGroup.FACULTY);
	}
	
	@RequestMapping(value="/faculties/{facultyId}/groups/{groupId}/lessons", method=RequestMethod.GET)
	public ModelAndView showGroupLessons(@PathVariable Integer collegeId,
			   							 @PathVariable Long facultyId,
			   							 @PathVariable Long groupId){
		return lessonService.showLessons(groupId, CollegeFacultyGroup.GROUP);
	}
	
	@RequestMapping(value="/lessons/{lessonId}", method=RequestMethod.GET)
	public ModelAndView showLessonCollege(@PathVariable Integer collegeId,
										  @PathVariable Long lessonId) {
		return lessonService.showLesson(lessonId, CollegeFacultyGroup.COLLEGE);
	}
	
	@RequestMapping(value="faculties/{facultyId}/lessons/{lessonId}", method=RequestMethod.GET)
	public ModelAndView showLessonFaculty(@PathVariable Integer collegeId,
										  @PathVariable Long facultyId,
								   		  @PathVariable Long lessonId) {
		return lessonService.showLesson(lessonId, CollegeFacultyGroup.FACULTY);
	}

	@RequestMapping(value="faculties/{facultyId}/groups/{groupId}/lessons/{lessonId}", method=RequestMethod.GET)
	public ModelAndView showLessonGroup(@PathVariable Integer collegeId,
										@PathVariable Long facultyId,
										@PathVariable Long groupId,
										@PathVariable Long lessonId) {
		return lessonService.showLessonGroup(groupId, lessonId, CollegeFacultyGroup.GROUP);
	}
	
	@RequestMapping(value="/lessons/{lessonId}/edit", method=RequestMethod.GET)
	public ModelAndView showEditableLessonCollege(@PathVariable Integer collegeId,
										  		  @PathVariable Long lessonId) {
		return lessonService.showEditableLesson(lessonId, CollegeFacultyGroup.COLLEGE);
	}
	
	@RequestMapping(value="faculties/{facultyId}/lessons/{lessonId}/edit", method=RequestMethod.GET)
	public ModelAndView showEditableLessonFaculty(@PathVariable Integer collegeId,
										  		  @PathVariable Long facultyId,
										  		  @PathVariable Long lessonId) {
		return lessonService.showEditableLesson(lessonId, CollegeFacultyGroup.FACULTY);
	}

	@RequestMapping(value="faculties/{facultyId}/groups/{groupId}/lessons/{lessonId}/edit", method=RequestMethod.GET)
	public ModelAndView showEditableLessonGroup(@PathVariable Integer collegeId,
												@PathVariable Long facultyId,
												@PathVariable Long groupId,
												@PathVariable Long lessonId) {

		return lessonService.showEditableLessonGroup(groupId, lessonId, CollegeFacultyGroup.GROUP);
	}
	
	@RequestMapping(value="/lessons/{lessonId}/edit", method=RequestMethod.POST)
	public ModelAndView showEditedLessonCollege(@PathVariable Integer collegeId,
										  		@PathVariable Long lessonId,
										  		@RequestParam(required=false, value="absence") Long[] absenceChange,
												@RequestParam(required=false, value="marks") String[] freshMarks,
												@RequestParam(required=false, value="check") String isDeleted) {
			return lessonService.showEditedLesson(collegeId, lessonId, absenceChange,
					freshMarks, isDeleted, CollegeFacultyGroup.COLLEGE);
	}
	
	@RequestMapping(value="faculties/{facultyId}/lessons/{lessonId}/edit", method=RequestMethod.POST)
	public ModelAndView showEditedLessonFaculty(@PathVariable Integer collegeId,
										  		@PathVariable Long facultyId,
										  		@PathVariable Long lessonId,
										  		@RequestParam(required=false, value="absence") Long[] absenceChange,
												@RequestParam(required=false, value="marks") String[] freshMarks,
												@RequestParam(required=false, value="check") String isDeleted) {

			return lessonService.showEditedLesson(facultyId, lessonId, absenceChange,
					freshMarks, isDeleted, CollegeFacultyGroup.FACULTY);
	}

	@RequestMapping(value="faculties/{facultyId}/groups/{groupId}/lessons/{lessonId}/edit", method=RequestMethod.POST)
	public ModelAndView showEditedLessonGroup(@PathVariable Integer collegeId,
										  		@PathVariable Long facultyId,
										  		@PathVariable Long lessonId,
										  		@PathVariable Long groupId,
										  		@RequestParam(required=false, value="absence") Long[] absenceChange,
												@RequestParam(required=false, value="marks") String[] freshMarks,
												@RequestParam(required=false, value="check") String isDeleted) {
		
		return lessonService.showEditedLesson(groupId, lessonId, absenceChange,
				freshMarks, isDeleted, CollegeFacultyGroup.GROUP);
	}
	
  	@RequestMapping(value="/lessons", method=RequestMethod.POST)
  	public ModelAndView collegeLessonsForDate(@RequestParam("date") String date,
  									   		  @PathVariable Integer collegeId){
  		
  		return lessonService.lessonsForDate(date, collegeId, CollegeFacultyGroup.COLLEGE);
  	}
  	
  	@RequestMapping(value="/faculties/{facultyId}/lessons", method=RequestMethod.POST)
  	public ModelAndView facultyLessonsForDate(@RequestParam("date") String date,
  			 								  @PathVariable Integer collegeId,
  									   		  @PathVariable Long facultyId){
  		
  		return lessonService.lessonsForDate(date, facultyId, CollegeFacultyGroup.FACULTY);
	}
  	
  	@RequestMapping(value="/faculties/{facultyId}/groups/{groupId}/lessons", method=RequestMethod.POST)
  	public ModelAndView groupLessonsForDate(@RequestParam("date") String date,
								  			@PathVariable Integer collegeId,
									   		@PathVariable Long facultyId,
  									   		@PathVariable Long groupId){
  		
  		return lessonService.lessonsForDate(date, groupId, CollegeFacultyGroup.GROUP);
  	}
  	
  	@RequestMapping(value="/faculties/{facultyId}/groups/{groupId}/students/{studentId}", method=RequestMethod.POST)
  	public ModelAndView groupStudentLessonsForDate(@RequestParam("date") String date,
								  				@PathVariable Integer collegeId,
								  				@PathVariable Long facultyId,
								  				@PathVariable Long groupId,
								  				@PathVariable Long studentId){

  		return lessonService.groupStudentLessonsForDate(date, groupId, studentId);
  	}
  	
  	@RequestMapping(value="lessons/new", method=RequestMethod.GET)
	public ModelAndView newLessonFromCollege(@PathVariable Integer collegeId){

		return lessonService.newLessonFromCollege(collegeId, false);
	}
  	
  	@RequestMapping(value="faculties/{facultyId}/lessons/new", method=RequestMethod.GET)
	public ModelAndView newLessonFromFaculty(@PathVariable Integer collegeId,
								  			 @PathVariable Long facultyId){

		return lessonService.newLessonFromFaculty(collegeId, facultyId, false);

	}
  	
  	@RequestMapping(value="faculties/{facultyId}/groups/{groupId}/lessons/new", method=RequestMethod.GET)
	public ModelAndView newLessonFromGroup(@PathVariable Integer collegeId,
								  		   @PathVariable Long facultyId,
								  		   @PathVariable Long groupId){
  		
		return lessonService.newLessonFromGroup(collegeId, facultyId, false);
	}
  	
  	@RequestMapping(value="/lessons/new", method=RequestMethod.POST)
	public ModelAndView saveLessonFromCollege(@PathVariable Integer collegeId,
//											  @RequestParam("faculty") Long facultyId,
//											  @RequestParam("date") String lessonDate,
//											  @RequestParam("start") String lessonStart,
//											  @RequestParam("end") String lessonEnd,
//											  @RequestParam(value = "subject", required = false) String subjectName,
//											  @RequestParam("teacher") Long teacherId,
											  @RequestParam("types") int type,
											  @RequestParam("group") String[] groupNames,

											  @Valid @ModelAttribute("lesson") LessonValidator lesson,
											  BindingResult result){
			
//	return lessonService.saveLessonFromCollege(facultyId, collegeId, lessonDate,
//				lessonStart, lessonEnd, subjectName, teacherId,
//			type, groupNames, CollegeFacultyGroup.COLLEGE, result);

		return lessonService.saveLessonFromCollege(collegeId, type, groupNames, lesson, CollegeFacultyGroup.COLLEGE, result);
	}
  	
  	@RequestMapping(value="faculties/{facultyId}/lessons/new", method=RequestMethod.POST)
	public ModelAndView saveLessonFromFaculty(@PathVariable Integer collegeId,
							 @PathVariable Long facultyId,
//							 @RequestParam("date") String lessonDate,
//							 @RequestParam("start") String lessonStart,
//							 @RequestParam("end") String lessonEnd,
//							 @RequestParam("subjects") Integer subjectId,
//							 @RequestParam("teachers") Long teacherId,
							 @RequestParam("types") int type,
							 @RequestParam("group") String[] groupIds,
						     @Valid @ModelAttribute("lesson") LessonValidator lesson,
							 BindingResult result){
			
//		return lessonService.saveLessonFromFaculty(lessonDate,
//				lessonStart, lessonEnd, subjectId, teacherId,
//				type, groupIds, CollegeFacultyGroup.FACULTY, result);
		return lessonService.saveLessonFromFaculty(collegeId, facultyId, type, groupIds, lesson, CollegeFacultyGroup.FACULTY, result);
	}
  					
  	@RequestMapping(value="faculties/{facultyId}/groups/{groupId}/lessons/new", method=RequestMethod.POST)
	public ModelAndView saveLessonFromGroup(@PathVariable Integer collegeId,
											 @PathVariable Long facultyId,
											 @PathVariable Long groupId,
//											 @RequestParam("date") String lessonDate,
//											 @RequestParam("start") String lessonStart,
//											 @RequestParam("end") String lessonEnd,
//											 @RequestParam("subjects") Integer subjectId,
//											 @RequestParam("teachers") Long teacherId,
											 @RequestParam("types") int type,
											 @Valid @ModelAttribute("lesson") LessonValidator lesson,
											 BindingResult result){
			
		return lessonService.saveLessonFromGroup(collegeId, facultyId, groupId,
				type, lesson, CollegeFacultyGroup.GROUP, result);
	}
	
}