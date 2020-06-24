Feature: Validating Place API's

@AddPlace @Regression
Scenario Outline: Verify if place is being successfully added using AddPlaceAPI
	Given Add place payload with "<name>" "<language>" "<address>"
	When User calls "AddPlaceAPI" with "POST" http request
	Then the API call got success with status code 200
	And "status" in response body is "OK"
	And "scope" in response body is "APP"
	And verify place_id created maps to "<name>" using "getPlaceAPI"

Examples: 
	|name		|language	|address			|
	|AAhouse	|English	|World cross center	|
#	|BBhouse	|Spanish	|Sea cross center	|
#	|CChouse	|French		|River cross center	|

@DeletePlace @Regression
Scenario: Verify if delete place functionality is working
	Given DeletePlace payload
	When User calls "deletePlaceAPI" with "POST" http request
	Then the API call got success with status code 200
	And "status" in response body is "OK"