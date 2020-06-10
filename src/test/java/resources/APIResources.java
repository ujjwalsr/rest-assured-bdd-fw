package resources;

//enum is a special class in java which has the collection of constants and methods
public enum APIResources {
	
	zamboniHealthAPI("/api/actuator/health"),
	zamboniInfoAPI("/api/actuator/info"),
	ftDetailsfromCasDb("/api/v1/request");
	private String resource;
	
	public String getResource() {
		return resource;
	}
	

	APIResources(String resource){	
		this.resource = resource;
	}
}
