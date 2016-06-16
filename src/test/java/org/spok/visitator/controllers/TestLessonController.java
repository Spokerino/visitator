package org.spok.visitator.controllers;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockingDetails;
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
import org.spok.visitator.entities.lesson.Lecture;
import org.spok.visitator.entities.lesson.Lesson;
import org.spok.visitator.services.LessonService;
import org.springframework.test.web.servlet.MockMvc;

public class TestLessonController {
	
	private LessonRepository lessonRepository = mock(LessonRepository.class);

	LessonController controller = new LessonController();
	
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
