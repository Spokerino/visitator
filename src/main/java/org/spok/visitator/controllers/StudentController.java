package org.spok.visitator.controllers;

import org.spok.visitator.entities.person.CollegeStudent;
import org.spok.visitator.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("/colleges/{collegeId}")
public class StudentController {
	
	@Autowired
	private StudentService studentService;
		
	@RequestMapping(value="/students", method=RequestMethod.GET)
	public ModelAndView showCollegeStudents(@PathVariable Integer collegeId) {

		return studentService.showCollegeStudents(collegeId);
	}
	
	@RequestMapping(value="/faculties/{facultyId}/students", method=RequestMethod.GET)
	public ModelAndView showFacultyStudents(@PathVariable Integer collegeId,
											@PathVariable Long facultyId) {
			
		return studentService.showFacultyStudents(collegeId, facultyId);
	}
	
	@RequestMapping(value="/faculties/{facultyId}/groups/{groupId}/students", method=RequestMethod.GET)
	public ModelAndView showGroupStudents(@PathVariable Integer collegeId,
										  @PathVariable Long facultyId,
										  @PathVariable Long groupId) {
			
		return studentService.showGroupStudents(collegeId, facultyId, groupId);
	}
	
	@RequestMapping(value="/students", method=RequestMethod.POST)
	public ModelAndView showCollegeStudentsByName(@PathVariable Integer collegeId,
												  @RequestParam("name") String name) {
			
		return studentService.showCollegeStudentsByName(collegeId, name);
	}
	
	@RequestMapping(value="/faculties/{facultyId}/students", method=RequestMethod.POST)
	public ModelAndView showFacultyStudentsByName(@PathVariable Integer collegeId,
												  @PathVariable Long facultyId,
												  @RequestParam("name") String name) {
			
		return showFacultyStudentsByName(collegeId, facultyId, name);
	}
	
	@RequestMapping(value="/students/new", method=RequestMethod.GET)
	public ModelAndView addStudentFromCollegeGet(@PathVariable Integer collegeId) {
		
		return studentService.addStudentFromCollegeGet(collegeId);
	}
	
	@RequestMapping(value="/students/new", method=RequestMethod.POST)
	public ModelAndView addStudentFromCollegePost(@PathVariable Integer collegeId,
											  		@RequestParam("faculties") Long facultyId,
											  		@Valid @ModelAttribute("student") CollegeStudent student,
											  		BindingResult result, 
											  		@RequestParam("groups") String groupName) {
		
		return studentService.addStudentFromCollegePost(collegeId, facultyId, student, result, groupName);
	}
	
	@RequestMapping(value="/faculties/{facultyId}/students/new", method=RequestMethod.GET)
	public ModelAndView addStudentFromFacultyGet(@PathVariable Integer collegeId,
											  	 @PathVariable Long facultyId) {
		
		return studentService.addStudentFromFacultyGet(collegeId, facultyId);
	}

	@RequestMapping(value="/faculties/{facultyId}/students/new", method=RequestMethod.POST)
	public ModelAndView addStudentFromFacultyPost(@PathVariable Integer collegeId,
											  		@PathVariable Long facultyId,
											  		@Valid @ModelAttribute("student") CollegeStudent student,
											  		BindingResult result, 
											  		@RequestParam("grp") Long groupId) {
		
		return studentService.addStudentFromFacultyPost(collegeId, facultyId, student, result, groupId);
	}

	@RequestMapping(value="/faculties/{facultyId}/groups/{groupId}/students/new", method=RequestMethod.GET)
	public ModelAndView addStudentFromGroupGet(@PathVariable Integer collegeId,
										    @PathVariable Long facultyId,
										    @PathVariable Long groupId) {
		
		return studentService.addStudentFromGroupGet(collegeId, facultyId, groupId);
	}
	
	@RequestMapping(value="/faculties/{facultyId}/groups/{groupId}/students/new", method=RequestMethod.POST)
	public ModelAndView addStudentFromGroupPost(@PathVariable Integer collegeId,
										  		  @PathVariable Long facultyId,
										  		  @PathVariable Long groupId,
										  		  @Valid @ModelAttribute("student") CollegeStudent student,
										  		  BindingResult result) {
		
		return studentService.addStudentFromGroupPost(collegeId, facultyId, groupId, student, result);
	}
	
	
	@RequestMapping(value="/faculties/{facultyId}/groups/{groupId}/students/{studentId}/edit", 
			method=RequestMethod.GET)
	public ModelAndView editStudentFromGroupGet(@PathVariable Integer collegeId,
			@PathVariable Long facultyId,
			@PathVariable Long groupId,
			@PathVariable Long studentId) {
		
		return studentService.editStudentFromGroupGet(collegeId, facultyId, groupId, studentId);
	}
	
	@RequestMapping(value="/faculties/{facultyId}/groups/{groupId}/students/{studentId}/edit",
			method=RequestMethod.POST)
	public ModelAndView editStudentFromGroupPost(@PathVariable Integer collegeId,
			@PathVariable Long studentId,
			@PathVariable Long facultyId,
			@PathVariable Long groupId,
			@Valid @ModelAttribute("student") CollegeStudent student,
			BindingResult result,
			@RequestParam(required=false, value="check") String isDeleted,
			@RequestParam("groups") String groupName) {
		
		return studentService.editStudentFromGroupPost(collegeId, studentId, facultyId, groupId, student, result, isDeleted, groupName);
	}
	
	
	@RequestMapping(value="/faculties/{facultyId}/groups/{groupId}/students/{studentId}", 
			method=RequestMethod.GET)
	public ModelAndView studentLessons(@PathVariable Integer collegeId,
			@PathVariable Long facultyId,
			@PathVariable Long groupId,
			@PathVariable Long studentId) {
		
		return studentService.studentLessons(collegeId, facultyId, groupId, studentId);
	}
	
}
