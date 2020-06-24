package resources;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {
	public static RequestSpecification request;

	public RequestSpecification requestSpecification() throws IOException {
		if (request == null) {
			PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
			request = new RequestSpecBuilder().setBaseUri(getGlobalValue("baseURI"))
					.addFilter(RequestLoggingFilter.logRequestTo(log))
					.addFilter(ResponseLoggingFilter.logResponseTo(log)).addQueryParam("key", "qaclick123")
					.setContentType(ContentType.JSON).build();
			return request;
		}
		return request;
	}

	// To call the base URL from properties file
	public static String getGlobalValue(String key) throws IOException {
		Properties properties = new Properties();
		FileInputStream fis = new FileInputStream(
				"C:\\Users\\User\\eclipse-workspace\\APIFramework\\src\\test\\java\\resources\\global.properties");
		properties.load(fis);
		return properties.getProperty(key);
	}

	// To call the JsonPath anywhere
	public String getJsonPath(Response response, String key) {
		String resp = response.asString();
		JsonPath js = new JsonPath(resp);
		return js.get(key).toString();
	}
}
