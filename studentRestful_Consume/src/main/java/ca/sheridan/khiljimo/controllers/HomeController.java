package ca.sheridan.khiljimo.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import ca.sheridan.khiljimo.beans.Student;

@Controller
public class HomeController 
{
	//Rest Controller
//	private String url_root ="http://localhost:8080";
	private String url_root_student ="http://localhost:8080/students";
	private String url_getById_student ="http://localhost:8080/students/";
	
	//Student Repository stuRepo;
	
	@GetMapping("/")
	public String getHomePage(RestTemplate restTemplate, Model model)
	{
		//stuRepo.findAll()
		ResponseEntity<Student[]> responseEntity = restTemplate.getForEntity(url_root_student, Student[].class);
		
		model.addAttribute("students", responseEntity.getBody());
		model.addAttribute("student", new Student());
		
		return "home.html";
	}
	
	@GetMapping("/process")
	public String loadAddPage(@ModelAttribute Student student, RestTemplate restTemplate, Model model)
	{	//stuRepo.save(student);
		restTemplate.postForLocation(url_root_student, student);
		return "redirect:/";
	}
	
	@GetMapping("/edit/{id}")
	public String loadEditStudent(@PathVariable Long id, Model model,RestTemplate restTemplate)
	{
		ResponseEntity<Student> responseEntity = restTemplate.getForEntity(url_getById_student + id, Student.class);
		Student s = responseEntity.getBody();
		model.addAttribute("student", s);
		return "edit.html";
		
	}
	
	@GetMapping("/edit")
	public String editStudent(@ModelAttribute Student student, Model model,RestTemplate restTemplate)
	{
		restTemplate.put(url_getById_student + student.getId(), student);
		
		return "redirect:/";
		
	}
	
	@GetMapping("/delete/{id}")
	public String deleteStudent(@PathVariable Long id, Model model,RestTemplate restTemplate)
	{
		restTemplate.delete(url_getById_student + id);
		
		return "redirect:/";
		
	}
	
}
