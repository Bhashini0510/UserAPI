package resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.authentication.BasicAuthScheme;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {
	
	

	public static RequestSpecification req;
	public RequestSpecification requestSpecification() throws IOException
	{
		
		if(req==null)
		{
			
			BasicAuthScheme basicAuthScheme = new BasicAuthScheme();
			basicAuthScheme.setUserName("Numpy@gmail.com");
			basicAuthScheme.setPassword("tim123");
		PrintStream log =new PrintStream(new FileOutputStream("logging.txt"));
		 req=new RequestSpecBuilder().setAuth(basicAuthScheme).setBaseUri(getGlobalValue("baseUrl"))
				 .addFilter(RequestLoggingFilter.logRequestTo(log))
				 .addFilter(ResponseLoggingFilter.logResponseTo(log))
		.setContentType(ContentType.JSON).build();
		 return req;
		}
		return req;		
		
	}
	
	
	public static String getGlobalValue(String key) throws IOException
	{
		Properties prop =new Properties();
		FileInputStream fis =new FileInputStream("C:\\Manju\\Udemy_Framwork\\UserAPI\\src\\test\\java\\resources\\global.properties");
		prop.load(fis);
		return prop.getProperty(key);	
		
	}	
	
	public String getJsonPath(Response response,String key)
	{
		String resp = response.asString();
		JsonPath   js = new JsonPath(resp);
		return js.get(key).toString();
	}
	
	

}
