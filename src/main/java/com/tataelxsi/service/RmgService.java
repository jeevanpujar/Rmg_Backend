package com.tataelxsi.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import org.springframework.web.multipart.MultipartFile;

import com.tataelxsi.entity.Customer;
import com.tataelxsi.entity.Employee;
import com.tataelxsi.entity.EmployeeBillingStatus;
import com.tataelxsi.entity.EmployeeProject;
import com.tataelxsi.entity.Project;
import com.tataelxsi.entity.logIn;
import com.tataelxsi.repository.CustomerRepository;
import com.tataelxsi.repository.EmployeeBillingStatusRepository;
import com.tataelxsi.repository.EmployeeProjectRepository;
import com.tataelxsi.repository.EmployeeRepository;
import com.tataelxsi.repository.ProjectRepository;
import com.tataelxsi.repository.loginRepository;

@Service
public class RmgService {

	@Autowired
	CustomerRepository customerRepo;
	@Autowired
	loginRepository logRepo;
	@Autowired
	EmployeeRepository employeeRepo;
	@Autowired
	EmployeeBillingStatusRepository empBillStatusRepo;
	@Autowired
	EmployeeProjectRepository empProjectRepo;
	@Autowired
	ProjectRepository projectRepo;
	
	public ResponseEntity<Object> importExcel(MultipartFile files) throws IOException {

		XSSFWorkbook workbook = new XSSFWorkbook(files.getInputStream());

		
		XSSFSheet worksheet = workbook.getSheetAt(0);
		Iterator<Row> rows = worksheet.iterator();

		Map<String, Integer> map = new HashMap<String, Integer>();
		int rowNumber = 0;
		while (rows.hasNext()) {
			Row currentRow = rows.next();
			if (rowNumber == 0) {

				short minCollx = currentRow.getFirstCellNum();
				short maxCollx = currentRow.getLastCellNum();
				for (short collx = minCollx; collx < maxCollx; collx++) {
					org.apache.poi.ss.usermodel.Cell cell = currentRow.getCell(collx);
					map.put(cell.getStringCellValue().trim(), cell.getColumnIndex());
				}
				rowNumber++;
			} else {

				// check to avoid duplicate entry forCustomer
				Customer cust = this.customerRepo
						.findByCustomerName(currentRow.getCell(map.get("Customer")).getStringCellValue());
				if (cust == null) {

					cust = new Customer();
					cust.setCustomerName(currentRow.getCell(map.get("Customer")).getStringCellValue());
					cust.setDeliveryManagerName(currentRow.getCell(map.get("DM Name")).getStringCellValue());
					customerRepo.save(cust);

				}

				// Employee
				Employee emp = this.employeeRepo.findByEmployeeNumber(
						(int) currentRow.getCell(map.get("Employee Number")).getNumericCellValue());
				if (emp == null) {

					emp = new Employee();
					emp.setEmployeeNumber((int) currentRow.getCell(map.get("Employee Number")).getNumericCellValue());
					emp.setEmployeeName(currentRow.getCell(map.get("Employee Name")).getStringCellValue());
					emp.setEmailAddress(currentRow.getCell(map.get("E-mail Address")).getStringCellValue());

					emp.setEmployeePhoneNumber(currentRow.getCell(map.get("Employee Present Phone Number 1"))
							.getCellType() == org.apache.poi.ss.usermodel.CellType.STRING
									? currentRow.getCell(map.get("Employee Present Phone Number 1")).getStringCellValue().trim() : 
										 currentRow.getCell(map.get("Employee Present Phone Number 1")).getNumericCellValue() != 0
											? String.valueOf(currentRow.getCell(map.get("Employee Present Phone Number 1")).getNumericCellValue()): "");
		
					emp.setDateOfBirth(currentRow.getCell(map.get("Date of Birth")).getDateCellValue());
					emp.setCampusBatch(currentRow.getCell(map.get("Campus batch")).getStringCellValue());
					emp.setTotalExperience((double) currentRow.getCell(map.get("Total Experience(Yrs)")).getNumericCellValue());
					emp.setAllocationBand(currentRow.getCell(map.get("Allocation Band")).getStringCellValue());
					emp.setSkills(currentRow.getCell(map.get("Skills")).getStringCellValue());
					emp.setCustomer(cust);
					employeeRepo.save(emp);

					// Billing Status
					EmployeeBillingStatus ebs = new EmployeeBillingStatus();
					ebs.setBillabilityStatus(currentRow.getCell(map.get("Billability Status")).getStringCellValue());
					ebs.setReservedforAccount(currentRow.getCell(map.get("Reserved for Account")).getStringCellValue());
					ebs.setPlanningAllocatedBilled(
							currentRow.getCell(map.get("Planning/Allocated/Billed")).getStringCellValue());
					ebs.setEmployee(emp);
					empBillStatusRepo.save(ebs);

				}

				// Project

				Project p = this.projectRepo
						.findByProjectName(currentRow.getCell(map.get("Project Name")).getStringCellValue());
				if (p == null) {
					p = new Project();
					p.setProjectName(currentRow.getCell(map.get("Project Name")).getStringCellValue());
					projectRepo.save(p);

				}

				// EmployeeProject
				EmployeeProject ep = new EmployeeProject();
				ep.setEmployee(emp);
				ep.setProject(p);

				empProjectRepo.save(ep);

			}
		}
		workbook.close();
		return new ResponseEntity<Object>(HttpStatus.CREATED);
	}
	


	private Sort.Direction getSortDirection(String direction) {
		if (direction.equals("asc")) {
			return Sort.Direction.ASC;
		} else if (direction.equals("desc")) {
			return Sort.Direction.DESC;
		}

		return Sort.Direction.ASC;
	}


	// Employee list

	public ResponseEntity<Map<String, Object>> getAllEmployee(String search,int page,int size,String[] sort) {

		try {
			List<Order> orders = new ArrayList<Order>();

			if (sort[0].contains(",")) {
				// will sort more than 2 fields
				// sortOrder="field, direction"
				for (String sortOrder : sort) {
					String[] sortA = sortOrder.split(",");
					orders.add(new Order(getSortDirection(sortA[1]), sortA[0]));
				}
			} else {
				// sort=[field, direction]
				orders.add(new Order(getSortDirection(sort[1]), sort[0]));
			}
			Pageable paging = PageRequest.of(page, size, Sort.by(orders));

			Page<Employee> pageTuts;
			if ( search == null)
				pageTuts = employeeRepo.findAll(paging);
			else
				pageTuts = employeeRepo.findByEmployeeContaining(search , paging);
			List<Employee> employees = pageTuts.getContent();
			

			Map<String, Object> response = new HashMap<>();
			response.put("employee", employees);
			response.put("currentPage", pageTuts.getNumber());
			response.put("totalItems", pageTuts.getTotalElements());
			response.put("totalPages", pageTuts.getTotalPages());

			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	

	public ResponseEntity<Map<String, Object>> getAllCustomer(String search, int page, int size,  String[] sort){

		try {
			List<Order> orders = new ArrayList<Order>();

			if (sort[0].contains(",")) {
				// will sort more than 2 fields
				// sortOrder="field, direction"
				for (String sortOrder : sort) {
					String[] sortA = sortOrder.split(",");
					orders.add(new Order(getSortDirection(sortA[1]), sortA[0]));
				}
			} else {
				// sort=[field, direction]
				orders.add(new Order(getSortDirection(sort[1]), sort[0]));
			}
		
        
			Pageable paging = PageRequest.of(page, size, Sort.by(orders));

			Page<Customer> pageTuts;
			if (search == null) {
			pageTuts = customerRepo.findAll(paging);
			}
			else {
				pageTuts = customerRepo.findByCustomerContaining(search , paging);
			}
			List<Customer> cust =pageTuts.getContent();
			

			Map<String, Object> response = new HashMap<>();
			response.put("customer", cust);
			response.put("currentPage", pageTuts.getNumber());
			response.put("totalItems", pageTuts.getTotalElements());
			response.put("totalPages", pageTuts.getTotalPages());

			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}



	public ResponseEntity<Map<String, Object>> getAllEmployeeBillingStatus(String search, int page, int size, String[] sort

	) {

		try {
			List<Order> orders = new ArrayList<Order>();

			if (sort[0].contains(",")) {
				// will sort more than 2 fields
				// sortOrder="field, direction"
				for (String sortOrder : sort) {
					String[] sortA = sortOrder.split(",");
					orders.add(new Order(getSortDirection(sortA[1]), sortA[0]));
				}
			} else {
				// sort=[field, direction]
				orders.add(new Order(getSortDirection(sort[1]), sort[0]));
			}
			
			Pageable paging = PageRequest.of(page, size, Sort.by(orders));

			Page<EmployeeBillingStatus> pageTuts;
			if (search == null) {
			pageTuts = empBillStatusRepo.findAll(paging);
			}else {
				pageTuts = empBillStatusRepo.findByEmployeeBillStatusContaining(search , paging);
			}
			List<EmployeeBillingStatus> empbill = pageTuts.getContent();
			 

			Map<String, Object> response = new HashMap<>();
			response.put("empbill", empbill);
			response.put("currentPage", pageTuts.getNumber());
			response.put("totalItems", pageTuts.getTotalElements());
			response.put("totalPages", pageTuts.getTotalPages());

			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>( HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}




	public ResponseEntity<Map<String, Object>> getAllEmployeeProject(String search,int page,int size,  String[] sort) {

		try {
			List<Order> orders = new ArrayList<Order>();

			if (sort[0].contains(",")) {
				// will sort more than 2 fields
				// sortOrder="field, direction"
				for (String sortOrder : sort) {
					String[] sortA = sortOrder.split(",");
					orders.add(new Order(getSortDirection(sortA[1]), sortA[0]));
				}
			} else {
				// sort=[field, direction]
				orders.add(new Order(getSortDirection(sort[1]), sort[0]));
			}
			
			Pageable paging = PageRequest.of(page, size, Sort.by(orders));

			Page<EmployeeProject> pageTuts;
			if (search == null) {
				pageTuts = empProjectRepo.findAll(paging);
			}else {
				pageTuts = empProjectRepo.findByEmployeeProjectsContaining(search , paging);
			}
			

			List<EmployeeProject> empProject =  pageTuts.getContent();

			Map<String, Object> response = new HashMap<>();
			response.put("employeeproject", empProject);
			response.put("currentPage", pageTuts.getNumber());
			response.put("totalItems", pageTuts.getTotalElements());
			response.put("totalPages", pageTuts.getTotalPages());

			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


	
	public ResponseEntity<Map<String, Object>> getAllProject(String search,int page,int size,String[] sort) {

		try {
			List<Order> orders = new ArrayList<Order>();

			if (sort[0].contains(",")) {
				// will sort more than 2 fields
				// sortOrder="field, direction"
				for (String sortOrder : sort) {
					String[] sortA = sortOrder.split(",");
					orders.add(new Order(getSortDirection(sortA[1]), sortA[0]));
				}
			} else {
				// sort=[field, direction]
				orders.add(new Order(getSortDirection(sort[1]), sort[0]));
			}
			
			Pageable paging = PageRequest.of(page, size, Sort.by(orders));

			Page<Project> pageTuts;
			if (search == null) {
			pageTuts = projectRepo.findAll(paging);
			}else {
				pageTuts = projectRepo.findByProjectsContaining(search , paging);
			}
			List<Project> project  = pageTuts.getContent();

			Map<String, Object> response = new HashMap<>();
			response.put("project", project);
			response.put("currentPage", pageTuts.getNumber());
			response.put("totalItems", pageTuts.getTotalElements());
			response.put("totalPages", pageTuts.getTotalPages());

			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	public logIn findByUserNameAndPassword(String userName, String password) throws Exception {
        // TODO Auto-generated method stub
    return logRepo.findByUserNameAndPassword(userName, password);
    }
	 public logIn createLogin(logIn login) {
	        logIn logins=null;
	        try {
	            logins=logRepo.save(login);
	        } catch (Exception e) {
	            e.printStackTrace();}
	        return logins;
	        }

}
