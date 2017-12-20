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

public class Student {
	
	private int id;
	private String name;

	// complete student object constructor
	public Student (int id, String name){
		this.id=id;
		this.name=name;
	}
	
	
	// compete public getter and setter methods

	public Student() {
		this(0,null);
		// TODO Auto-generated constructor stub
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + "]";
	}
	
	
	
}
