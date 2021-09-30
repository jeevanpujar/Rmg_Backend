package com.tataelxsi.controller;

import java.io.IOException;

import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.tataelxsi.entity.logIn;
import com.tataelxsi.service.RmgService;

@RestController
@RequestMapping("/excel")
@CrossOrigin
public class RmgController {
	@Autowired
	RmgService rmgService;
	
	
	@PostMapping("/parseData")
	public Object importExcel(@RequestParam("file") MultipartFile files) throws IOException {

		return rmgService.importExcel(files);
	}

	
	@GetMapping("/employee")
	public ResponseEntity<Map<String, Object>> getAllTutorials(@RequestParam(required = false)String search,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size,
			@RequestParam(defaultValue = "employeeNumber,asc") String[] sort) {
	   
	                ResponseEntity<Map<String, Object>> response = rmgService.getAllEmployee(search, page, size, sort);
	                return  response;
	            }


	@GetMapping("/customer")
	public ResponseEntity<Map<String, Object>> getAllCustomer(@RequestParam(required = false)String search,
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "5") int size, 
			@RequestParam(defaultValue = "customerId,asc") String[] sort){
		  ResponseEntity<Map<String, Object>> response = rmgService.getAllCustomer(search, page, size, sort);
          return  response;
      }

	@GetMapping("/empbill")
	public ResponseEntity<Map<String, Object>> getAllEmployeeBillingStatus(@RequestParam(required = false)String search,
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "5") int size,
			@RequestParam(defaultValue = "billabilityStatusId,asc") String[] sort

	) {

		  ResponseEntity<Map<String, Object>> response = rmgService.getAllEmployeeBillingStatus(search, page, size, sort);
          return  response;
      }

	@GetMapping("/employeeproject")
	public ResponseEntity<Map<String, Object>> getAllEmployeeProject(@RequestParam(required = false)String search,
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "5") int size, 
			@RequestParam(defaultValue = "empProjectId,asc") String[] sort) {

		  ResponseEntity<Map<String, Object>> response = rmgService.getAllEmployeeProject(search, page, size, sort);
          return  response;
      }


	@GetMapping("/project")
	public ResponseEntity<Map<String, Object>> getAllProject(@RequestParam(required = false)String search,
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "5") int size,
			@RequestParam(defaultValue = "projectId,asc") String[] sort) {

		  ResponseEntity<Map<String, Object>> response = rmgService.getAllProject(search, page, size, sort);
          return  response;
      }
	@GetMapping("/userLogin/{userName}/{password}")    
	public logIn loginPage(@PathVariable String userName,@PathVariable  String password) throws Exception{  
		logIn login=new logIn();
		
//		userName=login.getUserName();         
//		password=login.getPassword();         
	//	logIn log=null;         
		if(userName!=null&&password!=null) {                     
			login=rmgService.findByUserNameAndPassword(userName,password);         
			}         
		if(login==null) {             
			throw new Exception("Bad credentials");         
			}         
		return login;         
		}
	@PostMapping("/registration")
    public logIn saveLogin(@RequestBody logIn login) {
        logIn logins=rmgService.createLogin(login);
        return logins;
	}
}
