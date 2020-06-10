Feature: Validating the fulfillment request API

@createFT
Scenario: Validate that user should be able to trigger fulfillment request via API
 Given Fulfillment request Payload
 When user calls zambonifulfillmentAPI with post http request
 Then API call got success with status code 200
 And user gets Success message in response body

@getFtDetails 
Scenario: Validate that fulfillment request details is inserted in cassandra db
	Given get fulfillment details payload
	When user calls getFTDetailsAPI with get http request
	Then API call got success with status code 200
	And "referenceId" and source system "GPS" get extracted in response body