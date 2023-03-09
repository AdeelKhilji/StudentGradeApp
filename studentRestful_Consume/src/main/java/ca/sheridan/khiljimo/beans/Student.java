package ca.sheridan.khiljimo.beans;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data //for database
@Builder
public class Student 
{		
		private Long id;
		private String name;
		private double grade;
		private String letterGrade;		
}
