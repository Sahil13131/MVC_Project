package com.tca.dao;

import java.util.List;


import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.tca.constants.Status;
import com.tca.entities.Student;
import com.tca.util.HibernateUtil;

public class StudentDaoImpl implements StudentDao {

	@Override
	public String add(Student student) 
	{
		SessionFactory sessionFactory=HibernateUtil.getSessionFactory();
				
		try( Session session = sessionFactory.openSession() )
		{
			//
			//Student tempStudent = session.get(Student.class, student.getRno() );
			
			//if(tempStudent==null)
			{
				Transaction transaction = session.beginTransaction();
				session.save(student);
				transaction.commit();
				return Status.STUDENT_SAVE_SUCCESS;
			}
			//else
		//	{
			//	return Status.STUDENT_EXIST;
		//	}
		}
		catch(Exception  e)
		{
			e.printStackTrace();
			return Status.STUDENT_SAVE_FAILURE;
		}
		
	}

	@Override
	public Student fetchById(Integer id) 
	{
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		
		try(Session session = sessionFactory.openSession())
		{			
			Student S = session.get(Student.class, id);
			return S;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
		
	}

	
	@Override
	public List<Student> fetchByName(String name) 
	{
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		
		try(Session session = sessionFactory.openSession())
		{			
			String qry = "FROM Student WHERE name LIKE '%"+name+"%'";
			Query q = session.createQuery(qry);
			
			List<Student> list = q.list();
			
			return list;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Student> fetchAll() 
	{
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		
		try(Session session = sessionFactory.openSession())
		{			
			String qry = "FROM Student";
			Query q = session.createQuery(qry);
			
			List<Student> list = q.list();
			
			return list;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String modify(Student student) 
	{
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		
		try(Session session = sessionFactory.openSession())
		{
			Transaction transaction = session.beginTransaction();
			
			session.update(student);
			transaction.commit();
			return Status.STUDENT_UPDATE_SUCCESS;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return Status.STUDENT_UPDATE_FAILURE;
		}
		
	}

	@Override
	public String erase(Integer id) 
	{
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		
		try(Session session = sessionFactory.openSession())
		{
			Transaction transaction = session.beginTransaction();
			
			Student s = session.get(Student.class, id);
			if(s==null)
			{
				return Status.STUDENT_DOES_NOT_EXIST;
			}
			
			session.delete(s);
			transaction.commit();
			return Status.STUDENT_DELETE_SUCCESS;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return Status.STUDENT_DELETE_FAILURE;
		}
	}

	

	
	
	
	
	
	
	
	
	
	
	
	
}
