package resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {

	public static RequestSpecification res;
	public RequestSpecification requestSpecification() throws IOException
	{
		if(res==null) {
		PrintStream log=new PrintStream(new File("logging.txt"));
	    res = new RequestSpecBuilder().setBaseUri(getGlobalvalues("baseURL"))
				.addQueryParam("key", "qaclick123")
				.addFilter(RequestLoggingFilter.logRequestTo(log))
				.addFilter(ResponseLoggingFilter.logResponseTo(log))
				.setContentType(ContentType.JSON).build();
	    return res;
		}
		return res;
	}
	
	public static String getGlobalvalues(String key) throws IOException
	{
		Properties prop=new Properties();
		FileInputStream file=new FileInputStream("D:\\Rest API Automation\\APIFramework\\src\\test\\java\\resources\\global.properties");
		prop.load(file);
		return prop.getProperty(key);
		
	}
	
	public String getJsonPath(Response response,String key)
	{
		String validres=response.asString();
		JsonPath js=new JsonPath(validres);
		String value=js.get(key).toString();
		return value;
	}
}
