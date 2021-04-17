package stepDefinations;

import io.cucumber.java.Before;

public class Hooks {

	@Before
	public void beforeScenario() throws Throwable
	{
		//write a code that will give you place id
		//execute this code only when the placeid is null
		StepDefination stepdef=new StepDefination();
		if(StepDefination.place_id==null) {
			stepdef.add_place_payload("Ashwini", "Kannada", "Udupi");
			stepdef.user_calls_http_request("AddPlaceAPI", "Post");
			stepdef.verify_placeid_created_maps("Ashwini", "GetPlaceAPI");
		}
		
	}
}
