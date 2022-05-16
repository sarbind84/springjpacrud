package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entity.Employee;
import com.example.demo.entity.Student;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.StudentRepository;

@Controller
public class StudentController {
	
	@Autowired
	StudentRepository eRepo;
	
	
	@GetMapping({"/showStudent","/","list1"})
	public ModelAndView showEmployees()
	{
		
		
		ModelAndView mav = new ModelAndView("list-students");
		List<Student> emp = eRepo.findAll();
		
		mav.addObject("employees", emp);
		
		return mav;
		
	}
	
	
	//opens form and link handler hai i.e.,
	//opening page ko bhi btana hota hai what you are about to receive
	//th:object="${employee}"
	
	@GetMapping("/addStudentForm")
	public ModelAndView addEmployeeForm() {
		ModelAndView mav = new ModelAndView("add-student-form");
		Student newEmployee = new Student();
		mav.addObject("employee", newEmployee);
		return mav;
	}
	
	
	@PostMapping("/saveStudent")
	public String saveEmployee(@ModelAttribute Student employee) {
		//save operation also performs the operation of update
		//on the basis of id
		eRepo.save(employee);
		return "redirect:/list1";
	}
	
	
	@GetMapping("/showUpdateForm1")
	public ModelAndView showUpdateForm(@RequestParam Long employeeId) {
		
		ModelAndView mav = new ModelAndView("add-student-form");
		
		//get returns a value ..
		//find by id returns a optional
		
		Student emp = eRepo.findById(employeeId).get();
		mav.addObject("employee", emp);
		
		return mav;
	}
	
	
	@GetMapping("/deleteStudent")
	public String deleteEmployeeForm(@RequestParam Long employeeId) {
		
		ModelAndView mav = new ModelAndView("add-student-form");
		
		//get returns a value ..
		//find by id returns a optional
		
		 eRepo.deleteById((employeeId));
		 return "redirect:/list";
		 
	}
	
	

}
