package com.tataelxsi;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import com.tataelxsi.controller.RmgController;
import com.tataelxsi.entity.Customer;
import com.tataelxsi.entity.Employee;
import com.tataelxsi.entity.EmployeeBillingStatus;
import com.tataelxsi.entity.EmployeeProject;
import com.tataelxsi.entity.Project;
import com.tataelxsi.repository.CustomerRepository;
import com.tataelxsi.repository.EmployeeBillingStatusRepository;
import com.tataelxsi.repository.EmployeeProjectRepository;
import com.tataelxsi.repository.EmployeeRepository;
import com.tataelxsi.repository.ProjectRepository;


@SpringBootTest
class RmgReportApplicationTests {
//
//	@Mock
//	private CustomerRepository custRepo;
//	@Mock
//	private EmployeeRepository empRepo;
//	@Mock
//	private ProjectRepository projectRepo;
//	@Mock
//	private EmployeeBillingStatusRepository empBillRepo;
//	@Mock
//	private EmployeeProjectRepository EmpProRepo;
//
//	@InjectMocks
//	private RmgController rmgController;	
//	@Test
//	void getAllCustpmerTest() {
//		List<Customer> list = new ArrayList<Customer>();
//		Customer cust = new Customer(1, "jeevan", "pujar");
//		Customer cust1 = new Customer(2, "pramod", "pujar");
//		Customer cust2 = new Customer(3, "prakash", "pujar");
//		list.add(cust);
//		list.add(cust1);
//		list.add(cust2);
//		when(custRepo.findAll()).thenReturn(list);
//		when(custRepo.findByCustomerName("jeevan")).thenReturn(new Customer(1, "jeevan", "pujar"));
//	}
//
//	@Test
//	void getAllEmployeeTest() {
//		List<Employee> empList = new ArrayList<Employee>();
//		Employee emp= new Employee(1, "jeevan", "pujar@gmail.com","9696969696","2021",25.5,"abc","Java");
//		Employee emp1 = new Employee(2, "pramod", "pujar@gmail.com","1234567898","2022",20.2,"abd","Php");
//		empList.add(emp);
//		empList.add(emp1);
//		when(empRepo.findAll()).thenReturn(empList);
//		when(empRepo.findByEmployeeNumber(1)).thenReturn(new Employee(1, "jeevan", "pujar@gmail.com","9696969696","2021",25.5,"abc","Java"));
//	}
//	@Test
//	void getAllProject() {
//		List<Project> project=new ArrayList<Project>();
//		Project p=new Project(1,"JavaProject");
//		Project p2=new Project(1,"AngularProject");
//		project.add(p);
//		project.add(p2);
//		when(projectRepo.findAll()).thenReturn(project);
//		when(projectRepo.findByProjectName("JavaProject")).thenReturn(new Project(1,"JavaProject"));
//	}
//	
//	@Test
//	void getEmployeeBilling() {
//		List<EmployeeBillingStatus> empBill=new ArrayList<EmployeeBillingStatus>();
//		EmployeeBillingStatus bill=new EmployeeBillingStatus(1,"Billable","Competency Buffer","allocated",(new Employee(1, "jeevan", "pujar@gmail.com","9696969696","2021",25.5,"abc","Java")));
//		EmployeeBillingStatus bill2=new EmployeeBillingStatus(2,"Billable","Competency Buffer","allocated",(new Employee(1, "jeevan", "pujar@gmail.com","9696969696","2021",25.5,"abc","Java")));
//		empBill.add(bill);
//		empBill.add(bill2);
//		when(empBillRepo.findAll()).thenReturn(empBill);
//		
//	}
//	@Test
//	void getEmployeeProject() {
//		List<EmployeeProject> empProject=new ArrayList<EmployeeProject>();
//		EmployeeProject ep=new EmployeeProject(1,(new Employee(1, "jeevan", "pujar@gmail.com","9696969696","2021",25.5,"abc","Java")),(new Project(1,"JavaProject")));
//		EmployeeProject ep2=new EmployeeProject(2,(new Employee(1, "jeevan", "pujar@gmail.com","9696969696","2021",25.5,"abc","Java")),(new Project(1,"JavaProject")));
//		empProject.add(ep);
//		empProject.add(ep2);
//		when(EmpProRepo.findAll()).thenReturn(empProject);
//	}
//		
}
	
	



