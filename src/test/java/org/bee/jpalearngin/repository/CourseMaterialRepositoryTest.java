package org.bee.jpalearngin.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.bee.jpalearngin.entity.Course;
import org.bee.jpalearngin.entity.CourseMaterial;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CourseMaterialRepositoryTest {

    @Autowired
    private CourseMaterialRepository courseMaterialRepository;

    @Test
    public void SaveCourseMaterial(){
        Course course = Course.builder()
            .title("DSA")
            .credit("6").build();

        CourseMaterial courseMaterial = CourseMaterial.builder()
            .url("www.yahoo.com")
//            .course(course)
            .build();

        courseMaterialRepository.save(courseMaterial);
    }

    @Test
    public void getAllCourseMaterial(){
        List<CourseMaterial> all = courseMaterialRepository.findAll();
        all.forEach(System.out::println);
    }
}