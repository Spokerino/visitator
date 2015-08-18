package org.spok.visitator.web;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import org.junit.Test;
import org.spok.visitator.data.EducationGroupRepository;
import org.spok.visitator.data.EducationSpecializationRepository;
import org.spok.visitator.data.LessonRepository;
import org.spok.visitator.data.StudentRepository;
import org.spok.visitator.data.SubjectRepository;
import org.spok.visitator.data.TeacherRepository;
import org.spok.visitator.lesson.Lecture;
import org.spok.visitator.lesson.Lesson;
import org.springframework.test.web.servlet.MockMvc;

public class TestLessonController {
	
	private LessonRepository lessonRepository = mock(LessonRepository.class);
	private SubjectRepository subjectRepository = mock(SubjectRepository.class);
	private StudentRepository studentRepository = mock(StudentRepository.class);
	private TeacherRepository teacherRepository = mock(TeacherRepository.class);
	private EducationSpecializationRepository facultyRepository = mock(EducationSpecializationRepository.class);
	private EducationGroupRepository groupRepository = mock(EducationGroupRepository.class);
	
	LessonController controller = new LessonController(lessonRepository,
													   subjectRepository,
													   studentRepository,
													   teacherRepository,
													   facultyRepository,
													   groupRepository);
	
	@Test
	public void testCollegeLessons() throws Exception{
		
		MockMvc mockMvc = standaloneSetup(controller).build();
		
		mockMvc.perform(get("/colleges/1/lessons"))
			.andExpect(view().name("college/collegeLessons"))
			.andExpect(model().attributeExists("lessonList"));
	}
	
	@Test
	public void testLessonById() throws Exception {
		
		Lesson expectedLesson = new Lecture();
		when(lessonRepository.findLessonById((long) 12345)).thenReturn(expectedLesson);
		
		MockMvc mockMvc = standaloneSetup(controller).build();
		
		mockMvc.perform(get("/colleges/1/lessons/12345"))
			.andExpect(view().name("college/collegeLesson"))
			.andExpect(model().attributeExists("lesson"))
			.andExpect(model().attribute("lesson", expectedLesson));
	}
	
}
