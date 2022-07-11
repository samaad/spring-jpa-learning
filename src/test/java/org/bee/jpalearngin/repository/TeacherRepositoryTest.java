package org.bee.jpalearngin.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.bee.jpalearngin.entity.Course;
import org.bee.jpalearngin.entity.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TeacherRepositoryTest {

    @Autowired
    private TeacherRepository repository;

    @Test
    public void saveTeacher() throws Exception {
        Course coursesDBA = Course.builder()
            .credit("4").title("DBA").build();
        Course coursesJava = Course.builder()
            .credit("6").title("JAVA").build();

        Teacher teacher = Teacher.builder()
            .firstName("Qutub")
            .lastName("Khan")
            .email("qutub@gmail")
//            .courses(List.of(coursesDBA, coursesJava))
            .build();

        repository.save(teacher);
    }
}