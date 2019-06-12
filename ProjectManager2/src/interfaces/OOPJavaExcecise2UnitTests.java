package interfaces;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.GregorianCalendar;
//import org.junit.Ignore;
import org.junit.*;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

//import controle.API;

class OOPJavaExcecise2UnitTests {
	private final apiImplementation api = new apiImplementation();
	
	@Nested
	class CreateHappyPath{
		@Test
		void createProject() {
			assertEquals(true, api.createProject( "p1",new GregorianCalendar(2019,5,12), new GregorianCalendar(2019,6,13)));
			ArrayList<String> al = new ArrayList<String>();
			al.add("p1");
			assertEquals(al,api.getProjectNames());
		}
		@Test
		void createEmployee() {
			assertEquals(true,api.createEmployee("e1", 1000));
			ArrayList<String>el=new ArrayList<String>();
			el.add("e1");
			assertEquals(el,api.getEmployeeNames());
		}
		@Test
		void createDepartmentLead() {
			assertEquals(true,api.createDepartmentlead("d1", 1000));
			ArrayList<String>el=new ArrayList<String>();
			el.add("d1");
			assertEquals(el,api.getDepartmentLeadNames());
			assertEquals(el,api.getEmployeeNames());
		}
	}
	@Nested
	class AssignHappyPath {
		@Test
		void addEmployeeToProject1() {
			create();
			assertEquals(true,api.addEmployeeToProject("e1", "p1"));
		}
		@Test
		void addEmployeeToProject2() {
			create();
			assertEquals(true,api.addEmployeeToProject("e1", "p1"));
			assertEquals(true,api.addEmployeeToProject("e1", "p2"));
			assertEquals(true,api.addEmployeeToProject("e2", "p1"));
			ArrayList<String>el=new ArrayList<String>();
			el.add("e1");
			assertEquals(el,api.getEmployeesWorkingOnProject("p2"));
			el.add("e2");
			assertEquals(el,api.getEmployeesWorkingOnProject("p1"));
			ArrayList<String>pl=new ArrayList<String>();
			pl.add("p1");
			assertEquals(pl,api.getProjectsOfEmployees("e2"));
			pl.add("p2");
			assertEquals(pl,api.getProjectsOfEmployees("e1"));
			
		}
		@Test
		void addEmployeeToDepartment1() {
			create();
			assertEquals(true,api.addEmployeeToDepartment("e1", "d1"));
		}
		@Test
		void addEmployeeToDepartment2() {
			create();
			assertEquals(true,api.addEmployeeToDepartment("e1", "d1"));
			assertEquals(true,api.addEmployeeToDepartment("e2", "d1"));
			ArrayList<String>el=new ArrayList<String>();
			el.add("d1");
			el.add("e1");
			el.add("e2");
			assertEquals(el,api.getEmployeesOfDepartment("d1"));			
		}
		@Test
		void addEmployeeToTwoDepartment() {
			create();
			assertEquals(true,api.addEmployeeToDepartment("e1", "d1"));
			assertEquals(true,api.addEmployeeToDepartment("e1", "d2"));
			ArrayList<String>el1=new ArrayList<String>();
			el1.add("d1");
			ArrayList<String>el2=new ArrayList<String>();
			el2.add("d2");
			el2.add("e1");
			assertEquals(el1,api.getEmployeesOfDepartment("d1"));
			assertEquals(el2,api.getEmployeesOfDepartment("d2"));	
		}
		private void create() {
			api.createEmployee( "e1",1000);
			api.createEmployee( "e2",1000);
			api.createDepartmentlead("d1", 3000);
			api.createDepartmentlead("d2", 3000);
			api.createProject( "p1",new GregorianCalendar(2019,6,12), new GregorianCalendar(2019,6,13));
			api.createProject( "p2",new GregorianCalendar(2019,6,12), new GregorianCalendar(2019,6,13));
		}

	}
	@Nested
	class AnswerHappyPath {
		@Test
		void getDepartmentLeadsdOfProject() {
			create();
			ArrayList<String>dl=new ArrayList<String>();
			dl.add("d1");
			dl.add("d2");
			assertEquals(dl,api.getDepartmentLeadsOfProject("p1"));
		}
		@Test
		void getProjectsOfDepartment() {
			create();
			ArrayList<String>dl1Emp=new ArrayList<String>();
			dl1Emp.add("d1");
			dl1Emp.add("e1");
			dl1Emp.add("e2");
			assertEquals(dl1Emp,api.getEmployeesOfDepartment("d1"));
		}
		@Test
		void getMonthlyCostsofProject(){
			create();
			assertEquals(2000,api.getMonthlyCostsOfProject("p1"));
		}
		@Test
		void getMonthlyCostsofDepartment(){
			create();
			assertEquals(5500,api.getMonthlyCostsOfDepartment("d1"));
		}
		
		private void create() {
			api.createEmployee( "e1",1000);
			api.createEmployee( "e2",1500);
			api.createEmployee( "e3",1000);
			api.createDepartmentlead("d1", 3000);
			api.createDepartmentlead("d2", 3000);
			api.createProject( "p1",new GregorianCalendar(2019,6,12), new GregorianCalendar(2019,6,13));
			api.createProject( "p2",new GregorianCalendar(2019,6,12), new GregorianCalendar(2019,6,13));
			api.addEmployeeToDepartment("e1", "d1");
			api.addEmployeeToDepartment("e2", "d1");
			api.addEmployeeToDepartment("e3", "d2");
			api.addEmployeeToProject("e1", "p1");
			api.addEmployeeToProject("e2", "p2");
			api.addEmployeeToProject("e3", "p1");
		}
	}
	@Nested
	class Change{
		@Test 
		void ChangeEmployee() {
			api.createEmployee( "e1",1000);
			api.createDepartmentlead("d1", 3000);
			api.createProject( "p1",new GregorianCalendar(2019,6,12), new GregorianCalendar(2019,6,13));
			api.addEmployeeToDepartment("e1", "d1");
			api.addEmployeeToProject("e1", "p1");
			
			//correct input, other name & salary
			assertEquals(true,api.changeEmployee("e1","e2", 2000));
			ArrayList<String>empAll=new ArrayList<String>();
			empAll.add("e2");
			empAll.add("d1");
			ArrayList<String>empD1=new ArrayList<String>();
			empD1.add("d1");
			empD1.add("e2");
			ArrayList<String>empP1=new ArrayList<String>();
			empP1.add("e2");
			assertEquals(empAll,api.getEmployeeNames());
			assertEquals(empD1,api.getEmployeesOfDepartment("d1"));
			assertEquals(empP1,api.getEmployeesWorkingOnProject("p1"));
			assertEquals(5000,api.getMonthlyCostsOfDepartment("d1"));
			assertEquals(2000,api.getMonthlyCostsOfProject("p1"));	
		}
		@Test
		void ChangeDepartmentLeadViaDeprecated() {
			api.createEmployee( "e2",1000);
			api.createDepartmentlead("d2", 3000);
			api.createProject( "p1",new GregorianCalendar(2019,6,12), new GregorianCalendar(2019,6,13));
			api.addEmployeeToDepartment("e2", "d2");
			api.addEmployeeToProject("e2", "p1");
			
			//correct input, other name & salary
			assertEquals(true,api.changeDepartmentlead("d2","d1", 2000));
			ArrayList<String>empAll=new ArrayList<String>();
			empAll.add("e2");
			empAll.add("d1");
			ArrayList<String>empD1=new ArrayList<String>();
			empD1.add("d1");
			empD1.add("e2");
			ArrayList<String>leadP1=new ArrayList<String>();
			leadP1.add("d1");
			assertEquals(empAll,api.getEmployeeNames());
			assertEquals(empD1,api.getEmployeesOfDepartment("d1"));
			assertEquals(leadP1,api.getDepartmentLeadsOfProject("p1"));
			assertEquals(3000,api.getMonthlyCostsOfDepartment("d1"));		
		}
		@Test
		void ChangeDepartmentLeadViaChangeEmployee() {
			api.createEmployee( "e2",1000);
			api.createDepartmentlead("d2", 3000);
			api.createProject( "p1",new GregorianCalendar(2019,6,12), new GregorianCalendar(2019,6,13));
			api.addEmployeeToDepartment("e2", "d2");
			api.addEmployeeToProject("e2", "p1");
			
			//correct input, other name & salary
			assertEquals(true,api.changeEmployee("d2","d1", 2000));
			ArrayList<String>empAll=new ArrayList<String>();
			empAll.add("e2");
			empAll.add("d1");
			ArrayList<String>empD1=new ArrayList<String>();
			empD1.add("d1");
			empD1.add("e2");
			ArrayList<String>leadP1=new ArrayList<String>();
			leadP1.add("d1");
			assertEquals(empAll,api.getEmployeeNames());
			assertEquals(empD1,api.getEmployeesOfDepartment("d1"));
			assertEquals(leadP1,api.getDepartmentLeadsOfProject("p1"));
			assertEquals(3000,api.getMonthlyCostsOfDepartment("d1"));		
		}
		@Test
		void changeProject() {
			api.createEmployee( "e1",1000);
			api.createDepartmentlead("d1", 3000);
			api.createProject( "p1",new GregorianCalendar(2019,6,12), new GregorianCalendar(2019,6,13));
			api.addEmployeeToDepartment("e1", "d1");
			api.addEmployeeToProject("e1", "p1");
			assertEquals(true,api.changeProject("p1","p2", new GregorianCalendar(2019,6,12), new GregorianCalendar(2019,6,13)));
			ArrayList<String>empP2=new ArrayList<String>();
			empP2.add("e1");
			ArrayList<String>leadP2=new ArrayList<String>();
			leadP2.add("d1");
			assertEquals(empP2,api.getEmployeesWorkingOnProject("p2"));
			assertEquals(leadP2,api.getDepartmentLeadsOfProject("p2"));
			assertEquals(1000,api.getMonthlyCostsOfProject("p2"));		
			
			
		}
		
		@Test
		void deleteProject() {
			api.createEmployee( "e1",1000);
			api.createDepartmentlead("d1", 3000);
			api.createProject( "p1",new GregorianCalendar(2019,6,12), new GregorianCalendar(2019,6,13));
			api.addEmployeeToDepartment("e1", "d1");
			api.addEmployeeToProject("e1", "p1");
			assertEquals(true,api.addEmployeeToProject("d1", "p1"));
			assertEquals(true,api.deleteProject("p1"));	
			assertEquals(new ArrayList<String>(),api.getProjectNames());
			assertEquals(new ArrayList<String>(),api.getDepartmentLeadsOfProject("p1"));
			assertEquals(new ArrayList<String>(),api.getEmployeesWorkingOnProject("p1"));
			assertEquals(new ArrayList<String>(),api.getProjectsOfDepartment("d1"));
			assertEquals(new ArrayList<String>(),api.getProjectsOfEmployees("d1"));
			assertEquals(new ArrayList<String>(),api.getProjectsOfEmployees("e1"));	
		}
		
		@Test
		void deleteEmployee() {
			api.createEmployee( "e1",1000);
			api.createDepartmentlead("d1", 3000);
			api.createProject( "p1",new GregorianCalendar(2019,6,12), new GregorianCalendar(2019,6,13));
			api.addEmployeeToDepartment("e1", "d1");
			api.addEmployeeToProject("e1", "p1");
			assertEquals(true,api.addEmployeeToProject("d1", "p1"));
			assertEquals(true,api.deleteEmployee("e1"));	
			ArrayList<String>al=new ArrayList<String>();
			al.add("d1");
			
			ArrayList<String>al2=new ArrayList<String>();
			al2.add("p1");
			
			assertEquals(al,api.getDepartmentLeadsOfProject("p1"));
			assertEquals(al,api.getEmployeesWorkingOnProject("p1"));
			assertEquals(al2,api.getProjectsOfDepartment("d1"));
			assertEquals(al,api.getEmployeesOfDepartment("d1"));
			assertEquals(3000,api.getMonthlyCostsOfDepartment("d1"));
			assertEquals(3000,api.getMonthlyCostsOfProject("p1"));
			assertEquals(new ArrayList<String>(),api.getProjectsOfEmployees("e1"));	
		}
		
		@Test
		void deleteDepartmentLead() {
			api.createEmployee( "e1",1000);
			api.createDepartmentlead("d1", 3000);
			api.createProject( "p1",new GregorianCalendar(2019,6,12), new GregorianCalendar(2019,6,13));
			api.addEmployeeToDepartment("e1", "d1");
			api.addEmployeeToProject("e1", "p1");
			assertEquals(true,api.addEmployeeToProject("d1", "p1"));
			assertEquals(true,api.deleteDepartmentlead("d1"));	
			ArrayList<String>al=new ArrayList<String>();
			al.add("e1");
			
			ArrayList<String>al2=new ArrayList<String>();
			al2.add("p1");
			
			assertEquals(new ArrayList<String>(),api.getDepartmentLeadNames());
			assertEquals(al,api.getEmployeeNames());
			assertEquals(new ArrayList<String>(),api.getDepartmentLeadsOfProject("p1"));
			assertEquals(al,api.getEmployeesWorkingOnProject("p1"));
			assertEquals(new ArrayList<String>(),api.getProjectsOfDepartment("d1"));
			assertEquals(new ArrayList<String>(),api.getEmployeesOfDepartment("d1"));	
		}
		
		@Test
		//@Ignore //produces error
		void addEmployeeToAnotherProject() {
			api.createEmployee( "e1",1000);
			api.createDepartmentlead("d1", 3000);
			api.createProject( "p1",new GregorianCalendar(2019,6,12), new GregorianCalendar(2019,6,13));
			api.createProject( "p2",new GregorianCalendar(2019,6,12), new GregorianCalendar(2019,6,13));
			api.addEmployeeToDepartment("e1", "d1");
			api.addEmployeeToProject("e1", "p1");
			
			assertEquals(true,api.addEmployeeToProject("e1", "p2"));
			
			ArrayList<String>p_al=new ArrayList<String>();
			p_al.add("p1");
			p_al.add("p2");
			ArrayList<String>e_al=new ArrayList<String>();
			e_al.add("e1");
			ArrayList<String>d_al=new ArrayList<String>();
			d_al.add("d1");
			
			assertEquals(d_al,api.getDepartmentLeadsOfProject("p1"));
			assertEquals(d_al,api.getDepartmentLeadsOfProject("p2"));
			assertEquals(e_al,api.getEmployeesWorkingOnProject("p1"));
			assertEquals(e_al,api.getEmployeesWorkingOnProject("p2"));
			assertEquals(e_al,api.getEmployeesWorkingOnProject("p1"));
			assertEquals(e_al,api.getEmployeesWorkingOnProject("p1"));
			assertEquals(p_al,api.getProjectsOfDepartment("d1"));
			assertEquals(p_al,api.getProjectsOfEmployees("e1"));	
		}
		
		@Test
		void addEmployeeToAnotherDepartment() {
			api.createEmployee( "e1",1000);
			api.createDepartmentlead("d1", 3000);
			api.createDepartmentlead("d2", 4000);
			api.createProject( "p1",new GregorianCalendar(2019,6,12), new GregorianCalendar(2019,6,13));
			api.addEmployeeToDepartment("e1", "d1");
			api.addEmployeeToProject("e1", "p1");
			
			assertEquals(true,api.addEmployeeToDepartment("e1", "d2"));
			
			ArrayList<String>p_al=new ArrayList<String>();
			p_al.add("p1");
			ArrayList<String>e1_al=new ArrayList<String>();
			e1_al.add("d1");
			ArrayList<String>e2_al=new ArrayList<String>();
			e2_al.add("d2");
			e2_al.add("e1");
			ArrayList<String>d_al=new ArrayList<String>();
			d_al.add("d2");
			
			assertEquals(d_al,api.getDepartmentLeadsOfProject("p1"));
			
			assertEquals(new ArrayList<String>(),api.getProjectsOfDepartment("d1"));
			assertEquals(p_al,api.getProjectsOfDepartment("d2"));
			
			assertEquals(e1_al,api.getEmployeesOfDepartment("d1"));	
			assertEquals(e2_al,api.getEmployeesOfDepartment("d2"));	
			
			assertEquals(3000,api.getMonthlyCostsOfDepartment("d1"));
			assertEquals(5000,api.getMonthlyCostsOfDepartment("d2"));	
		}
		
		
		
	}
	
	
	

}
