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

	
	
	public Course() {
		this(0,null);
		// TODO Auto-generated constructor stub
	}



	// Creates and adds a student object to the  students array
	public void addStudent(int id, String name){
		
		students[numberOfStudents]=new Student();
		students[numberOfStudents].setId(id);
		students[numberOfStudents].setName(name);
		
		numberOfStudents++;
	}

	// adds a grade object to the  grades array
   public void addGrade(int id, String assignment, Student student, double score){
		
	    grades[numberOfGrades]=new Grade();
	    grades[numberOfGrades].setId(id);
		grades[numberOfGrades].setStudent(student);
		grades[numberOfGrades].setScore(score);
		grades[numberOfGrades].setAssignment(assignment);
		
		numberOfGrades++;
	}
   
   
   
   public double getGradeAVG(){
	   
	   double average,sum=0;
	   for(int i=0;i<numberOfGrades;i++) {
		   sum+=grades[i].getScore();
	   }
	   average=sum/numberOfGrades;
	   return average;
		
	}
   
   
    public String getHighestStudentGrade(){
	   
	  int i=1,h=0;
	  double highScore=grades[0].getScore();
	  while(i<numberOfGrades) {
		  if(grades[i].getScore()>highScore) {
			  highScore=grades[i].getScore();
			  h=i;
		  }
		  i++;
	  }
	  return students[h].getName()+" ("+String.valueOf(highScore)+")";
		
	}
   
    public Student getStudent(int i) {
		return students[i];
	}
    
    public Grade getGrade(int i) {
    	return grades[i];
    }



	public int getId() {
		return id;
	}


	public String getDescription() {
		return description;
	}

    
   
}
