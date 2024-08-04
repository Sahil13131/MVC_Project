package com.tca.service;

import java.util.List;

import com.tca.dao.StudentDao;
import com.tca.entities.Student;
import com.tca.factory.StudentDaoFactory;

public class StudentServiceImpl implements StudentService {

	
	
	@Override
	public String addStudent(Student student) // student= name: xyz, city:pune
	{
		
		student.setName(student.getName().toLowerCase());
		student.setCity(student.getCity().toLowerCase());
		
		
		StudentDao dao = StudentDaoFactory.getInstanceStudentDao();
		String status = dao.add(student);
		return status;
	}

	@Override
	public Student fetchStudentById(Integer id) 
	{
		StudentDao dao = StudentDaoFactory.getInstanceStudentDao();
		Student S = dao.fetchById(id);
		
		if(S != null) // Optional<Student>
		{
			S.setName(S.getName().toUpperCase());
			S.setCity(S.getCity().toUpperCase());
		}
		
		return S;
	}
	
	public List<Student> fetchStudentByName(String name)
	{
		
		if(name.isEmpty())
		{
			return null;
		}
		
		
		StudentDao dao = StudentDaoFactory.getInstanceStudentDao();
		List<Student> list = dao.fetchByName( name.toLowerCase() );
		return list;
	}

	@Override
	public List<Student> fetchAllStudent() 
	{
		StudentDao dao = StudentDaoFactory.getInstanceStudentDao();
		List<Student> list = dao.fetchAll();
		return list;
	}

	@Override
	public String modifyStudent(Student student) 
	{
		
		student.setName(student.getName().toLowerCase());
		student.setCity(student.getCity().toLowerCase());
		StudentDao studentDao = StudentDaoFactory.getInstanceStudentDao();
		String status = studentDao.modify(student);
		return status;
	}

	@Override
	public String eraseStudent(Integer id) 
	{
		StudentDao studentDao = StudentDaoFactory.getInstanceStudentDao();
		String status = studentDao.erase(id);
		return status;
	}
	

}





















