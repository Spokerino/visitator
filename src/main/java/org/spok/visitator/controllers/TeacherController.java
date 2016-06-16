package org.spok.visitator.controllers;

import org.spok.visitator.entities.person.CollegeTeacher;
import org.spok.visitator.services.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("/colleges/{collegeId}")
public class TeacherController {

	@Autowired
	private TeacherService teacherService;

	@RequestMapping(value="/teachers", method=RequestMethod.GET)
	public ModelAndView showCollegeTeachers(@PathVariable Integer collegeId) {

		return  teacherService.showCollegeTeachers(collegeId);
	}

	@RequestMapping(value="/faculties/{facultyId}/teachers", method=RequestMethod.GET)
	public ModelAndView showFacultyTeachers(@PathVariable Integer collegeId,
											@PathVariable Long facultyId) {

		return  teacherService.showFacultyTeachers(collegeId, facultyId);
	}

	@RequestMapping(value="faculties/{facultyId}/teachers/new", method=RequestMethod.GET)
	public ModelAndView addTeacherFromFacultyGet(@PathVariable Integer collegeId,
												 @PathVariable Long facultyId) {

		return teacherService.addTeacherFromFacultyGet(collegeId, facultyId);
	}

	@RequestMapping(value="faculties/{facultyId}/teachers/new", method=RequestMethod.POST)
	public ModelAndView addTeacherFromFacultyPost(@PathVariable Integer collegeId,
			 									  @PathVariable Long facultyId,
										  		  @Valid @ModelAttribute("teacher") CollegeTeacher teacher,
										  		  BindingResult result,
										  		  @RequestParam(value="subject", required=false) int[] subjectsId) {

		return teacherService.addTeacherFromFacultyPost(collegeId, facultyId, teacher, result, subjectsId);
	}

	@RequestMapping(value="/faculties/{facultyId}/teachers/{teacherId}/edit",
			method=RequestMethod.GET)
	public ModelAndView editTeacherFromFacultyGet(@PathVariable Integer collegeId,
												  @PathVariable Long facultyId,
												  @PathVariable Long teacherId) {

		return teacherService.editTeacherFromFacultyGet(collegeId, facultyId, teacherId);
	}

	@RequestMapping(value="/faculties/{facultyId}/teachers/{teacherId}/edit",
			method=RequestMethod.POST)
	public ModelAndView editTeacherFromFacultyPost(@PathVariable Integer collegeId,
												   @PathVariable Long facultyId,
												   @PathVariable Long teacherId,
											 	   @Valid @ModelAttribute("teacher") CollegeTeacher teacher,
											 	   BindingResult result,
											 	   @RequestParam(required=false, value="check") String isDeleted,
											 	   @RequestParam(value="subjectToAdd", required=false) Integer[] subjectAdd,
											  	   @RequestParam(value="subjectToRemove", required=false) Integer[] subjectRemove,
											  	   @RequestParam("faculties") Long newFacultyId) {

		return teacherService.editTeacherFromFacultyPost(collegeId, facultyId, teacherId, teacher, result,
				isDeleted, subjectAdd, subjectRemove, newFacultyId);
	}
}
