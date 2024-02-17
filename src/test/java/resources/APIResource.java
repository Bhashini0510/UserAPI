package resources;
//enum is special class in java which has collection of constants or  methods
public enum APIResource {
	
	PostAPI("/createusers"),
	GetAPIUSERS("/users"),

	;
	private String resource;
	
	APIResource(String resource)
	{
		this.resource=resource;
	}
	
	public String getResource()
	{
		return resource;
	}
	

}
