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
import java.text.DecimalFormat;
import javax.swing.JOptionPane;

public class School {
	
	private static Course course;
	private static DecimalFormat df = new DecimalFormat(".##");

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int i=0;
		int id;
		String name;
		String assignment;
		double score;
		
		// 1- Instantiates the course object
		course=new Course(611,"JAVA");
		
		// 2- Prompt the user to input the student data, and calls addStudentGrade() to add the student to course, for three students
		while(i!=3) {
			while(i<3) {
				try {
				id=Integer.parseInt(JOptionPane.showInputDialog(null,"Enter the Student ID: "));
				}
				catch(Exception ex) {
					JOptionPane.showMessageDialog(null, "Please enter a valid student ID! Ex:12");
					break;
				}
				try {
					name=JOptionPane.showInputDialog(null,"Enter the Student Name: ");
					if (name.isEmpty()) {
						throw new Exception();
					}
					
				}
				catch(Exception ex) {
					JOptionPane.showMessageDialog(null, "Please enter a Student Name! Ex:Anand");
					break;
				}
				addStudentToCourse(id,name);
				i++;
			}
		}
		i=0;
		// 3- Prompt the user to input the grade data, and calls aaddStudentGradeToCourse() to add the grade to course, for three grades
		while(i!=3) {
			while(i<3) {
				
				try {
					id=Integer.parseInt(JOptionPane.showInputDialog(null,"Enter the Grade ID: "));
				}
				catch(Exception ex) {
					JOptionPane.showMessageDialog(null, "Please enter a valid Grade ID! Ex:34");
					break;
				}
				try {
					assignment=JOptionPane.showInputDialog(null,"Enter the assignment name: ");
					if(assignment.isEmpty()) {
						throw new Exception();
					}
				}
				catch(Exception ex) {
					JOptionPane.showMessageDialog(null, "Please enter an Assignment Name! Ex:Java");
					break;
				}
				try {
				score=Double.parseDouble(JOptionPane.showInputDialog(null, "Enter the score: "));
				}
				catch(Exception ex) {
					JOptionPane.showMessageDialog(null, "Please enter a valid Score! Ex:95");
					break;
				}
				
				addStudentGradeToCourse(id,assignment,course.getStudent(i),score);
				i++;
				
			}
		}
		
		// 4- Displays the course statistics by calling displayCourseStat()
		displayCourseStat();
		
	}
	
	// it uses the course object to add a new student to the course
	public static void addStudentToCourse(int id, String name){
		course.addStudent(id, name);
	}
	
	// it uses the course object to add a new grade object to the course
	public static void addStudentGradeToCourse(int id, String assignment, Student student, double score){
		course.addGrade(id,assignment,student,score);
	}
	
	// it should display the grade average and the highest student grade, student name 
	public static void displayCourseStat(){
		
		
		String message="\nCOURSE AND STUDENT DETAILS\n";
		for(int i=0;i<3;i++) {
			message+=course.getGrade(i).toString()+"\n";
		}
		
		message+="\nGRADE Average = "+df.format(course.getGradeAVG())+"\nHighest Grade scored by Student : "+course.getHighestStudentGrade();
		JOptionPane.showMessageDialog(null, message, "Course Statistics - "+course.getId()+" "+ course.getDescription(),JOptionPane.INFORMATION_MESSAGE);
		
	}

}
