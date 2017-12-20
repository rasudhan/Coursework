

public class Course {
	
	private int id;
	private String description;
	private Student[] students;
	private Grade[] grades;
	
	 private int numberOfStudents;
	 private int numberOfGrades;
	
	// complete course object constructor
	public Course (int id, String description){
		
		this.id=id;
		this.description=description;
		students=new Student[3];
		grades=new Grade[3];
		numberOfStudents=0;
		numberOfGrades=0;
		// you should initialize the students and grades arrays here
		// these array should be of size 3 
	}

	// Creates and adds a student object to the  students array
	public void addStudent(int id, String name){
		students[numberOfStudents].setId(id);
		students[numberOfStudents].setName(name);
		
		numberOfStudents++;
	}

	// adds a grade object to the  grades array
   public void addGrade(int id, String assignment, Student student, double score){
		
	    grades[numberOfGrades].setId(id);
		grades[numberOfGrades].setStudent(student);
		grades[numberOfGrades].setScore(score);
		grades[numberOfGrades].setAssignment(assignment);
		
		numberOfGrades++;
	}
   
   
   
   public double getGradeAVG(){
	   
	   return 0;
		
	}
   
   
  public String getHiegestStudentGrade(){
	   
	   return "";
		
	}
   
  public Student getStudent() {
		return students[numberOfGrades];
	}
   
}
