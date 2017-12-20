package PP03;

// 1- The Employee class extends superclass Person
public class Employee extends Person{
	
	private int eID;
    private Status empStatus;
    private static int noOfEmployees;
    
 // 2- add the subclass Employee constructor that calls the supper Person class constructor, you should provide input data for all parent class data fields	     
   public Employee (int eID, String fName, String lName, Status empStatus, Address address) {
	   super();
	   this.fName = fName;
	   this.lName = lName;
	   this.address = address;
	   this.eID = eID;
	   this.empStatus = empStatus;  
	   noOfEmployees++;
   }
   
   public Employee() {
	   this(0,null,null,null,null);
   }
   
   // 3- add setters/getters methods
   public int geteID() {
	   return eID;
   }
   public void seteID(int eID) {
	   this.eID = eID;
   }
   public Status getEmpStatus() {
	   return empStatus;
   }
   public void setEmpStatus(Status empStatus) {
	   this.empStatus = empStatus;
   }
   
   public String getfName() {
	   return fName;
   }

   public void setfName(String fName) {
	   this.fName = fName;
   }

   public String getlName() {
	   return lName;
   }

   public void setlName(String lName) {
	   this.lName = lName;
   }

   public Address getAddress() {
	   return address;
   }
   
   public void setAdsress(Address address) {
	   this.address = address;
   }
   
   
   public static int getNoOfEmployees() {
	   return noOfEmployees;
   }

   public static void setNoOfEmployees(int noOfEmployees) {
	   Employee.noOfEmployees = noOfEmployees;
   }

   @Override
   public String toString() {
	   return "Employee [eID=" + eID + ", empStatus=" + empStatus + "]";
   }


}
