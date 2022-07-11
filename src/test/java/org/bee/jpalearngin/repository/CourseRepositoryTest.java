package org.bee.jpalearngin.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import org.bee.jpalearngin.entity.Course;
import org.bee.jpalearngin.entity.Guardian;
import org.bee.jpalearngin.entity.Student;
import org.bee.jpalearngin.entity.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@SpringBootTest
class CourseRepositoryTest {

    @Autowired
    private CourseRepository courseRepository;

    @Test
    public void printCourse(){
        List<Course> all = courseRepository.findAll();
        all.forEach(System.out::println);
    }

    @Test
    public void saveCoureWithTeacher(){
        Teacher teacher = Teacher.builder()
            .firstName("aysha")
            .email("ayesha@abc.com").lastName("alBastami")
            .build();

        Course course = Course.builder().title("Python")
            .credit("6")
            .teacher(teacher)
            .build();

        courseRepository.save(course);
    }

    @Test
    public void findAllPagination(){
        Pageable firstPageWithThreeRecords = PageRequest.of(0, 3);
        Pageable secondPageWithThreeRecords = PageRequest.of(1, 2);

        List<Course> courses = courseRepository.findAll(secondPageWithThreeRecords).getContent();

        Long totalElements = courseRepository.findAll(secondPageWithThreeRecords).getTotalElements();
        int totalPages = courseRepository.findAll(secondPageWithThreeRecords).getTotalPages();

        courses.forEach(System.out::println);
        System.out.println("totalElements ->" + totalElements);
        System.out.println("totalPages --> " + totalPages);
    }

    @Test
    public void findAllSorting(){
        Pageable sortByTitle = PageRequest.of(0, 2, Sort.by("title"));
        Pageable sortByCredit = PageRequest.of(0, 2, Sort.by("credit").descending());
        Pageable sortByTitleAndCredit = PageRequest.of(0, 2, Sort.by("title").descending()
        .and(Sort.by("credit")));

        List<Course> courses = courseRepository.findAll(sortByTitle).getContent();
        courses.forEach(System.out::println);

    }

    @Test
    public void printfindByTitleContiance(){
        Pageable firstPageWIthFirstRecord = PageRequest.of(0, 1);
        Pageable firstPageWIthSecondRecord = PageRequest.of(1, 1);

        List<Course> courses = courseRepository.findByTitleContaining("D", firstPageWIthFirstRecord).getContent();
        courses.forEach(System.out::println);
    }

    @Test
    public void saveCourseWithStudentAndTeacher(){
        Teacher teacher = Teacher.builder()
            .firstName("heena")
            .email("heena@abc.com").lastName("momin")
            .build();

        Student student = Student.builder()
            .firstName("naszneen")
            .lastName("mistry")
            .emailID("masz@baaz")
            .guardian(Guardian.builder().name("shoaib").mobile("98765").email("shoaib@gmail.com").build())
            .build();
        Student student2 = Student.builder()
            .firstName("eliza")
            .lastName("sss")
            .emailID("eliza@baaz")
            .guardian(Guardian.builder().name("shoaib").mobile("98765").email("shoaib@gmail.com").build())
            .build();
        Course course = Course.builder()
            .title("AI")
            .credit("12")
            .teacher(teacher)
            .students(List.of(student, student2))
            .build();

        courseRepository.save(course);
    }
}