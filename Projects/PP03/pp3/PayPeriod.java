package PP03;

import java.util.Date;


public class PayPeriod {
	
	private int pID;
    private Date pStartDate, pEndDate;
    
    // 1- add the class constructor
	
    public PayPeriod(int pID, Date pStartDate, Date pEndDate) {
		super();
		this.pID = pID;
		this.pStartDate = pStartDate;
		this.pEndDate = pEndDate;
	}
   
    public PayPeriod() {
    	this(0,null,null);
    }
    
    // 2- add the setters/getters methods

	public int getpID() {
		return pID;
	}
	public void setpID(int pID) {
		this.pID = pID;
	}
	public Date getpStartDate() {
		return pStartDate;
	}
	public void setpStartDate(Date pStartDate) {
		this.pStartDate = pStartDate;
	}
	public Date getpEndDate() {
		return pEndDate;
	}
	public void setpEndDate(Date pEndDate) {
		this.pEndDate = pEndDate;
	}

    // 3- add override method toString() 
	
	@Override
	public String toString() {
		return "PayPeriod [pID=" + pID + ", pStartDate=" + pStartDate + ", pEndDate=" + pEndDate + "]";
	}
	
	
}
