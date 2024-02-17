package stepDefinations;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.DataDriven;
import resources.TestDataBuild;
import resources.Utils;

public class PUTNegative_StepDefinition extends Utils {

	RequestSpecification res;
	Response resspec;
	Response response;
	Response responsedata;
	TestDataBuild data = new TestDataBuild();
	DataDriven d = new DataDriven();
	static String user_id;

	@Given("user calls POST request")
	public void user_calls_post_request() throws IOException {

		Map<String, Object> jsonASMAp = new HashMap<String, Object>();

		jsonASMAp.put("user_first_name", "John");
		jsonASMAp.put("user_last_name", "Paul");
		jsonASMAp.put("user_contact_number", 8523423324L);
		jsonASMAp.put("user_email_id", "rassar55@gmail.com");
		Map<String, Object> jsonASMAp2 = new HashMap<>();
		jsonASMAp2.put("plotNumber", "78-B");
		jsonASMAp2.put("street", "Alphine");
		jsonASMAp2.put("state", "Georgia");
		jsonASMAp2.put("country", "US");
		jsonASMAp2.put("zipCode", 458976);
		jsonASMAp.put("userAddress", jsonASMAp2);
		responsedata = given().log().all().spec(requestSpecification()).body(jsonASMAp).when().post("/createusers")
				.then().assertThat().statusCode(201).extract().response();
		System.out.println(responsedata);
		user_id = getJsonPath(responsedata, "user_id");

	}

	@Given("Update User Details Payload with {string}")
	public void update_user_details_Payload(String Scenario) throws IOException {

		ArrayList<String> a = d.getData(Scenario);
		String scenario = a.get(0);
		String firstname = a.get(1);
		String lastname = a.get(2);

		// long contact = Long.parseLong((a.get(3)));
		String contact = a.get(3);
		String mail = a.get(4);
		String plot = a.get(5);
		String street = a.get(6);
		String state = a.get(7);
		String country = a.get(8);
		int zip = Integer.parseInt(a.get(9));

		res = given().log().all().spec(requestSpecification())
				.body("{\r\n" + "\"user_id\":  \"" + user_id + "\"\r\n" + "\"user_first_name\":  \"" + firstname
						+ "\"\r\n" + " \"user_last_name\":  \"\"" + lastname + "\"\" " + "\"user_contact_number\": \""
						+ contact + "\" " + "\"user_email_id\": \"" + mail.toString() + "\"\r\n" + "\r\n"
						+ "\"userAddress\": {\r\n" + "   \"plotNumber\": \"" + plot + "\"\r\n" + "   \"street\": \""
						+ street + "\"\r\n" + "   \"state\": \"" + state + "\"\r\n" + "    \"country\": \"" + country
						+ "\"\r\n" + "   \"zipCode\": \"" + zip + "\"\r\n" + " }\r\n" + "}\r\n" + "\r\n" + "");
	}

	@When("user calls PUT API request")
	public void user_calls_put_Api_request() {

		resspec = res.when().put("/updateuser/" + user_id);

	}

	@Then("the API call got status")
	public void the_APi_call_Got_Success() {

		resspec.then().log().all().extract().response();
		int statusCode = resspec.getStatusCode();
		if (statusCode == 400) {
			Assert.assertEquals(statusCode, 400);
		} else
			Assert.assertEquals(statusCode, 409);

	}

	@Then("Delete the userid created")
	public void delete_the_useris_created() throws IOException {
		responsedata = given().log().all().spec(requestSpecification()).when().delete("/deleteuser/" + user_id).then()
				.assertThat().statusCode(200).extract().response();
		System.out.println(responsedata);
	}

}
