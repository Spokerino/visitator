package org.spok.visitator.web;

import java.util.List;

import javax.validation.Valid;

import org.spok.visitator.data.EducationGroupRepository;
import org.spok.visitator.data.EducationSpecializationRepository;
import org.spok.visitator.institution.CollegeFaculty;
import org.spok.visitator.institution.EducationGroup;
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
@RequestMapping("/colleges/{collegeId}/faculties")
public class CollegeFacultyController {

	private EducationSpecializationRepository specializationRepository;
	private EducationGroupRepository gRepository;
	
	
	
	@Autowired
	public CollegeFacultyController(EducationSpecializationRepository specializationRepository,
			EducationGroupRepository gRepository) {
		
		this.specializationRepository = specializationRepository;
		this.gRepository = gRepository;
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView showFaculties(
			@PathVariable Integer collegeId) {
		
		ModelAndView model = new ModelAndView("faculty/faculties");
		List<EducationSpecialization> list = specializationRepository.findAllFaculties(collegeId);
		model.addObject("educationSpecializationList", list);
		model.addObject("collegeId", collegeId);
		return model;
	}
	
	@RequestMapping(value="/new", method=RequestMethod.GET)
	public String addFacultyGet(@PathVariable Integer collegeId,
								Model model) {
		
		model.addAttribute(new CollegeFaculty());
		return "faculty/facultyForm";
	}
	
	@RequestMapping(value="/new", method=RequestMethod.POST)
	public String addFacultyPost(@PathVariable Integer collegeId,
								 @Valid @ModelAttribute("collegeFaculty") CollegeFaculty faculty,
								 BindingResult result) {
		
		if(result.hasErrors()){
			return "faculty/facultyForm";
		}
		
		specializationRepository.createFaculty(faculty, collegeId);
		return "redirect:/colleges/{collegeId}/faculties";
	}

	@RequestMapping(value="/{facultyId}", method=RequestMethod.GET)
	public ModelAndView showFacultyById(
			@PathVariable Integer collegeId,
			@PathVariable Long facultyId){
		
		ModelAndView model = new ModelAndView("faculty/faculty");
		EducationSpecialization faculty = specializationRepository.findFacultyById(facultyId);
		List<EducationGroup> list = gRepository.findAllFacultyGroups(facultyId);
		
		model.addObject("educationGroupList", list);
		model.addObject("faculty", faculty);
		model.addObject(collegeId);
		
		return model;
	}
	
	@RequestMapping(value="/{facultyId}/edit", method=RequestMethod.GET)
	public ModelAndView editFacultyGet(@PathVariable Integer collegeId,
									   @PathVariable Long facultyId) {
		
		CollegeFaculty faculty = (CollegeFaculty) specializationRepository.findFacultyById(facultyId);
		ModelAndView model = new ModelAndView("faculty/editFaculty");
		model.addObject("faculty", faculty);
		
		return model;
	}
	
	@RequestMapping(value="/{facultyId}/edit", method=RequestMethod.POST)
	public String editFacultyPost(@PathVariable Integer collegeId,
								  @PathVariable Long facultyId,	
								  @Valid @ModelAttribute("faculty") CollegeFaculty faculty,
								  BindingResult result,
								  @RequestParam(required=false, value="check") String isDeleted) {
		
		if(isDeleted == null)
		{
			if(result.hasErrors())
				return "faculty/editFaculty";
			
			faculty.setId(facultyId);
			specializationRepository.updateFaculty(faculty);
		}
		else
		{
			specializationRepository.deleteFaculty(facultyId);
		}
		return "redirect:/colleges/{collegeId}/faculties";
	}
}
