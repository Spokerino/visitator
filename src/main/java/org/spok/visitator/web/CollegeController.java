package org.spok.visitator.web;

import java.util.List;

import javax.validation.Valid;

import org.spok.visitator.data.EducationInstitutionRepository;
import org.spok.visitator.data.EducationSpecializationRepository;
import org.spok.visitator.institution.College;
import org.spok.visitator.institution.EducationInstitution;
import org.spok.visitator.institution.EducationSpecialization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/colleges")
public class CollegeController {

	private EducationInstitutionRepository institutionRepostory;
	private EducationSpecializationRepository specializationRepository;
	
	
	@Autowired
	public CollegeController(EducationInstitutionRepository institutionRepostory,
			EducationSpecializationRepository specializationRepository) {
		
		this.institutionRepostory = institutionRepostory;
		this.specializationRepository = specializationRepository;
	}
	
	
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView showColleges(){
		
		ModelAndView model = new ModelAndView("college/colleges");
		List<EducationInstitution> colleges = institutionRepostory.findAllInstitutions(); 
		model.addObject("colleges", colleges);		
		
		return model; 
	}
	
	
	@RequestMapping(value="/{collegeId}", method=RequestMethod.GET)
	public ModelAndView showFaculties(@PathVariable Integer collegeId) {
		
		ModelAndView model = new ModelAndView("college/college");
		EducationInstitution college =  institutionRepostory.findCollegeById(collegeId);
		List<EducationSpecialization> list = specializationRepository.findAllFaculties(collegeId);
		college.setSpecializations(list);
		model.addObject(college);
		return model;
	}
	
	@RequestMapping(value="/new", method=RequestMethod.GET)
	public String addCollegeGet(Model model) {
		model.addAttribute(new College());
		return "college/collegeForm";
	}
	
	@RequestMapping(value="/new", method=RequestMethod.POST)
	public String addCollegePost(@Valid @ModelAttribute("college") College college,
								 BindingResult result) {
		
		if(result.hasErrors()){
			return "college/collegeForm";
		}
		
		institutionRepostory.createCollege(college);
		return "redirect:/colleges";
	}
	
	@RequestMapping(value="/{collegeId}/edit", method=RequestMethod.GET)
	public ModelAndView editCollegeGet(@PathVariable Integer collegeId) {
		
		College college = institutionRepostory.findCollegeById(collegeId);
		ModelAndView model = new ModelAndView("college/editCollege");
		model.addObject("college", college);
		
		return model;
	}
	
	@RequestMapping(value="/{collegeId}/edit", method=RequestMethod.POST)
	public String editCollegePost(@PathVariable Integer collegeId,
										@Valid @ModelAttribute("college") College college,
										BindingResult result,
										@RequestParam(required=false, value="check") String isDeleted) {
		
		if(isDeleted == null)
		{
			if(result.hasErrors())
				return "college/editCollege";
			
			college.setId(collegeId);
			institutionRepostory.updateCollege(college);
		}
		else
		{
			institutionRepostory.deleteCollege(collegeId);
		}
		return "redirect:/colleges";
	}
}
