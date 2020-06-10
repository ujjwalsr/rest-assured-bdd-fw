package stepDefinations;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import resources.APIResources;
import resources.Utils;

public class ZamboniStepDefination extends Utils {

	RequestSpecification request;
	Response response;
	JsonPath jp;

	@Given("^gp-fulfillment-zamboni has been set up in OpenShift$")
	public void gp_fulfillment_zamboni_has_been_set_up_in_OpenShift() throws FileNotFoundException {
		// Write code here that turns the phrase above into concrete actions
		request = given().spec(getZamboniRequestSpecification());
	}

	@When("user hits {string} api with get http request")
	public void user_hits_health_api_for_non_prod_environment(String resource) {
		// Write code here that turns the phrase above into concrete actions
		APIResources resAPI = APIResources.valueOf(resource);
		response = request.when().get(resAPI.getResource()).then().extract().response();
	}

	@Then("user gets the response with success status code {int}")
	public void user_gets_the_response_with_success_status_code(int code) {
		// Write code here that turns the phrase above into concrete actions
		assertEquals(code, response.getStatusCode());
	}

	@Then("status {string} in response body")
	public void status_UP_in_response_body(String status) {
		// Write code here that turns the phrase above into concrete actions
		jp = new JsonPath(response.asString());
		assertEquals(status, jp.getString("status"));
	}


	@Then("branch name is {string} and name {string} in the response body")
	public void branch_name_is_master_and_name_gp_fulfillment_zamboni(String branch, String name) {
		// Write code here that turns the phrase above into concrete actions
		jp = new JsonPath(response.asString());
		assertEquals(branch, jp.getString("git.branch"));
		assertEquals(name, jp.getString("build.name"));
	}

}
