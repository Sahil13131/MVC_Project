package com.tca.service;

import java.util.List;

import com.tca.entities.Student;

public interface StudentService 
{
	public abstract String addStudent(Student student);
	public abstract Student fetchStudentById(Integer id);
	public abstract List<Student> fetchStudentByName(String name);
	public abstract List<Student> fetchAllStudent();
	
	public abstract String modifyStudent(Student student);
	public abstract String eraseStudent(Integer id);
}
