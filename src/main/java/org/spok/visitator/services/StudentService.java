package org.spok.visitator.services;

import org.spok.visitator.data.*;
import org.spok.visitator.entities.enum_types.CollegeFacultyGroup;
import org.spok.visitator.entities.institution.CollegeGroup;
import org.spok.visitator.entities.institution.EducationGroup;
import org.spok.visitator.entities.institution.EducationInstitution;
import org.spok.visitator.entities.institution.EducationSpecialization;
import org.spok.visitator.entities.lesson.Lesson;
import org.spok.visitator.entities.lesson.Markable;
import org.spok.visitator.entities.person.CollegeStudent;
import org.spok.visitator.entities.person.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;
import java.util.List;

@Service
public class StudentService {

    @Autowired
    private EducationInstitutionRepository institutionRepostory;
    @Autowired
    private EducationSpecializationRepository specializationRepository;
    @Autowired
    private EducationGroupRepository groupRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private LessonRepository lessonRepository;

    public ModelAndView showCollegeStudents(Integer collegeId) {

        ModelAndView model = new ModelAndView("college/collegeStudents");

        EducationInstitution college =  institutionRepostory.findInstitutionById(collegeId);
        List<EducationSpecialization> list = specializationRepository.findAllFaculties(collegeId);
        List<Student> students = studentRepository.findAllStudentsInCollege(collegeId);
        college.setSpecializations(list);
        model.addObject("students", students);
        model.addObject(college);

        return model;
    }

    public ModelAndView showFacultyStudents(Integer collegeId,
                                            Long facultyId) {

        ModelAndView model = new ModelAndView("faculty/facultyStudents");

        EducationSpecialization faculty = specializationRepository.findFacultyById(facultyId);
        List<Student> students = studentRepository.findAllStudentsInFaculty(facultyId);

        model.addObject("students", students);
        model.addObject("faculty", faculty);

        return model;
    }

    public ModelAndView showGroupStudents(Integer collegeId,
                                          Long facultyId,
                                          Long groupId) {

        ModelAndView model = new ModelAndView("group/groupStudents");

        EducationGroup group = groupRepository.findGroupById(groupId);
        List<Student> students = studentRepository.findAllStudentsInGroup(groupId);
        group.setStudents(students);
        model.addObject("group", group);

        return model;
    }

    public ModelAndView showCollegeStudentsByName(Integer collegeId,
                                                  String name) {

        ModelAndView model = new ModelAndView("college/collegeStudents");

        EducationInstitution college =  institutionRepostory.findInstitutionById(collegeId);
        List<EducationSpecialization> list = specializationRepository.findAllFaculties(collegeId);
        List<Student> students = studentRepository.findAllStudentsInCollegeByName(collegeId, name);
        if(students.size() == 0)
            students = studentRepository.findAllStudentsInCollege(collegeId);
        college.setSpecializations(list);
        model.addObject("students", students);
        model.addObject(college);
        return model;
    }

    public ModelAndView showFacultyStudentsByName(Integer collegeId,
                                                  Long facultyId,
                                                  String name) {

        ModelAndView model = new ModelAndView("faculty/facultyStudents");

        EducationSpecialization faculty = specializationRepository.findFacultyById(facultyId);
        List<Student> students = studentRepository.findAllStudentsInFacultyByName(facultyId, name);
        if(students.size() == 0)
            students = studentRepository.findAllStudentsInFaculty(facultyId);

        model.addObject("students", students);
        model.addObject("faculty", faculty);

        return model;
    }

    public ModelAndView addStudentFromCollegeGet(Integer collegeId) {

        List<EducationSpecialization> faculties = specializationRepository.findAllFaculties(collegeId);
        ModelAndView model = new ModelAndView("college/collegeStudentForm");
        model.addObject("faculties", faculties);
        model.addObject("student", new CollegeStudent());
        return model;
    }

    public ModelAndView addStudentFromCollegePost(Integer collegeId,
                                                  Long facultyId,
                                                  CollegeStudent student,
                                                  BindingResult result,
                                                  String groupName) {

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

    public ModelAndView addStudentFromFacultyGet(Integer collegeId,
                                                 Long facultyId) {

        List<EducationGroup> groups = groupRepository.findAllFacultyGroups(facultyId);
        ModelAndView model = new ModelAndView("faculty/facultyStudentForm");
        model.addObject("groups", groups);
        model.addObject("student", new CollegeStudent());
        return model;
    }

    public ModelAndView addStudentFromFacultyPost(Integer collegeId,
                                                  Long facultyId,
                                                  CollegeStudent student,
                                                  BindingResult result,
                                                  Long groupId) {

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

    public ModelAndView addStudentFromGroupGet(Integer collegeId,
                                               Long facultyId,
                                               Long groupId) {

        ModelAndView model = new ModelAndView("group/groupStudentForm");
        CollegeGroup group = (CollegeGroup) groupRepository.findGroupById(groupId);
        model.addObject("student", new CollegeStudent());
        model.addObject("group", group);

        return model;
    }

    public ModelAndView addStudentFromGroupPost(Integer collegeId,
                                                Long facultyId,
                                                Long groupId,
                                                CollegeStudent student,
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


    public ModelAndView editStudentFromGroupGet(Integer collegeId,
                                                Long facultyId,
                                                Long groupId,
                                                Long studentId) {

        CollegeStudent student = (CollegeStudent) studentRepository.findStudentById(studentId);
        CollegeGroup group = (CollegeGroup) groupRepository.findGroupById(groupId);
        ModelAndView model = addStudentFromCollegeGet(collegeId);
        model.setViewName("group/editGroupStudent");
        model.addObject("student", student);
        model.addObject("group", group);

        return model;
    }

    public ModelAndView editStudentFromGroupPost(Integer collegeId,
                                                 Long studentId,
                                                 Long facultyId,
                                                 Long groupId,
                                                 CollegeStudent student,
                                                 BindingResult result,
                                                 String isDeleted,
                                                 String groupName) {

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


    public ModelAndView studentLessons(Integer collegeId,
                                       Long facultyId,
                                       Long groupId,
                                       Long studentId) {

        CollegeStudent student = (CollegeStudent) studentRepository.findStudentById(studentId);
        List<Lesson> lessons = lessonRepository.findLessons(CollegeFacultyGroup.GROUP, groupId);
        for(Lesson l : lessons) {
            l.setStudents(lessonRepository.findStudentsOnLesson(l.getId()));
            if(l instanceof Markable) {
                ((Markable) l).setMarks(lessonRepository.showLessonMarks(l.getId()));
            }
        }

        Collections.sort(lessons);
        ModelAndView model = addStudentFromCollegeGet(collegeId);
        model.setViewName("group/groupStudent");
        model.addObject("student", student);
        model.addObject("lessons", lessons);

        return model;
    }


}
