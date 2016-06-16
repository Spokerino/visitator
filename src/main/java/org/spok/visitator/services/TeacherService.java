package org.spok.visitator.services;

import org.spok.visitator.data.EducationSpecializationRepository;
import org.spok.visitator.data.SubjectRepository;
import org.spok.visitator.data.TeacherRepository;
import org.spok.visitator.entities.institution.EducationSpecialization;
import org.spok.visitator.entities.lesson.Subject;
import org.spok.visitator.entities.person.CollegeTeacher;
import org.spok.visitator.entities.person.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Service
public class TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private SubjectRepository subjectRepository;
    @Autowired
    private EducationSpecializationRepository facultyRepository;


    public ModelAndView showCollegeTeachers(Integer collegeId) {

        ModelAndView model = new ModelAndView("college/collegeTeachers");
        List<Teacher> teachers = teacherRepository.getCollegeTeachers(collegeId);
        model.addObject("teacherList", teachers);

        return  model;
    }

    public ModelAndView showFacultyTeachers(Integer collegeId, Long facultyId) {

        ModelAndView model = new ModelAndView("faculty/facultyTeachers");
        List<Teacher> teachers = teacherRepository.getFacultyTeachers(facultyId);
        model.addObject("teacherList", teachers);

        return  model;
    }

    public ModelAndView addTeacherFromFacultyGet(Integer collegeId,
                                                 Long facultyId) {

        List<Subject> subjects = subjectRepository.getFacultySubjects(facultyId);
        ModelAndView model = new ModelAndView("faculty/facultyTeacherForm");
        model.addObject("subjects", subjects);
        model.addObject("teacher", new CollegeTeacher());

        return model;
    }

    public ModelAndView addTeacherFromFacultyPost(Integer collegeId,
                                                  Long facultyId,
                                                  CollegeTeacher teacher,
                                                  BindingResult result,
                                                  int[] subjectsId) {

        if(result.hasErrors() || subjectsId == null) {
            ModelAndView model = new ModelAndView("faculty/facultyTeacherForm");
            List<Subject> subjects = subjectRepository.getFacultySubjects(facultyId);
            model.addObject("subjects", subjects);
            model.addObject("message", "Please, select at least one subject");
            return model;
        }

        teacherRepository.addTeacherFromFaculty(teacher, facultyId);

        if(subjectsId != null) {
            long id = teacherRepository.getLastTeacherId().getId();
            for(int i : subjectsId)
                subjectRepository.addSubjectToTeacher(id, i);
        }

        ModelAndView model = showFacultyTeachers(collegeId, facultyId);
        model.setViewName("redirect:/colleges/{collegeId}/faculties/{facultyId}/teachers");

        return model;
    }

    public ModelAndView editTeacherFromFacultyGet(Integer collegeId,
                                                  Long facultyId,
                                                  Long teacherId) {

        CollegeTeacher teacher = (CollegeTeacher) teacherRepository.getTeacherForId(teacherId);
        List<EducationSpecialization> faculties = facultyRepository.findAllFaculties(collegeId);
        List<Subject> subjectsToAdd = subjectRepository.getFacultySubjects(facultyId);
        List<Subject> teacherSubjects = subjectRepository.getTeacherSubjects(teacherId);
        subjectsToAdd.removeAll(teacherSubjects);
        teacher.setSubjects(teacherSubjects);
        ModelAndView model = addTeacherFromFacultyGet(collegeId, facultyId);
        model.addObject("faculties", faculties);
        model.addObject("teacher", teacher);
        model.setViewName("faculty/editFacultyTeacher");
        model.addObject("subjectsToAdd", subjectsToAdd);

        return model;
    }

    public ModelAndView editTeacherFromFacultyPost(Integer collegeId,
                                                   Long facultyId,
                                                   Long teacherId,
                                                   CollegeTeacher teacher,
                                                   BindingResult result,
                                                   String isDeleted,
                                                   Integer[] subjectAdd,
                                                   Integer[] subjectRemove,
                                                   Long newFacultyId) {


        if(isDeleted == null)
        {
            teacher.setId(teacherId);

            if(result.hasErrors()) {

                ModelAndView model = editTeacherFromFacultyGet(collegeId, newFacultyId, teacherId);
                model.addObject("teacher", teacher);
            }

            if(subjectAdd != null) {
                for(int i : subjectAdd)
                    subjectRepository.addSubjectToTeacher(teacherId, i);
            }

            if(subjectRemove != null) {
                for(int i : subjectRemove)
                    subjectRepository.deleteSubjectFromTeacher(teacherId, i);
            }



            if(newFacultyId != -1)
                teacherRepository.updateTeacher(teacher, newFacultyId);
            else
                teacherRepository.updateTeacher(teacher, facultyId);

            ModelAndView model = showFacultyTeachers(collegeId, facultyId);
            model.setViewName("redirect:/colleges/{collegeId}/faculties/{facultyId}/teachers");

            return model;
        }
        else
        {
            teacherRepository.deleteTeacher(teacherId);
            return new ModelAndView("redirect:/colleges/{collegeId}/faculties/{facultyId}/teachers");
        }

    }
}
