package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entity.Employee;
import com.example.demo.repository.EmployeeRepository;

@Controller
public class EmployeeController {

	@Autowired
	EmployeeRepository eRepo;
	
	
	@GetMapping({"/showEmployee","/","list"})
	public ModelAndView showEmployees()
	{
		
		
		ModelAndView mav = new ModelAndView("list-employees");
		List<Employee> emp = eRepo.findAll();
		
		mav.addObject("employees", emp);
		
		return mav;
		
	}
	
	
	//opens form and link handler hai i.e.,
	//opening page ko bhi btana hota hai what you are about to receive
	//th:object="${employee}"
	
	@GetMapping("/addEmployeeForm")
	public ModelAndView addEmployeeForm() {
		ModelAndView mav = new ModelAndView("add-employee-form");
		Employee newEmployee = new Employee();
		mav.addObject("employee", newEmployee);
		return mav;
	}
	
	
	@PostMapping("/saveEmployee")
	public String saveEmployee(@ModelAttribute Employee employee) {
		//save operation also performs the operation of update
		//on the basis of id
		eRepo.save(employee);
		return "redirect:/list";
	}
	
	
	@GetMapping("/showUpdateForm")
	public ModelAndView showUpdateForm(@RequestParam Long employeeId) {
		
		ModelAndView mav = new ModelAndView("add-employee-form");
		
		//get returns a value ..
		//find by id returns a optional
		
		Employee emp = eRepo.findById(employeeId).get();
		mav.addObject("employee", emp);
		
		return mav;
	}
	
	
	@GetMapping("/deleteEmployee")
	public String deleteEmployeeForm(@RequestParam Long employeeId) {
		
		ModelAndView mav = new ModelAndView("add-employee-form");
		
		//get returns a value ..
		//find by id returns a optional
		
		 eRepo.deleteById((employeeId));
		 return "redirect:/list";
		 
	}
	
	
	
}
