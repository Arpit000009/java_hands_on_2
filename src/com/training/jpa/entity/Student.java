package com.training.jpa.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

@Entity
public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long studId;
	
	private String name;
	
	
	@ManyToMany
	@JoinTable(
			name="student_course_table",
			joinColumns = @JoinColumn(name="studId"),
			inverseJoinColumns = @JoinColumn(name = "courseId")
			)
	private List<Course> courses;
}
