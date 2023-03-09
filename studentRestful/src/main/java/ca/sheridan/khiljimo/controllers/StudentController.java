package ca.sheridan.khiljimo.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.sheridan.khiljimo.beans.Student;
import ca.sheridan.khiljimo.repositories.StudentRepository;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/students")
public class StudentController 
{
	private StudentRepository studentRepo;
	
	//GET MAPPINGS
	
	@GetMapping(value={"/", ""})
	public List<Student> getStudentCollection()
	{
		return studentRepo.findAll();
	}
	
	@GetMapping("/{id}")
	public Student getStudentById(@PathVariable long id)
	{
		Optional<Student> student = studentRepo.findById(id);
		if(student.isPresent())
		{
			return studentRepo.findById(id).get();
		}
		else
		{
			return null;
		}
	}
	
	//POST MAPPINGS
	@PostMapping(value={"/",""},headers="Content-type=application/json")
	public String postStudent(@RequestBody Student student)
	{
		student.setId(null);
		student.calculateLetterGrade();
		Student s = studentRepo.save(student);
		return "Record was added at index " + s.getId();
	}
	
	@PutMapping(value={"/",""},headers="Content-type=application/json")
	public List<Student> updateStudent(@RequestBody List<Student> listOfStudents)
	{
		
		for(Student s: listOfStudents)
		{
			s.calculateLetterGrade();
			studentRepo.save(s);
		}
		return studentRepo.findAll();
	}
	
	
	//Update method
	@PutMapping(value={"/{id}"},headers="Content-type=application/json")
	public Student updateStudent(@PathVariable Long id, @RequestBody Student student)
	{
		student.calculateLetterGrade();
		student.setId(id);
		return studentRepo.save(student);
	}
	
	//Delete
	
	@DeleteMapping(value="/{id}")
	public String deleteStudent(@PathVariable Long id)
	{
		if(id != null && studentRepo.findById(id).isPresent()) 
		{
			studentRepo.deleteById(id);
			return "Student at index " + id + " was deleted";
		}
		else
		{
			return "Couldn't find the student of index: " + id;
		}
	}
}
