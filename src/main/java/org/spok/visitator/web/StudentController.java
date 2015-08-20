package org.spok.visitator.web;

import java.util.List;

import javax.validation.Valid;

import org.spok.visitator.data.EducationGroupRepository;
import org.spok.visitator.data.EducationInstitutionRepository;
import org.spok.visitator.data.EducationSpecializationRepository;
import org.spok.visitator.data.LessonRepository;
import org.spok.visitator.data.StudentRepository;
import org.spok.visitator.data.enum_types.CollegeFacultyGroup;
import org.spok.visitator.institution.CollegeGroup;
import org.spok.visitator.institution.EducationGroup;
import org.spok.visitator.institution.EducationInstitution;
import org.spok.visitator.institution.EducationSpecialization;
import org.spok.visitator.lesson.Lesson;
import org.spok.visitator.lesson.Markable;
import org.spok.visitator.person.CollegeStudent;
import org.spok.visitator.person.Student;
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
@RequestMapping("/colleges/{collegeId}")
public class StudentController {
	
	private EducationInstitutionRepository institutionRepostory;
	private EducationSpecializationRepository specializationRepository;
	private EducationGroupRepository groupRepository;
	private StudentRepository studentRepository;
	private LessonRepository lessonRepository;
		
	@Autowired
	public StudentController(
			EducationInstitutionRepository institutionRepostory,
			EducationSpecializationRepository specializationRepository,
			StudentRepository studentRepository,
			EducationGroupRepository groupRepository,
			LessonRepository lessonRepository) {
		
		this.institutionRepostory = institutionRepostory;
		this.specializationRepository = specializationRepository;
		this.groupRepository = groupRepository;
		this.studentRepository = studentRepository;
		this.lessonRepository = lessonRepository;
	}


	@RequestMapping(value="/students", method=RequestMethod.GET)
	public ModelAndView showCollegeStudents(@PathVariable Integer collegeId) {
			
		ModelAndView model = new ModelAndView("college/collegeStudents");
		
		EducationInstitution college =  institutionRepostory.findCollegeById(collegeId);
		List<EducationSpecialization> list = specializationRepository.findAllFaculties(collegeId);
		List<Student> students = studentRepository.findAllStudentsInCollege(collegeId);
		college.setSpecializations(list);
		model.addObject("students", students);
		model.addObject(college);
		return model;
	}
	
	@RequestMapping(value="/faculties/{facultyId}/students", method=RequestMethod.GET)
	public ModelAndView showFacultyStudents(@PathVariable Integer collegeId,
											@PathVariable Long facultyId) {
			
		ModelAndView model = new ModelAndView("faculty/facultyStudents");
		
		EducationSpecialization faculty = specializationRepository.findFacultyById(facultyId);
		//List<EducationGroup> groups = groupRepository.findAllFacultyGroups(collegeId, facultyId);
		List<Student> students = studentRepository.findAllStudentsInFaculty(facultyId);
		//faculty.setGroups(groups);
		
		model.addObject("students", students);
		model.addObject("faculty", faculty);
		
		return model;
	}
	
	@RequestMapping(value="/faculties/{facultyId}/groups/{groupId}/students", method=RequestMethod.GET)
	public ModelAndView showGroupStudents(@PathVariable Integer collegeId,
										  @PathVariable Long facultyId,
										  @PathVariable Long groupId) {
			
		ModelAndView model = new ModelAndView("group/groupStudents");
		
		EducationGroup group = groupRepository.findGroupById(groupId);
		List<Student> students = studentRepository.findAllStudentsInGroup(groupId);
		group.setStudents(students);
		model.addObject("group", group);
		
		return model;
	}
	
	@RequestMapping(value="/students", method=RequestMethod.POST)
	public ModelAndView showCollegeStudentsByName(@PathVariable Integer collegeId,
												  @RequestParam("name") String name) {
			
		ModelAndView model = new ModelAndView("college/collegeStudents");
		
		EducationInstitution college =  institutionRepostory.findCollegeById(collegeId);
		List<EducationSpecialization> list = specializationRepository.findAllFaculties(collegeId);
		List<Student> students = studentRepository.findAllStudentsInCollegeByName(collegeId, name);
		if(students.size() == 0)
			students = studentRepository.findAllStudentsInCollege(collegeId);
		college.setSpecializations(list);
		model.addObject("students", students);
		model.addObject(college);
		return model;
	}
	
	@RequestMapping(value="/faculties/{facultyId}/students", method=RequestMethod.POST)
	public ModelAndView showFacultyStudentsByName(@PathVariable Integer collegeId,
												  @PathVariable Long facultyId,
												  @RequestParam("name") String name) {
			
		ModelAndView model = new ModelAndView("faculty/facultyStudents");
		
		EducationSpecialization faculty = specializationRepository.findFacultyById(facultyId);
		List<Student> students = studentRepository.findAllStudentsInFacultyByName(facultyId, name);
		if(students.size() == 0)
				students = studentRepository.findAllStudentsInFaculty(facultyId);
		
		model.addObject("students", students);
		model.addObject("faculty", faculty);
		
		return model;
	}
	
	@RequestMapping(value="/students/new", method=RequestMethod.GET)
	public ModelAndView addStudentFromCollegeGet(@PathVariable Integer collegeId) {
		
		List<EducationSpecialization> faculties = specializationRepository.findAllFaculties(collegeId);
		ModelAndView model = new ModelAndView("college/collegeStudentForm");
		model.addObject("faculties", faculties);
		model.addObject("student", new CollegeStudent());
		return model;
	}
	
	@RequestMapping(value="/students/new", method=RequestMethod.POST)
	public ModelAndView addStudentFromCollegePost(@PathVariable Integer collegeId,
											  		@RequestParam("faculties") Long facultyId,
											  		@Valid @ModelAttribute("student") CollegeStudent student,
											  		BindingResult result, 
											  		@RequestParam("groups") String groupName) {
		
		ModelAndView model = new ModelAndView();
		if(groupName.equals(""))
			model.addObject("emptyGroup", "Please, select a group");
		
		if(result.hasErrors() || groupName.equals("")){
			List<EducationSpecialization> faculties = specializationRepository.findAllFaculties(collegeId);
			model.setViewName("college/collegeStudentForm");
			model.addObject("faculties", faculties);
						
			return model; 
		}
		
		EducationGroup group = groupRepository.findGroupByName(facultyId, groupName);
		student.setGroup(group);
		studentRepository.addStudent(student);
		
		model = showCollegeStudents(collegeId);
		model.setViewName("redirect:/colleges/{collegeId}/students");
		return model;
	}
	
	@RequestMapping(value="/faculties/{facultyId}/students/new", method=RequestMethod.GET)
	public ModelAndView addStudentFromFacultyGet(@PathVariable Integer collegeId,
											  	 @PathVariable Long facultyId) {
		
		List<EducationGroup> groups = groupRepository.findAllFacultyGroups(facultyId);
		ModelAndView model = new ModelAndView("faculty/facultyStudentForm");
		model.addObject("groups", groups);
		model.addObject("student", new CollegeStudent());
		return model;
	}

	@RequestMapping(value="/faculties/{facultyId}/students/new", method=RequestMethod.POST)
	public ModelAndView addStudentFromFacultyPost(@PathVariable Integer collegeId,
											  		@PathVariable Long facultyId,
											  		@Valid @ModelAttribute("student") CollegeStudent student,
											  		BindingResult result, 
											  		@RequestParam("grp") Long groupId) {
		
		if(result.hasErrors()){
			ModelAndView model = new ModelAndView("faculty/facultyStudentForm");//addStudentFromFacultyGet(collegeId, facultyId);
			List<EducationGroup> groups = groupRepository.findAllFacultyGroups(facultyId);
			model.addObject("groups", groups);
			return model; 
		}
		
		EducationGroup group = groupRepository.findGroupById(groupId);
		student.setGroup(group);
		studentRepository.addStudent(student);
		
		ModelAndView model = showFacultyStudents(collegeId, facultyId);
		model.setViewName("redirect:/colleges/{collegeId}/faculties/{facultyId}/students");
		return model;
	}

	@RequestMapping(value="/faculties/{facultyId}/groups/{groupId}/students/new", method=RequestMethod.GET)
	public ModelAndView addStudentFromGroupGet(@PathVariable Integer collegeId,
										    @PathVariable Long facultyId,
										    @PathVariable Long groupId) {
		
		ModelAndView model = new ModelAndView("group/groupStudentForm");
		CollegeGroup group = (CollegeGroup) groupRepository.findGroupById(groupId);
		model.addObject("student", new CollegeStudent());
		model.addObject("group", group);
		
		return model;
	}
	
	@RequestMapping(value="/faculties/{facultyId}/groups/{groupId}/students/new", method=RequestMethod.POST)
	public ModelAndView addStudentFromGroupPost(@PathVariable Integer collegeId,
										  		  @PathVariable Long facultyId,
										  		  @PathVariable Long groupId,
										  		  @Valid @ModelAttribute("student") CollegeStudent student,
										  		  BindingResult result) {
		
		EducationGroup group = groupRepository.findGroupById(groupId);
		
		if(result.hasErrors()) { 			
			ModelAndView model = new ModelAndView("group/groupStudentForm");
			model.addObject("group", group);
			return model;
		}
		
		student.setGroup(group);
		studentRepository.addStudent(student);
		
		ModelAndView model = showGroupStudents(collegeId, facultyId, groupId);
		model.setViewName("redirect:/colleges/{collegeId}/faculties/{facultyId}/groups/{groupId}/students");
		return model;
	}
	
	
	@RequestMapping(value="/faculties/{facultyId}/groups/{groupId}/students/{studentId}/edit", 
			method=RequestMethod.GET)
	public ModelAndView editStudentFromGroupGet(@PathVariable Integer collegeId,
			@PathVariable Long facultyId,
			@PathVariable Long groupId,
			@PathVariable Long studentId) {
		
		CollegeStudent student = (CollegeStudent) studentRepository.findStudentById(studentId);
		CollegeGroup group = (CollegeGroup) groupRepository.findGroupById(groupId);
		ModelAndView model = addStudentFromCollegeGet(collegeId); 
		model.setViewName("group/editGroupStudent");
		model.addObject("student", student);
		model.addObject("group", group);
		
		return model;
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
		
		CollegeGroup group = (CollegeGroup) groupRepository.findGroupById(groupId);
		
		if(isDeleted == null)
		{
			if(result.hasErrors()) {
				System.out.println(groupName);
				ModelAndView model = addStudentFromCollegeGet(collegeId);
				student.setGroup(group);
				model.addObject("student", student);
				model.setViewName("group/editGroupStudent");
				return model;
			}
			
			if(!groupName.equals("")) {
				group = (CollegeGroup) groupRepository.findGroupByName(facultyId, groupName);
			}
			
			student.setGroup(group);
			student.setId(studentId);
			studentRepository.updateStudent(student);
			
		}
		else
		{
			studentRepository.deleteStudent(studentId);
		}
		return new ModelAndView("redirect:/colleges/{collegeId}/faculties/{facultyId}/groups/{groupId}/students");
	}
	
	
	@RequestMapping(value="/faculties/{facultyId}/groups/{groupId}/students/{studentId}", 
			method=RequestMethod.GET)
	public ModelAndView StudentLessons(@PathVariable Integer collegeId,
			@PathVariable Long facultyId,
			@PathVariable Long groupId,
			@PathVariable Long studentId) {
		
		CollegeStudent student = (CollegeStudent) studentRepository.findStudentById(studentId);
		List<Lesson> lessons = lessonRepository.findLessons(CollegeFacultyGroup.GROUP, groupId);
		for(Lesson l : lessons) {
			l.setStudents(lessonRepository.findStudentsOnLesson(l.getId()));
			if(l instanceof Markable) {
				((Markable) l).setMarks(lessonRepository.showLessonMarks(l.getId()));
			}
		}

		ModelAndView model = addStudentFromCollegeGet(collegeId); 
		model.setViewName("group/groupStudent");
		model.addObject("student", student);
		model.addObject("lessons", lessons);
		
		return model;
	}
	
}
