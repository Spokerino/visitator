package org.spok.visitator.controllers;

import org.spok.visitator.entities.institution.CollegeFaculty;
import org.spok.visitator.services.SpecializationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("/colleges/{collegeId}/faculties")
public class CollegeFacultyController {

	@Autowired
	private SpecializationService specializationService;
	

	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView showFaculties(@PathVariable Integer collegeId) {
		return specializationService.getAllFaculties(collegeId);
	}
	
	@RequestMapping(value="/new", method=RequestMethod.GET)
	public String addFacultyGet(@PathVariable Integer collegeId, Model model) {
		return specializationService.addFacultyGet(model);
	}
	
	@RequestMapping(value="/new", method=RequestMethod.POST)
	public String addFacultyPost(@PathVariable Integer collegeId,
								 @Valid @ModelAttribute("collegeFaculty") CollegeFaculty faculty,
								 BindingResult result) {

		return specializationService.addFacultyPost(collegeId, faculty, result);
	}

	@RequestMapping(value="/{facultyId}", method=RequestMethod.GET)
	public ModelAndView showFacultyById(
			@PathVariable Integer collegeId,
			@PathVariable Long facultyId){
		
		return specializationService.showFacultyById(collegeId, facultyId);
	}
	
	@RequestMapping(value="/{facultyId}/edit", method=RequestMethod.GET)
	public ModelAndView editFacultyGet(@PathVariable Integer collegeId,
									   @PathVariable Long facultyId) {
		return specializationService.editFacultyGet(facultyId);
	}
	
	@RequestMapping(value="/{facultyId}/edit", method=RequestMethod.POST)
	public String editFacultyPost(@PathVariable Integer collegeId,
								  @PathVariable Long facultyId,
								  @Valid @ModelAttribute("faculty") CollegeFaculty faculty,
								  BindingResult result,
								  @RequestParam(required=false, value="check") String isDeleted) {
		
		return specializationService.editFacultyPost(facultyId, faculty, result, isDeleted);
	}
}
