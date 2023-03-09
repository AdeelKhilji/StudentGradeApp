package ca.sheridan.khiljimo.beans;

import jakarta.persistence.Entity;
//import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.JoinTable;
//import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data //for database
@Entity // for database
@Builder
public class Student {
			
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
		
		private String name;
		
		private double grade;
		
		private String letterGrade;
		
		public void calculateLetterGrade() {
			if(grade >= 80) letterGrade = "A";
			else if(grade >=70) letterGrade = "B";
			else if(grade >=60) letterGrade = "C";
			else if(grade >=50) letterGrade = "D";
			else letterGrade = "F";
//			letterGrade = (grade >= 80) ? 'A' : (grade >= 70) ? 'B' : (grade >= 60) ? 'C' : (grade >= 50) ? 'D' : 'F';
		}

}
