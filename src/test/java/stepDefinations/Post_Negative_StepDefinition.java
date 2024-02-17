package stepDefinations;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.util.ArrayList;

import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.DataDriven;
import resources.TestDataBuild;
import resources.Utils;

public class Post_Negative_StepDefinition extends Utils {
	DataDriven d = new DataDriven();
	RequestSpecification res;
	ResponseSpecification resspec;
	Response response;
	Response responsedata;
	static String user_id;
	TestDataBuild data = new TestDataBuild();

	@Given("Add User Details Payload with {string}")
	public void Add_user_Details_Payload(String Scenario) throws IOException {
		ArrayList<String> a = d.getData(Scenario);
		String scenario = a.get(0);
		String firstname = a.get(1);
		String lastname = a.get(2);
		long contact = Long.parseLong(a.get(3));
		String mail = a.get(4);
		String plot = a.get(5);
		String street = a.get(6);
		String state = a.get(7);
		String country = a.get(8);
		int zip = Integer.parseInt(a.get(9));
		res = given().log().all().spec(requestSpecification())
				.body(data.addPlacePayLoad(firstname, lastname, contact, mail, country, zip, state, plot, street));

	}

	@When("user calls POST API request")
	public void user_calls_POST_API() {
		response = res.when().post("/createusers");
	}

	@Then("the API call got status code")
	public void the_API_call_got_status_code() {
		responsedata = response.then().log().all().extract().response();
		int statusCode = responsedata.getStatusCode();
		if (statusCode == 400) {
			Assert.assertEquals(statusCode, 400);
		} else if (statusCode == 409) {
			String message = getJsonPath(responsedata, "message");
			System.out.println(message);
		} else
			System.out.println(statusCode);

	}

}
