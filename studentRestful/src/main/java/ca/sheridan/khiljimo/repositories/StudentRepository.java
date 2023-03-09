package ca.sheridan.khiljimo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ca.sheridan.khiljimo.beans.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

}
