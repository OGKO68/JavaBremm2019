package interfaces;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

//import controle.API;

//@TestInstance(Lifecycle.PER_CLASS)
class OOPJavaExcecise2UnitTestsEdgeCases {
	private final apiImplementation api = new apiImplementation();
	
	//Proj, Emp, DepLeads Each for their own. No combinations
	@Nested
	class CreateProject{
		@Test
		void correctInput() {
			assertEquals(true, api.createProject( "p1",new GregorianCalendar(2019,5,12), new GregorianCalendar(2019,6,13)));
		}
		@Test
		void startAfterEnd() {
			assertEquals(false, api.createProject( "p1",new GregorianCalendar(2019,7,12), new GregorianCalendar(2019,6,13)));
		}
		@Test
		void emtyName() {
			assertEquals(false, api.createProject( "",new GregorianCalendar(2019,7,12), new GregorianCalendar(2019,6,13)));
		}
		@Test
		void nullName() {
			assertEquals(false, api.createProject( null,new GregorianCalendar(2019,7,12), new GregorianCalendar(2019,6,13)));
		}
		@Test
		void nullDate() {
			assertEquals(false, api.createProject( "p1",null, new GregorianCalendar(2019,6,13)));
		}
		@Test
		void identicalName() {
			assertEquals(true, api.createProject( "p1",new GregorianCalendar(2019,6,12), new GregorianCalendar(2019,6,13)));
			assertEquals(false, api.createProject( "p1",new GregorianCalendar(2019,6,12), new GregorianCalendar(2019,6,13)));
		}		
	}
	@Nested
	class ChangeProject{
		@Test
		void correctInputOtherName() {
			api.createProject( "p1",new GregorianCalendar(2019,5,12), new GregorianCalendar(2019,6,13));
			assertEquals(true, api.changeProject("p1", "p2",new GregorianCalendar(2019,5,12), new GregorianCalendar(2019,6,13)));
		}
		@Test
		void correctInputSameName() {
			api.createProject( "p1",new GregorianCalendar(2019,5,12), new GregorianCalendar(2019,6,13));
			assertEquals(true, api.changeProject("p1", "p1",new GregorianCalendar(2019,4,12), new GregorianCalendar(2019,6,13)));
		}
		@Test
		void emtyName() {
			api.createProject( "p1",new GregorianCalendar(2019,5,12), new GregorianCalendar(2019,6,13));
			assertEquals(false, api.changeProject("p1", "",new GregorianCalendar(2019,4,12), new GregorianCalendar(2019,6,13)));
		}
		@Test
		void nullName() {
			api.createProject( "p1",new GregorianCalendar(2019,5,12), new GregorianCalendar(2019,6,13));
			assertEquals(false, api.changeProject("p1", null,new GregorianCalendar(2019,4,12), new GregorianCalendar(2019,6,13)));
		}
		@Test
		void nullName2() {
			api.createProject( "p1",new GregorianCalendar(2019,5,12), new GregorianCalendar(2019,6,13));
			assertEquals(false, api.changeProject(null, "p2",new GregorianCalendar(2019,4,12), new GregorianCalendar(2019,6,13)));
		}
		@Test
		void noProjectWithName() {
			assertEquals(false, api.changeProject("p1", "p2",new GregorianCalendar(2019,4,12), new GregorianCalendar(2019,6,13)));
		}
		@Test
		void identicalName() {
			api.createProject( "p1",new GregorianCalendar(2019,5,12), new GregorianCalendar(2019,6,13));
			api.createProject( "p2",new GregorianCalendar(2019,5,12), new GregorianCalendar(2019,6,13));
			assertEquals(false, api.changeProject("p1", "p2",new GregorianCalendar(2019,4,12), new GregorianCalendar(2019,6,13)));
		}
		@Test
		void wrongDate() {
			api.createProject( "p1",new GregorianCalendar(2019,5,12), new GregorianCalendar(2019,6,13));
			assertEquals(false, api.changeProject("p1", "p1",new GregorianCalendar(2019,7,12), new GregorianCalendar(2019,6,13)));
		}
		@Test
		void nullDate() {
			api.createProject( "p1",new GregorianCalendar(2019,5,12), new GregorianCalendar(2019,6,13));
			assertEquals(false, api.changeProject("p1", "p1",null, new GregorianCalendar(2019,6,13)));
		}
	}
	@Nested
	class GetProjectNames{
		@Test
		void afterCreation() {
			ArrayList<String> a = new ArrayList<String>();
			api.createProject( "p1",new GregorianCalendar(2019,5,12), new GregorianCalendar(2019,6,13));
			api.createProject( "p2",new GregorianCalendar(2019,5,12), new GregorianCalendar(2019,6,13));
			a.add("p1");
			a.add("p2");
			assertEquals(a,api.getProjectNames());
		}
		@Test
		void beforeCreation() {
			ArrayList<String> a = new ArrayList<String>();
			assertEquals(a,api.getProjectNames());
		}
		@Test
		void afterDeletion() {
			ArrayList<String> a = new ArrayList<String>();
			api.createProject( "p1",new GregorianCalendar(2019,5,12), new GregorianCalendar(2019,6,13));
			api.createProject( "p2",new GregorianCalendar(2019,5,12), new GregorianCalendar(2019,6,13));
			a.add("p1");
			a.add("p2");
			a.remove(1);
			api.deleteProject("p2");
			assertEquals(a,api.getProjectNames());
		}
	}
	@Nested //DONE
	class CreateEmployee{
		@Test
		void correctInput() {
			assertEquals(true,api.createEmployee("e1", 1000));
			ArrayList<String>el=new ArrayList<String>();
			el.add("e1");
			assertEquals(el,api.getEmployeeNames());
		}
		@Test
		void emptyName() {
			assertEquals(false,api.createEmployee("", 1000));
			assertEquals(new ArrayList<String>(),api.getEmployeeNames());
		}
		@Test
		void nullName() {
			assertEquals(false,api.createEmployee(null, 1000));
			assertEquals(new ArrayList<String>(),api.getEmployeeNames());
		}
		@Test
		void doubleName() {
			api.createEmployee("e1", 1000);
			assertEquals(false,api.createEmployee("e1", 1000));
			ArrayList<String>el=new ArrayList<String>();
			el.add("e1");
			assertEquals(el,api.getEmployeeNames());
		}
		@Test
		void negativeIncome() {
			assertEquals(false,api.createEmployee("e1", -2000));
			assertEquals(new ArrayList<String>(),api.getEmployeeNames());
		}
		
	}
	@Nested //DONE
	class ChangeEmployee{
		@Test
		void correctInputOtherName() {
			api.createEmployee("e1", 1000);
			assertEquals(true,api.changeEmployee("e1","e2", 2000));
		}
		@Test
		void correctInputSameName() {
			api.createEmployee("e1", 1000);
			assertEquals(true,api.changeEmployee("e1","e1", 2000));
		}
		@Test
		void emptyName() {
			api.createEmployee("e1", 1000);
			assertEquals(false,api.changeEmployee("e1","", 2000));
		}
		@Test
		void nullName() {
			api.createEmployee("e1", 1000);
			assertEquals(false,api.changeEmployee("e1",null, 1000));
		}
		@Test
		void doubleName() {
			api.createEmployee("e1", 1000);
			api.createEmployee("e2", 1000);
			assertEquals(false,api.changeEmployee("e1","e2", 1000));
		}
		@Test
		void negativeIncome() {
			api.createEmployee("e1", 1000);
			assertEquals(false,api.changeEmployee("e1","e1", -2000));
		}
		
	}
	@Nested //DONE
	class GetEmployeeNames{
		@Test
		void afterCreation() {
			ArrayList<String> a = new ArrayList<String>();
			api.createEmployee( "e1",1000);
			api.createEmployee( "e2",1000);
			a.add("e1");
			a.add("e2");
			assertEquals(a,api.getEmployeeNames());
		}
		@Test
		void beforeCreation() {
			ArrayList<String> a = new ArrayList<String>();
			assertEquals(a,api.getEmployeeNames());
		}
		@Test
		void afterDeletion() {
			ArrayList<String> a = new ArrayList<String>();
			api.createEmployee( "e1",1000);
			api.createEmployee( "e2",1000);
			a.add("e1");
			api.deleteEmployee("e2");
			assertEquals(a,api.getEmployeeNames());
		}
		
	}
	@Nested //DONE
	class DeleteEmployees{
		@Test
		void correctInput() {
			api.createEmployee("e1", 1000);
			assertEquals(true,api.deleteEmployee("e1"));
//			ArrayList<String>el=new ArrayList<String>();
//			el.add("e1");
			assertEquals(new ArrayList<String>(),api.getEmployeeNames());
		}
		@Test
		void incorrectInput() {
			assertEquals(false,api.deleteEmployee("e1"));
			api.createEmployee("e1", 1000);
			assertEquals(true,api.deleteEmployee("e1"));
			assertEquals(false,api.deleteEmployee("e1"));
			assertEquals(false,api.deleteEmployee("e2"));
			assertEquals(false,api.deleteEmployee(""));
			assertEquals(false,api.deleteEmployee(null));
		}
	}
	
	@Test
	void getMonthlyCostsOfProject() {
		api.createEmployee( "e1",1000);
		api.createEmployee( "e2",2000);
		api.createProject( "p1",new GregorianCalendar(2019,6,12), new GregorianCalendar(2019,6,13));
		api.addEmployeeToProject("e1", "p1");
		api.addEmployeeToProject("e2", "p1");
		assertEquals(3000,api.getMonthlyCostsOfProject("p1"));
		api.removeEmployeeFromProject("e1", "p1");
		assertEquals(2000,api.getMonthlyCostsOfProject("p1"));
		api.changeEmployee("e2", "e2", 500);
		assertEquals(500,api.getMonthlyCostsOfProject("p1"));
		api.addEmployeeToProject("e1", "p1");
		assertEquals(1500,api.getMonthlyCostsOfProject("p1"));
		api.deleteEmployee("e1");
		assertEquals(500,api.getMonthlyCostsOfProject("p1"));
	}
	@Nested //DONE
	class CreateDepartmentLead{
		@Test
		void correctInput() {
			assertEquals(true,api.createDepartmentlead("d1", 1000));
		}
		@Test
		void emptyName() {
			assertEquals(false,api.createDepartmentlead("", 1000));
		}
		@Test
		void nullName() {
			assertEquals(false,api.createDepartmentlead(null, 1000));
		}
		@Test
		void doubleName() {
			api.createEmployee("e1", 1000);
			assertEquals(false,api.createDepartmentlead("e1", 1000));
		}
		@Test
		void negativeIncome() {
			assertEquals(false,api.createDepartmentlead("e1", -2000));
		}
		@Test
		void doubleNameEmp() {
			api.createEmployee("e1", 1000);
			assertEquals(false,api.createDepartmentlead("e1", 2000));
		}
		
	}
	@Nested //DONE
	class ChangeDepartmentLead{
		@Test
		void correctInputOtherName() {
			api.createDepartmentlead("e1", 1000);
			assertEquals(true,api.changeDepartmentlead("e1","e6", 2000));
		}
		@Test
		void correctInputSameName() {
			api.createDepartmentlead("e1", 1000);
			assertEquals(true,api.changeDepartmentlead("e1","e1", 2000));
		}
		@Test
		void emptyName() {
			api.createDepartmentlead("e1", 1000);
			assertEquals(false,api.changeDepartmentlead("e1","", 2000));
		}
		@Test
		void nullName() {
			api.createDepartmentlead("e1", 1000);
			assertEquals(false,api.changeDepartmentlead("e1",null, 1000));
		}
		@Test
		void doubleName() {
			api.createDepartmentlead("e1", 1000);
			api.createDepartmentlead("e2", 1000);
			assertEquals(false,api.changeDepartmentlead("e1","e2", 1000));
		}
		@Test
		void negativeIncome() {
			api.createDepartmentlead("e1", 1000);
			assertEquals(false,api.changeDepartmentlead("e1","e1", -2000));
		}
		@Test
		void doubleNameEmp() {
			api.createEmployee("e1", 1000);
			api.createDepartmentlead("d1", 1000);
			assertEquals(false,api.changeDepartmentlead("d1","e1", 2000));
		}
	}
	
	//Test of combinations of Prj, Emp 
	@Nested
	class AddEmpToPrj{
		@Test
		void correctInput1() {
			create();
			assertEquals(true,api.addEmployeeToProject("e1", "p1"));
		}
		@Test
		void correctInput2() {
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
		void wrongInput() {
			create();
			assertEquals(false,api.addEmployeeToProject("e3", "p1"));
			assertEquals(false,api.addEmployeeToProject("e1", "p3"));
			assertEquals(false,api.addEmployeeToProject("", "p1"));
			assertEquals(false,api.addEmployeeToProject("e2", ""));
			assertEquals(false,api.addEmployeeToProject("e2", null));
			assertEquals(false,api.addEmployeeToProject(null, "p1"));
		}
		private void create() {
			api.createEmployee( "e1",1000);
			api.createEmployee( "e2",1000);
			api.createProject( "p1",new GregorianCalendar(2019,6,12), new GregorianCalendar(2019,6,13));
			api.createProject( "p2",new GregorianCalendar(2019,6,12), new GregorianCalendar(2019,6,13));
		}
	}
	
	@Nested
	class RemoveEmpFromPrj {
		@Test
		void correctInput() {
			create();
			assertEquals(true,api.addEmployeeToProject("e1", "p1"));
			assertEquals(true,api.addEmployeeToProject("e2", "p1"));
			assertEquals(true,api.removeEmployeeFromProject("e1", "p1"));
			ArrayList<String>el=new ArrayList<String>();
			assertEquals(el,api.getProjectsOfEmployees("e1"));
			el.add("e2");
			assertEquals(el,api.getEmployeesWorkingOnProject("p1"));			
		}
		@Test
		void wrongInput() {
			create();
			assertEquals(true,api.addEmployeeToProject("e1", "p1"));
			assertEquals(true,api.addEmployeeToProject("e2", "p1"));			
			assertEquals(false,api.removeEmployeeFromProject("e3", "p1"));
			assertEquals(false,api.removeEmployeeFromProject("e1", "p3"));
			assertEquals(false,api.removeEmployeeFromProject("", "p1"));
			assertEquals(false,api.removeEmployeeFromProject("e2", ""));
			assertEquals(false,api.removeEmployeeFromProject("e2", null));
			assertEquals(false,api.removeEmployeeFromProject(null, "p1"));
			
		}
		private void create() {
			api.createEmployee( "e1",1000);
			api.createEmployee( "e2",1000);
			api.createProject( "p1",new GregorianCalendar(2019,6,12), new GregorianCalendar(2019,6,13));
			api.createProject( "p2",new GregorianCalendar(2019,6,12), new GregorianCalendar(2019,6,13));
		}
	}
	
	//Test of combinations of  Emp & DepLead
	@Nested
	class addEmpToDep{
		@Test
		void correctInput1() {
			create();
			assertEquals(true,api.addEmployeeToDepartment("e1", "d1"));
		}
		@Test
		void correctInput2() {
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
		void wrongInput() {
			create();
			assertEquals(false,api.addEmployeeToDepartment("e3", "d1"));
			assertEquals(false,api.addEmployeeToDepartment("e1", "d3"));
			assertEquals(false,api.addEmployeeToDepartment("", "d1"));
			assertEquals(false,api.addEmployeeToDepartment("e2", ""));
			assertEquals(false,api.addEmployeeToDepartment("e2", null));
			assertEquals(false,api.addEmployeeToDepartment(null, "d1"));
		}
		@Test
		void twoDepartments() {
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
			api.createEmployee( "e2",2000);
			api.createDepartmentlead( "d1",3000);
			api.createDepartmentlead( "d2",3000);
		}
	}

}
