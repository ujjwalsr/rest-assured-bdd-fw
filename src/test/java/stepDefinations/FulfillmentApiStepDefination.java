package stepDefinations;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

public class FulfillmentApiStepDefination extends Utils{
	RequestSpecification request;
	ResponseSpecification responseSpec;
	Response response;
	TestDataBuild data = new TestDataBuild();
	
	@Given("Fulfillment request Payload")
	public void fulfillment_request_Payload() throws FileNotFoundException {
	    // Write code here that turns the phrase above into concrete actions
		request = given().spec(getFtApiRequestSpecification()).body(data.fulfillmentRequestPayload());
	    
	}

	@When("user calls zambonifulfillmentAPI with post http request")
	public void user_calls_zambonifulfillmentAPI_with_post_http_request() {
	    // Write code here that turns the phrase above into concrete actions
		response = request.when().post("api/pushjasontokafka").then().extract()
				.response();
	}
	
	@Then("API call got success with status code {int}")
	public void the_API_call_got_success_with_status_code(Integer int1) {
		// Write code here that turns the phrase above into concrete actions
		assertEquals(200, response.getStatusCode());
	}

	@Then("user gets Success message in response body")
	public void user_gets_Success_message_in_response_body() {
	    // Write code here that turns the phrase above into concrete actions
	    assertEquals("Success", response.getBody().asString());
	}
	
	@Given("get fulfillment details payload")
	public void get_fulfillment_details_payload() throws FileNotFoundException {
	    // Write code here that turns the phrase above into concrete actions
		request = given().spec(getFtDetailsRequestSpecification()).queryParam("id", data.fulfillmentRequestPayload().getKey().getId()).queryParam("source", "GPS");
	}

	@When("user calls getFTDetailsAPI with get http request")
	public void user_calls_getFTDetailsAPI_with_get_http_request() {
	    // Write code here that turns the phrase above into concrete actions
	    response = request.get(APIResources.ftDetailsfromCasDb.getResource());
	}

	@Then("{string} and source system {string} get extracted in response body")
	public void reference_id_and_source_system_get_extracted_in_response_body(String key1, String key2) {
	    // Write code here that turns the phrase above into concrete actions
	    assertEquals(data.fulfillmentRequestPayload().getKey().getId(), getJsonPathValue(response, key1));
	    assertEquals(key2, getJsonPathValue(response, "sourceSystem"));
	}


}
