package stepDefinations;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;

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
import resources.TestDataBuild;
import resources.Utils;

public class AddPlaceStepDefination extends Utils{

	RequestSpecification request;
	ResponseSpecification responseSpec;
	Response response;
	TestDataBuild data = new TestDataBuild();

	@Given("Add Place Payload")
	public void add_Place_Payload() throws FileNotFoundException {
		// Write code here that turns the phrase above into concrete actions		
		request = given().spec(getRequestSpecification()).body(data.addPlacePayLoad());

	}

	@When("user calls {string} with post http request")
	public void user_calls_with_post_http_request(String string) {
		// Write code here that turns the phrase above into concrete actions
		response = request.when().post("maps/api/place/add/json").then().spec(responseSpec).extract()
				.response();
	}

	@Then("the API call got success with status code {int}")
	public void the_API_call_got_success_with_status_code(Integer int1) {
		// Write code here that turns the phrase above into concrete actions
		assertEquals(200, response.getStatusCode());
	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String key, String value) {
		// Write code here that turns the phrase above into concrete actions
		JsonPath jp = new JsonPath(response.asString());
		assertEquals(value, jp.getString(key));
		
	}

}
