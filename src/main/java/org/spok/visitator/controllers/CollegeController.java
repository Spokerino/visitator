package org.spok.visitator.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.spok.visitator.entities.enum_types.EducationInstitutionTypes;
import org.spok.visitator.entities.institution.College;
import org.spok.visitator.services.InstitutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/colleges")
public class CollegeController {

	@Autowired
	private InstitutionService institutionService;


	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView showInstitutions(){
		return institutionService.getAllColleges();
	}
	
	@RequestMapping(value="/{collegeId}", method=RequestMethod.GET)
	public ModelAndView showInstitution(@PathVariable Integer collegeId) {
		return institutionService.getCollegeById(collegeId);
	}
	
	@RequestMapping(value="/new", method=RequestMethod.GET)
	public String addCollegeGet(Model model) {
		return institutionService.addInstitutionGet(model, EducationInstitutionTypes.COLLEGE);
	}

	@RequestMapping(value="/new", method=RequestMethod.POST)
	public String addCollegePost(@Valid @ModelAttribute("college") College college,
								 BindingResult result) {
		return institutionService.addCollegePost(college, result);
	}
	
	@RequestMapping(value="/{collegeId}/edit", method=RequestMethod.GET)
	public ModelAndView editCollegeGet(@PathVariable Integer collegeId) {

		return institutionService.editCollegeGet(collegeId);
	}

	@RequestMapping(value="/{collegeId}/edit", method=RequestMethod.POST)
	public String editCollegePost(@PathVariable Integer collegeId,
										@Valid @ModelAttribute("college") College college,
										BindingResult result,
										@RequestParam(required=false, value="check") String isDeleted) {
		
		return institutionService.editCollegePost(collegeId, college, result, isDeleted);
	}
}
