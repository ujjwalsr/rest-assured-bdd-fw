package resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
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

	public static RequestSpecification req;
	public PrintStream log;
	public FileInputStream fis;
	public static Properties globalProperties = null;
	public JsonPath jpath;

	public RequestSpecification getRequestSpecification() throws FileNotFoundException {
		log = new PrintStream(new FileOutputStream("logging.txt"));
		req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addQueryParam("key", "qaclick123")
				.addFilter(RequestLoggingFilter.logRequestTo(log)).addFilter(ResponseLoggingFilter.logResponseTo(log))
				.setContentType(ContentType.JSON).build();
		return req;
	}

	public RequestSpecification getFtApiRequestSpecification() throws FileNotFoundException {
		loadBrowserConfigProperties();
		log = new PrintStream(new FileOutputStream("logging.txt"));
		req = new RequestSpecBuilder().setBaseUri(getglobalProperty("baseUri"))
				.addFilter(RequestLoggingFilter.logRequestTo(log)).addFilter(ResponseLoggingFilter.logResponseTo(log))
				.setContentType(ContentType.JSON).build();
		return req;
	}
	
	public RequestSpecification getFtDetailsRequestSpecification() throws FileNotFoundException {
		loadBrowserConfigProperties();
		log = new PrintStream(new FileOutputStream("logging.txt"));
		req = new RequestSpecBuilder().setBaseUri(getglobalProperty("getFtIDURI"))
				.addFilter(RequestLoggingFilter.logRequestTo(log)).addFilter(ResponseLoggingFilter.logResponseTo(log))
				.setContentType(ContentType.JSON).build();
		return req;
	}

	public RequestSpecification getZamboniRequestSpecification() throws FileNotFoundException {
		loadBrowserConfigProperties();
		if (req == null) {
			log = new PrintStream(new FileOutputStream("logging.txt"));
			req = new RequestSpecBuilder().setBaseUri(getglobalProperty("baseUri"))
					.addFilter(RequestLoggingFilter.logRequestTo(log))
					.addFilter(ResponseLoggingFilter.logResponseTo(log)).setContentType(ContentType.JSON).build();
		}
		return req;
	}

	public void loadBrowserConfigProperties() {
		try {
			fis = new FileInputStream(
					"C:\\Users\\usrivas1\\GPS_Repos\\fbox_automation\\APIFramework\\src\\test\\java\\resources\\global.properties");
			globalProperties = new Properties();
			globalProperties.load(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getglobalProperty(String key) {
		return globalProperties.getProperty(key);
	}
	
	public String getJsonPathValue(Response res, String key) {
		if(jpath == null) {
		jpath = new JsonPath(res.asString());
		}
		return jpath.get(key).toString();
	}

}
