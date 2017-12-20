
//add class template

public class ElectricBill extends Bill{


	final static double BASE_RESIDENTIAL_CUST = 6.75;
	final static double BASE_COMMERCIAL_CUST = 10.75;
	
	public ElectricBill() {
		super();
	}

	@Override
	protected double getSum() {
		
		return 0.0;

	}

	@Override
	protected double getAVG() {
		
		return 0.0;
		
	}

	@Override
	protected void computeBill(Customer customer) {
		// TODO Auto-generated method stub

	}

	protected  boolean addCustomer(int custID, String fName, String lName, int status,int noOfKWH, int month, double billAmount) {
		// TODO Auto-generated method stub
		
		return false;
		
	}

	
	@Override
	protected boolean writeToFile(String fileName) {
		// TODO Auto-generated method stub
		
		return false;
		
	}

	// override toString method
	@Override
	public String toString() {
		
		return null;

	}


private double residentialElecBill(int kwh){
	
	return 0.0;
}

private double commericialElecBill(int kwh){
	
	return 0.0;
	
}
}


