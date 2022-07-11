package org.bee.jpalearngin.repository;

import java.util.List;
import java.util.Optional;
import org.bee.jpalearngin.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface StudentRepository extends JpaRepository<Student, Long> {

    public List<Student> findByFirstName(String firstName);
    public List<Student> findByFirstNameContaining(String firstNameContaining);
    public List<Student> findByLastNameNotNull();
    public List<Student> findByGuardianName(String guardianName);

    // JPQL
    @Query("select s from Student s where s.emailID = ?1")
    Student getStudentByEmailAddress(String emailId);

    @Query("select s.firstName from Student s where s.emailID = ?1")
    Optional<String> getStudentFirstNameByEmailAddress(String emailId);

//    NATIVE SQL
    @Query(value = "select * from student s where s.email_address = ?1", nativeQuery = true)
    Student getStudentFirstNameByEmailAddressNative(String emailId);


    //    NATIVE
    @Query(value = "select * from student s where s.email_address = :email_address", nativeQuery = true)
    Student getStudentFirstNameByEmailAddressNamedParam(@Param("email_address") String emailId);

    @Modifying
    @Transactional
    @Query(value="update student set first_name = ?1 where email_address = ?2", nativeQuery= true)
    Integer updateStudentNameByEmailId(String firstName, String emailId);
}
