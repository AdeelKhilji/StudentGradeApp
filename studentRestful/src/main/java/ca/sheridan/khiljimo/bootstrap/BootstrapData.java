package ca.sheridan.khiljimo.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import ca.sheridan.khiljimo.beans.Student;
import ca.sheridan.khiljimo.repositories.StudentRepository;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class BootstrapData implements CommandLineRunner {

	private StudentRepository studentRepo;
	@Override
	public void run(String... args) throws Exception {
		String[] names = {"jon","kim","tod","ted","Ann"};
		for(String n: names) {
			Student s = Student.builder().name(n).grade((int)(Math.random()*50+50)).build();
			s.calculateLetterGrade();
			studentRepo.save(s);
		}

	}

}
