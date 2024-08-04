package com.tca.dao;

import java.util.List;

import com.tca.entities.Student;

public interface StudentDao 
{
	public abstract String add(Student student);
	public abstract Student fetchById(Integer id);
	public abstract List<Student> fetchByName(String name);
	public abstract List<Student> fetchAll();
	
	public abstract String modify(Student student);
	public abstract String erase(Integer id);
}
