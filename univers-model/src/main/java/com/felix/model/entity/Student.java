package com.felix.model.entity;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "STUDENT")
public class Student {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @NotNull(message = "You have to specify first name")
    @Column(name = "first_name")
    private String firstName;

    @NotNull(message = "You have to specify last name")
    @Column(name = "last_name")
    private String lastName;

    @NotNull(message = "You have to specify age")
    @Min(message = "Minimum value is 0", value = 0)
    @Max(value = 100, message = "Minimum value is 0")
    @Column
    private Integer age;

    @NotNull(message = "Gender must be set!")
    @Column
    private String gender;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "university_id")
    @NotNull(message = "University mustn't be null!")
    private University university;

    public Student() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public University getUniversity() {
        return university;
    }

    public void setUniversity(University university) {
        this.university = university;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return firstName +"-"+ lastName+"-"+age ;
    }
}
