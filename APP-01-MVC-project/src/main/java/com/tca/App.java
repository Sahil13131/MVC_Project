package com.tca;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.tca.constants.Status;
import com.tca.entities.Student;
import com.tca.factory.StudentServiceFactory;
import com.tca.service.StudentService;
import com.tca.util.HibernateUtil;

public class App 
{
	
	
    public static void main( String[] args )
    {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
    	try
    	{
    		
    		System.out.println("***** Welcome To Techno Comp Academy ********");
    		
    	while(true)
    	{
    		System.out.println("MENU");
    		System.out.println("	Press 0 for Exit the Application ");
    		System.out.println("	Press 1 for Add a Record ");
    		System.out.println("	Press 2 for Update a Record ");
    		System.out.println("	Press 3 for Delete a Record ");
    		System.out.println("	Press 4 for Show all Record ");
    		System.out.println("	Press 5 for Show by ID  ");
    		System.out.println("	Press 6 for Show by Name  ");
    		
    		System.out.print("What is Your choice : ");
    		int choice = Integer.parseInt(br.readLine());
    		
    		//int choice = Integer.parseInt(req.getParameter("choice"));
    		
    		
    		switch(choice)
    		{
    		case 0:
    				HibernateUtil.closeSessionFactory();
    				System.exit(0);
    		case 1:
    				//optionSave();
    				// optionsave should returns Output messgage/ status 
    				// based on that i will decide what to sent UI Layer
    				// Prepare content/message and give to UI Layer
    				
    				System.out.print("Enter The Name : ");
    				String name = br.readLine();
        		
    				System.out.print("Enter The Percentage : ");
    				double per = Double.parseDouble(br.readLine());
        		
    				System.out.print("Enter The City : ");
    				String city = br.readLine();
    			
    			
    				String rval = optionSave(name,per,city);
    			       
    	    		if( rval == Status.STUDENT_SAVE_SUCCESS )
    	    		{
    	    			System.out.println("Student Data is Saved for ID : "+ name);
    	    			
    	    		}
    	    		else if(rval == Status.STUDENT_SAVE_FAILURE)
    	    		{
    	    			System.out.println("Failed to save Student Data for  ID : "+ name);
    	    		}
    	    		else if(rval == Status.STUDENT_EXIST)
    	    		{
    	    			System.out.println("Student Data  is already Existed for  ID : "+ name);
    	    		}
    				
    				break;
    		case 2:
    				optionModify();
    				break;
    		case 3:
    				optionErase();
    				break;
    			
    		case 4:
    				optionFetchAll();
    				break;
    		case 5:
    				optionFetchById();
    				break;
    		case 6:
    				optionFetchByName();
    				break;
    		default :
    			System.out.println("Invalid Choice !!!");
    		}//switch
    		
    	}//while	
    		
    	 		
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    	finally
    	{
    		HibernateUtil.closeSessionFactory();
    	}
    }
     
      
    
    
    
    public static void optionErase()
    {
    	StudentService service = StudentServiceFactory.getInstanceService();
    	
    	try
    	{
    		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    		int id;
    		   		
    			try
    			{
    				System.out.print("Enter Student ID for Delete Data :" );
    				id = Integer.parseInt(br.readLine());
    			}
    			catch(NumberFormatException ne)
    			{
    				System.out.println("You have given Invalid Input : 'Number Expected' ");
    				return;
    			}
    		
    		String sval = service.eraseStudent(id);
    		
    		if(sval==Status.STUDENT_DOES_NOT_EXIST)
    		{
    			System.out.println("No Record Exist for ID : " + id);
    		}
    		else if(sval==Status.STUDENT_DELETE_SUCCESS)
    		{
    			System.out.println("Record Deleted for ID : " + id);
    		}
    		else if (sval==Status.STUDENT_DELETE_FAILURE)
    		{
    			System.out.println("Unable to delete Record for ID : " + id);
    		}
    		
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    		System.out.println("Error While Deleting Record !!");
    	}
    }
    
    public static void optionModify()
    {
    	StudentService service = StudentServiceFactory.getInstanceService();
    	
    	try
    	{
    		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    		System.out.print("Enter Student ID for Modification :" );
    		int id = Integer.parseInt(br.readLine());
    		  		
    		Student s =  service.fetchStudentById(id); //SDeo 33 Pune
    		
    		if(s==null)
    		{
    			System.out.println("Student Record Not found for ID : "+ id);
    			return;
    		}
    		System.out.println("For NO Input Just Press ENTER \n");
    		
    		System.out.print("Old Name : [ " + s.getName()+" ] Enter New Name : " );
    		String name = br.readLine();
    		if(! name.isEmpty())
    		{
    			s.setName(name);
    			System.out.println("I am not empty");
    			
    		}
    		
    		System.out.print("Old Per : [" + s.getPer() +"] Enter New Percentage : ");
    		String sper = br.readLine();
    		if( ! sper.isEmpty())
    		{
    			double per = Double.parseDouble(sper);
    			s.setPer(per);
    		}
    		
    		System.out.print("Old City: [" + s.getCity() +"] Enter New City : ");
    		String city = br.readLine();
    		if(!city.isEmpty())
    		{
    			s.setCity(city);
    		}
    		
    		String sval = service.modifyStudent(s);
    		if(sval==Status.STUDENT_UPDATE_SUCCESS)
    		{
    			System.out.println("Record Update for ID : " + id);
    		}
    		else if(sval==Status.STUDENT_UPDATE_FAILURE)
    		{
    			System.out.println("Failed tp Update Record for ID : " + id);
    		}
    		
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    		System.out.println("Error while Modifing Record !!");
    	}
    }
    
    public static void optionFetchAll()
    {
    	try
    	{
       		StudentService service = StudentServiceFactory.getInstanceService();
    		
    		List<Student> list = service.fetchAllStudent();
    		
    		
    		if(list==null || list.isEmpty())
    		{
    			System.out.println("No Record Found for Name !!");
    		}
    		else
    		{
    			for(Student S : list)
    			{
    				System.out.println("STUDENT RNO  : " + S.getRno());
        			System.out.println("STUDENT NAME : " + S.getName());
        			System.out.println("STUDENT PER  : " + S.getPer());
        			System.out.println("STUDENT CITY : " + S.getCity());
        			System.out.println("CREATE  TIME : " + S.getDateCreated());
        			System.out.println("UPDATE  TIME : " + S.getLastUpdated());
        			System.out.println("---------------------------------------------" );
    			}
    		
    		}
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    }
    
    
    public static void optionFetchByName()
    {
    	try
    	{
    		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		
    		System.out.print("Enter the Name to Search : ");
    		String name = br.readLine();
    	  		
    		
    		StudentService service = StudentServiceFactory.getInstanceService();
    		
    		List<Student> list = service.fetchStudentByName(name);
    		
    		
    		if(list==null || list.isEmpty())
    		{
    			System.out.println("No Record Found for Name : " + name );
    		}
    		else
    		{
    			for(Student S : list)
    			{
    				System.out.println("STUDENT RNO  : " + S.getRno());
        			System.out.println("STUDENT NAME : " + S.getName());
        			System.out.println("STUDENT PER  : " + S.getPer());
        			System.out.println("STUDENT CITY : " + S.getCity());
        			System.out.println("CREATE  TIME : " + S.getDateCreated());
        			System.out.println("UPDATE  TIME : " + S.getLastUpdated());
        			System.out.println("---------------------------------------------" );
    			}
    		
    		}
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    }
       
    public static void optionFetchById()
    {
    	try
    	{
    		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    		System.out.print("Enter the ID to Search : ");
    		int id = Integer.parseInt(br.readLine());
    		
    		StudentService service = StudentServiceFactory.getInstanceService();
    		
    		Student S = service.fetchStudentById(id);
    		
    		if(S==null)
    		{
    			System.out.println("No Record Found for ID : " + id);
    		}
    		else
    		{
    			System.out.println("Record Found for ID : " + id);
    			System.out.println("STUDENT RNO  : " + S.getRno());
    			System.out.println("STUDENT NAME : " + S.getName());
    			System.out.println("STUDENT PER  : " + S.getPer());
    			System.out.println("STUDENT CITY : " + S.getCity());
    			System.out.println("CREATE  TIME : " + S.getDateCreated());
    			System.out.println("UPDATE  TIME : " + S.getLastUpdated());
    		}
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    public static  String optionSave(String name, double per, String city)
    {
    	try
    	{
    		 BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    		//System.out.print("Enter The Roll Number : ");
    		//int rno = Integer.parseInt(br.readLine());
    		
    		
    		 				
    			
    		Student student = new Student();
    		//student.setRno(rno);
    		student.setName(name);
    		student.setPer(per);
    		student.setCity(city);
      
    		StudentService studentService =  StudentServiceFactory.getInstanceService();
       
       
    		String rval = studentService.addStudent(student);
       
    		return rval;
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    		return Status.STUDENT_SAVE_FAILURE;
    	}
       
    }
}
