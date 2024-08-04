package com.tca.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.tca.config.HibernateConfig;

// Uddesh --> Mala kiva saglyna EKACH session factory cha Object Milava

public class HibernateUtil   
{
	private static SessionFactory sessionFactory=null;
	
	public static SessionFactory getSessionFactory()
	{
		if(sessionFactory==null)
		{
			
			try
			{
				Configuration configuration = new Configuration();
				configuration.configure( HibernateConfig.FILE_NAME );
				sessionFactory = configuration.buildSessionFactory();
			}
			catch(Exception e)
			{
				e.printStackTrace();
				return null;
			}
			
			return sessionFactory;	
			
			
		/*	
			sessionFactory = new  Configuration().configure().buildSessionFactory();
		*/
		
		/*
			sessionFactory = new  Configuration()
					.configure()
					.buildSessionFactory();
		*/			
		}
		
		return sessionFactory;
	}
	
	public static void closeSessionFactory()
	{
		if(sessionFactory!=null)
		{
			sessionFactory.close();
		}
	}
	
}
