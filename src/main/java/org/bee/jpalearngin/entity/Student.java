package org.bee.jpalearngin.entity;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "student", uniqueConstraints = @UniqueConstraint(
    name="emailid_unique",
    columnNames = "email_address"
))
@ToString
public class Student {

    @Id
    @SequenceGenerator(name = "student_sequence", sequenceName = "student_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_sequence")
    @Column(name="id", updatable = false)
    private Long studentId;
    @Column(name="first_name", nullable = false, columnDefinition = "TEXT")
    private String firstName;
    @Column(name="last_name", nullable = false, columnDefinition = "TEXT")
    private String lastName;

    @Column(name = "email_address", nullable = false, columnDefinition = "TEXT")
    private String emailID;

    @Embedded
    private Guardian guardian;
}
