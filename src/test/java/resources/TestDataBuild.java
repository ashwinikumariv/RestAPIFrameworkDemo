package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.AddPlace;
import pojo.Location;

public class TestDataBuild {

	public AddPlace addPlacePayload(String name, String language, String address) {
		// Create Object for AddPlace POJO Class
		AddPlace addPlace = new AddPlace();
		addPlace.setAccuracy(50);
		addPlace.setName(name);
		addPlace.setPhone_number("(+91) 983 893 3937");
		addPlace.setAddress(address);
		addPlace.setWebsite("http://google.com");
		addPlace.setLanguage(language);
		List<String> types = new ArrayList<String>();
		types.add("shoe park");
		types.add("shop");
		addPlace.setTypes(types);
		Location location = new Location();
		location.setLat(-38.383494);
		location.setLng(33.427362);
		addPlace.setLocation(location);
		return addPlace;

	}
	
	public String deletePlacePayload(String placeid)
	{
		String body="{\r\n" + 
				"    \"place_id\":\""+placeid+"\"   	 	\r\n" + 
				"}\r\n" + 
				"";
		return body;
	}
}
