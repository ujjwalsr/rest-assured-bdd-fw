Feature: Validate that gp-fulfillment-zamboni service is running on stage, prod and non-prod environment

@healthcheck
Scenario: Validate that gp-fulfillment-zamboni-helth service is running on environment
	Given gp-fulfillment-zamboni has been set up in OpenShift
	When user hits "zamboniHealthAPI" api with get http request
	Then user gets the response with success status code 200
	And status "UP" in response body

@infocheck	
Scenario: Validate that gp-fulfillment-zamboni-info service is running on environment
	Given gp-fulfillment-zamboni has been set up in OpenShift
	When user hits "zamboniInfoAPI" api with get http request
	Then user gets the response with success status code 200
	And branch name is "master" and name "gp-fulfillment-zamboni" in the response body