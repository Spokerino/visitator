package org.spok.visitator.web;

import java.util.List;

import javax.validation.Valid;

import org.spok.visitator.data.EducationGroupRepository;
import org.spok.visitator.data.EducationSpecializationRepository;
import org.spok.visitator.data.StudentRepository;
import org.spok.visitator.educ_person.Student;
import org.spok.visitator.institution.CollegeFaculty;
import org.spok.visitator.institution.CollegeGroup;
import org.spok.visitator.institution.EducationGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("colleges/{collegeId}/faculties/{facultyId}/groups")
public class CollegeGroupController {

	private EducationGroupRepository gRepository;
	private StudentRepository stRepository;
	private EducationSpecializationRepository fRepository;
	
	@Autowired
	public CollegeGroupController(EducationGroupRepository gRepository,
			StudentRepository stRepository,
			EducationSpecializationRepository fRepository) {
		
		this.gRepository = gRepository;
		this.stRepository = stRepository;
		this.fRepository = fRepository;
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView showCollegeGroups(
			@PathVariable Integer collegeId,
			@PathVariable Long facultyId) {
		
		CollegeFaculty faculty = (CollegeFaculty) fRepository.findFacultyById(facultyId);
		ModelAndView model = new ModelAndView("group/groups");
		List<EducationGroup> list = gRepository.findAllFacultyGroups(facultyId);
		model.addObject("educationGroupList", list);
		model.addObject("faculty", faculty);
		return model;
	}
	
	
	@RequestMapping(value="/{groupId}", method=RequestMethod.GET)
	public ModelAndView showCollegeGroupsById(
			@PathVariable Integer collegeId,
			@PathVariable Long facultyId,
			@PathVariable Long groupId) {
		
		ModelAndView model = new ModelAndView("group/group");
		EducationGroup group = gRepository.findGroupById(groupId);
		List<Student> students = stRepository.findAllStudentsInGroup(groupId);
		model.addObject("group", group);
		model.addObject("studentList", students);
		
		return model;
	}
	
	@RequestMapping(value="/new", method=RequestMethod.GET)
	public ModelAndView addGroupGet(@PathVariable Integer collegeId,
							  		@PathVariable Long facultyId) {
		
		CollegeFaculty faculty = (CollegeFaculty) fRepository.findFacultyById(facultyId);
		int[] courses = new int[faculty.getYearsToBecomeMaster()];
		for(int i = 0; i < courses.length; i++)
			courses[i] = i + 1;
		
		ModelAndView model = new ModelAndView("group/groupForm");
		model.addObject(new CollegeGroup());
		model.addObject("courses", courses);
		model.addObject("faculty", faculty);
		return model;
	}
	
	@RequestMapping(value="/new", method=RequestMethod.POST)
	public ModelAndView addGroupPost(@PathVariable Integer collegeId,
							   		 @PathVariable Long facultyId,
							   		 @Valid @ModelAttribute("collegeGroup") CollegeGroup group,
							   		 BindingResult result) {
		
		if(result.hasErrors()){
			return addGroupGet(collegeId, facultyId);
		}
		
		gRepository.createGroup(group, facultyId);
		return new ModelAndView("redirect:/colleges/{collegeId}/faculties/{facultyId}");
	}


	@RequestMapping(value="/{groupId}/edit", method=RequestMethod.GET)
	public ModelAndView editGroupGet(@PathVariable Integer collegeId,
									 @PathVariable Long facultyId,
									 @PathVariable Long groupId) {
		
		CollegeGroup group = (CollegeGroup) gRepository.findGroupById(groupId);
		ModelAndView model = addGroupGet(collegeId, facultyId); 
		model.setViewName("group/editGroup");//new ModelAndView("group/editGroup");
		model.addObject("group", group);
		
		return model;
	}
	
	@RequestMapping(value="/{groupId}/edit", method=RequestMethod.POST)
	public ModelAndView editGroupPost(@PathVariable Integer collegeId,
							    @PathVariable Long facultyId,
							    @PathVariable Long groupId,
							    @Valid @ModelAttribute("group") CollegeGroup group,
							    BindingResult result,
							    @RequestParam(required=false, value="check") String isDeleted) {
		
		if(isDeleted == null)
		{
			if(result.hasErrors()) {
				ModelAndView model = addGroupGet(collegeId, facultyId);
				model.setViewName("group/editGroup");
				return model;
			}
				
			group.setId(groupId);
			gRepository.updateGroup(group);
			return new ModelAndView("redirect:/colleges/{collegeId}/faculties/{facultyId}/groups");
		}
		else
		{
			gRepository.deleteGroup(groupId);
			return new ModelAndView("redirect:/colleges/{collegeId}/faculties/{facultyId}/groups");
		}
		
	}
	
}
