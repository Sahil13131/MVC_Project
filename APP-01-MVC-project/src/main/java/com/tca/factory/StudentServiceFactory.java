package com.tca.factory;

import com.tca.service.StudentService;
import com.tca.service.StudentServiceImpl;

public class StudentServiceFactory 
{
	private static StudentService studentService=null;	
	
	private StudentServiceFactory() {} // private constructor
	
	public static StudentService getInstanceService()
	{
		if(studentService==null)
		{
			studentService = new StudentServiceImpl();
		}
		
		return studentService;
	}
	
}
