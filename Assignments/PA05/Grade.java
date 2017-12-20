//********************************************************************
//* 																 *
//* 				CIS611 Spring 2017 Anand RameshKannan			 *
//* 																 *
//* 					Program Assignment PA05 					 *
//* 																 *
//* 				Program to display Course Statistics
//*					using Classes and Objects
//* 																 *
//* 					Date Created: 02.28.2017 					 *
//*					Saved in: AnandRameshKannanPA0501.java		 	 *
//* 																 *
//********************************************************************
package PA05;


public class Grade {
	private int id;
	private String assignment;
	private Student student;
	private double score;
	
	// complete grade object constructor
	public Grade (int id, String assignment, Student student, double score){
		this.id=id;
		this.assignment=assignment;
		this.student=student;
		this.score=score;
	}
	
	

	
	public Grade() {
		this(0,null,null,0);
		// TODO Auto-generated constructor stub
	}




	// compete public getter and setter methods
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAssignment() {
		return assignment;
	}

	public void setAssignment(String assignment) {
		this.assignment = assignment;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}




	@Override
	public String toString() {
		return "Grade [id=" + id + ", assignment=" + assignment + ", student=" + student.toString() + ", score=" + score + "]";
	}
	
	
	
	
	
}
