package stepDefinations;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.UserDetails;
import pojo.UserAddress;
import resources.APIResource;
import org.apache.commons.io.FileUtils;

import resources.TestDataBuild;
import resources.Utils;

public class StepDefination extends Utils {
	RequestSpecification res;
	ResponseSpecification resspec;
	Response response;
	Response responsedata;
	TestDataBuild data =new TestDataBuild();
	static String user_id;
	static String user_name;
	
	

	@Given("Add User Details Payload with {string} {string} {long} {string} {string} {int} {string} {string} {string}")
public void add_Place_Payload_with(String firstname, String lastname, long contact, String mail, String country,int zip,String state, String plot, String street) throws IOException {
	    // Write code here that turns the phrase above into concrete actions
		
		 
		 res=given().log().all().spec(requestSpecification())
		.body(data.addPlacePayLoad(firstname,lastname,contact,mail,country,zip,state,plot,street));
	}

    @When("user calls {string} with {string} http request")
public void user_calls_with_http_request(String resource, String method) {
	    // Write code here that turns the phrase above into concrete actions
//constructor will be called with value of resource which you pass
		APIResource resourcesAPI=APIResource.valueOf(resource);
		System.out.println(resourcesAPI.getResource());
		
		
		resspec =new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		
		if(method.equalsIgnoreCase("POST"))
		 response =res.when().post(resourcesAPI.getResource());	
		if(method.equalsIgnoreCase("GET"))
			 response =res.when().get(resourcesAPI.getResource());	
		System.out.println(response);
			
		
}

	    @Then("the API call got success with status code {int}")
	public void the_API_call_got_success_with_status_code(Integer int1) {
	    // Write code here that turns the phrase above into concrete actions
	    	
	    	Assert.assertEquals(response.getStatusCode(),201);
	    }
		
		@When("user calls {string} with created userid")
		public void user_calls_get(String resource) throws IOException {
		
			
		   // requestSpec
			user_id = getJsonPath(response, "user_id");
		     user_name=getJsonPath(response,"user_first_name");
		     System.out.println(user_id);
		      responsedata =given().spec(requestSpecification()).log().all().
		    			when().get("/user/"+user_id).then().assertThat().statusCode(200)
		    			.extract().response();
		      
		  	System.out.println(responsedata);
		    			
			 //user_calls_with_http_request(resource,"GET");  
			 
		    
		}		

	    @Then("the APIcall got success with status code {int}")
	public void the_APIcall_got_success_with_status_code(Integer int1) {
	    // Write code here that turns the phrase above into concrete actions
	    	
		Assert.assertEquals(responsedata.getStatusCode(),200);
	    }
	    @When("user calls GetByNameAPI with created username")
	    public void user_calls_GetByNameAPI_with_created_userid() throws IOException{
	    	  responsedata =given().spec(requestSpecification()).log().all().
		    			when().get("/users/username/"+user_name).then().assertThat().statusCode(200)
		    			.extract().response();
	    		System.out.println(response);
	    }
	    @When("user calls PutAPI to update")
	    public void user_calls_PutAPI() throws IOException {
	    
	    	Map<String,Object> jsonASMAp = new HashMap<String,Object>();
	    	jsonASMAp.put("user_id", user_id);
	    	jsonASMAp.put("user_first_name", "John");
	    	jsonASMAp.put("user_last_name", "Paul");
	    	jsonASMAp.put("user_contact_number", 8523423324L);
	    	jsonASMAp.put("user_email_id", "rassar55@gmail.com");
	    	Map<String,Object> jsonASMAp2 = new HashMap<>();
	    	jsonASMAp2.put("plotNumber", "78-B");
	    	jsonASMAp2.put("street", "Alphine");
	    	jsonASMAp2.put("state", "Georgia");
	    	jsonASMAp2.put("country", "US");
	    	jsonASMAp2.put("zipCode", 458976);
	    	jsonASMAp.put("userAddress",jsonASMAp2);
	    	 responsedata=given().log().all().spec(requestSpecification())
	    				.body(jsonASMAp).when().put("/updateuser/"+user_id).then().assertThat().statusCode(200)
		    			.extract().response();
	    		System.out.println(responsedata);
	    	
	    }

	   @When("user calls GetByUsers with created username")
	   public void user_calls_GEtByUSers() throws IOException {
		   res=given().log().all().spec(requestSpecification());
		   user_calls_with_http_request("GetAPIUSERS","GET");
	   }
	   @When("user calls DeleteByID")
	   public void user_calls_DeleteByID() throws IOException{
		   responsedata=given().log().all().spec(requestSpecification()).when().delete("/deleteuser/"+user_id).then().assertThat().statusCode(200)
	    			.extract().response();
			System.out.println(responsedata);
	   }
	   @When("user calls DeleteByNAME")
	   public void user_calls_DeleteByNAME() throws IOException {
		   responsedata=given().log().all().spec(requestSpecification()).when().delete("/deleteuser/username/"+user_name).then().assertThat().statusCode(200)
	    			.extract().response();
			System.out.println(responsedata);
	   }
	   
	 
	   }


