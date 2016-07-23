package org.spok.visitator.controllers;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.spok.visitator.entities.lesson.Lesson;
import org.spok.visitator.entities.lesson.Practicum;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(MockitoJUnitRunner.class)
public class LessonControllerTest {

	@Mock
	LessonController controller;

	private MockMvc mockMvc;

	private ModelAndView model;

	private Lesson testLesson;

	@Before
	public void setup() {

		testLesson = new Practicum();
		testLesson.setId(12345L);

		mockMvc  = standaloneSetup(controller).build();
		model = new ModelAndView();

	}

	@Test
	public void testCollegeLessons() throws Exception {

		model.setViewName("college/collegeLessons");
		model.addObject("lessonList", new ArrayList<Lesson>());

		when(controller.showCollegeLessons(1)).thenReturn(model);

		mockMvc.perform(get("/colleges/1/lessons"))
			.andExpect(view().name("college/collegeLessons"))
			.andExpect(model().attributeExists("lessonList"));
	}

	@Test
	public void testShowCollegeLessonByLessonId() throws Exception {

		model.setViewName("college/collegeLesson");
		model.addObject("lesson", testLesson);

		when(controller.showLessonCollege(1, 12345L)).thenReturn(model);

		mockMvc.perform(get("/colleges/1/lessons/12345"))
			.andExpect(view().name("college/collegeLesson"))
			.andExpect(model().attributeExists("lesson"))
			.andExpect(model().attribute("lesson", testLesson));
	}
	
}
