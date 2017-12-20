
public class DataSet {

	
	private String name;
	private String informationURL;
	private String information;
	private String dataType;
	private String defaultTask;
	private String attributeTypes;
	private int numberOfInstances;
	private int numberOfAttributes;
	private int releaseYear;
	
	
	public DataSet(String name,String informationURL,String information,String dataType,String defaultTask,String attributeTypes,
			int numberOfInstances,int numberOfAttributes,int releaseYear){
		
		this.name = name;
		this.informationURL = informationURL;
		this.information = information;
		this.dataType = dataType;
		this.defaultTask = defaultTask;
		this.attributeTypes = attributeTypes;
		this.numberOfInstances = numberOfInstances;
		this.numberOfAttributes = numberOfAttributes;
		this.releaseYear = releaseYear;
	}


	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}


	public String getInformationURL() {
		return informationURL;
	}


	public void setInformationURL(String informationURL) {
		this.informationURL = informationURL;
	}


	public String getInformation() {
		return information;
	}


	public void setInformation(String information) {
		this.information = information;
	}


	public String getDataType() {
		return dataType;
	}


	public void setDataType(String dataType) {
		this.dataType = dataType;
	}


	public String getDefaultTask() {
		return defaultTask;
	}


	public void setDefaultTask(String defaultTask) {
		this.defaultTask = defaultTask;
	}


	public String getAttributeTypes() {
		return attributeTypes;
	}


	public void setAttributeTypes(String attributeTypes) {
		this.attributeTypes = attributeTypes;
	}


	public int getNumberOfInstances() {
		return numberOfInstances;
	}


	public void setNumberOfInstances(int numberOfInstances) {
		this.numberOfInstances = numberOfInstances;
	}


	public int getNumberOfAttributes() {
		return numberOfAttributes;
	}


	public void setNumberOfAttributes(int numberOfAttributes) {
		this.numberOfAttributes = numberOfAttributes;
	}


	public int getReleaseYear() {
		return releaseYear;
	}


	public void setReleaseYear(int releaseYear) {
		this.releaseYear = releaseYear;
	}
	
	
	@Override
	public String toString() {
		return "DataSet [name=" + name + ", informationURL=" + informationURL + ", information=" + information
				+ ", dataType=" + dataType + ", defaultTask=" + defaultTask + ", attributeTypes=" + attributeTypes
				+ ", numberOfInstances=" + numberOfInstances + ", numberOfAttributes=" + numberOfAttributes
				+ ", releaseYear=" + releaseYear + "]";
	}

	
	
	
}
