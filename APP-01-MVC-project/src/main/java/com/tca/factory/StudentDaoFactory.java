package com.tca.factory;

import com.tca.dao.StudentDao;
import com.tca.dao.StudentDaoImpl;

public class StudentDaoFactory 
{
	private static StudentDao studentDao=null;
	
	public static StudentDao getInstanceStudentDao()
	{
		if(studentDao==null)
		{
			studentDao = new StudentDaoImpl(); 
		}
		return studentDao;
	}
}
