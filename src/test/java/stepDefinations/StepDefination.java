package stepDefinations;

import static io.restassured.RestAssured.given;

import static org.junit.Assert.*;

import java.io.IOException;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

public class StepDefination extends Utils{

	RequestSpecification req;
	ResponseSpecification resspec;
	Response response;
	TestDataBuild testData=new TestDataBuild();
	static String place_id;
	
	 @Given("^Add Place Payload with \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
	    public void add_place_payload(String name, String language, String address)throws Throwable
	{
		req = given().spec(requestSpecification())
				.body(testData.addPlacePayload(name, language, address));
	}

	 @When("^User calls \"([^\"]*)\" with \"([^\"]*)\" HTTP request$")
	    public void user_calls_http_request(String strArg1, String strArg2){
		 
		APIResources resourceAPI= APIResources.valueOf(strArg1);
		System.out.println(resourceAPI.getResource());
		
	    resspec=new ResponseSpecBuilder().expectStatusCode(200)
				.expectContentType(ContentType.JSON).build();
	    System.out.println(strArg1);
		if(strArg2.equalsIgnoreCase("Post"))
			response=req.when().post(resourceAPI.getResource());
		else if(strArg2.equalsIgnoreCase("Get"))
			response=req.when().get(resourceAPI.getResource());
	 }

	@Then("^the API call got success with Statuscode 200$")
	public void the_api_call_got_success_with_statuscode_200() {
		assertEquals(response.getStatusCode(),200);
	}

	@And("^\"([^\"]*)\" in response body is \"([^\"]*)\"$")
	public void validate_response_body(String keyValue, String expectedvalue) {
		assertEquals(getJsonPath(response, keyValue),expectedvalue);
	}
	
	@And("^verify place_Id created maps to \"([^\"]*)\" using \"([^\"]*)\"$")
    public void verify_placeid_created_maps(String name, String strArg1) throws IOException {
	    place_id=getJsonPath(response, "place_id");
		req = given().spec(requestSpecification()).queryParam("place_id", place_id);
		user_calls_http_request(strArg1,"GET");
		String nameVal=getJsonPath(response, "name");
		assertEquals(nameVal,name);
    }
	
	 @Given("^Delete Place Payload$")
	    public void delete_place_payload() throws Throwable {
		 req=given().spec(requestSpecification()).body(testData.deletePlacePayload(place_id));
	    }
}
