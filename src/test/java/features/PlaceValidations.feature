Feature: Validating Place API's
@AddPlace
Scenario Outline: Verify if Place is being successfully added using AddPlaceAPI
	Given Add Place Payload with "<name>" "<language>" "<address>"
	When User calls "AddPlaceAPI" with "Post" HTTP request
	Then the API call got success with Statuscode 200
	And "status" in response body is "OK"
	And "scope" in response body is "APP"
	And verify place_Id created maps to "<name>" using "GetPlaceAPI"
	
Examples:
	|name    |language |address            |
	|AAHouse |English  |World cross Center |
	|BBHouse |Spanish  |Sea cross Center   |
	
@DeletePlace
Scenario: Verify if  Delete Place functionality is working
	Given Delete Place Payload
	When User calls "DeletePlaceAPI" with "Post" HTTP request
	Then the API call got success with Statuscode 200
	And "status" in response body is "OK"
