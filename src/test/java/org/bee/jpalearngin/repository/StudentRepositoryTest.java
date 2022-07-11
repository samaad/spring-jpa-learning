package org.bee.jpalearngin.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.bee.jpalearngin.entity.Guardian;
import org.bee.jpalearngin.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

//@DataJpaTest
@SpringBootTest
class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @Test
    public void saveStudent(){
        Student student = Student.builder()
            .emailID("shoaib@gmail.com")
            .firstName("shoaib")
            .lastName("doulatabad")
//            .guardianEmail("sarah@gmail.com")
//            .guardianName("sarah")
//            .guardianMobile("109912321")
            .build();

        studentRepository.save(student);
    }

    @Test
    public void printAllStudent(){
        List<Student> studentList = studentRepository.findAll();
        studentList.forEach(System.out::println);
    }

    @Test
    public void saveStudentWithGuarding(){
        Guardian guardian = Guardian.builder()
        .name("shoaib")
        .email("shoaib@gmail.com")
        .mobile("123123123").build();

        Student student = Student.builder()
            .emailID("manar@gmail.com")
            .firstName("manar")
            .lastName("almosawi")
            .guardian(guardian)
            .build();

        studentRepository.save(student);
    }

    @Test
    public void getStudentByFirstName(){
        List<Student> studentList = studentRepository.findByFirstName("manar");
        studentList.forEach(System.out::print);
    }

    @Test
    public void getStudentByFirstNameContaining(){
        List<Student> studentList = studentRepository.findByFirstNameContaining("ma");
        studentList.forEach(System.out::print);
    }

    @Test
    public void getStudentByGardianName(){
        List<Student> studentList = studentRepository.findByGuardianName("sarah");
        studentList.forEach(System.out::print);
    }

    @Test
    public void getStudentByEmailId(){
        Student studentList = studentRepository.getStudentByEmailAddress("shoaib@gmail.com");
        System.out.println(studentList);
    }

    @Test
    public void getStudentNameByEmailId(){
        studentRepository.getStudentFirstNameByEmailAddress("shoaib@gmail.com")
            .ifPresent(System.out::println);

    }

    @Test
    public void getStudentFirstNameByEmailAddressNative(){
        Student std = studentRepository.getStudentFirstNameByEmailAddressNative("shoaib@gmail.com");
        System.out.println(std);
    }

    @Test
    public void getStudentFirstNameByEmailAddressNamedParam(){
        Student std = studentRepository.getStudentFirstNameByEmailAddressNamedParam("shoaib@gmail.com");
        System.out.println(std);
    }

    @Test
    public void updateStudentNameByEmailId(){
        Integer std = studentRepository.updateStudentNameByEmailId("asma","shoaib@gmail.com");
        System.out.println(std);
    }
}