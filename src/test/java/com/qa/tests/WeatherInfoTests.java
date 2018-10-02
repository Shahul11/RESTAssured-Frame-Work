package com.qa.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.util.TestBase;
import com.qa.util.TestUtil;

import io.restassured.RestAssured;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class WeatherInfoTests extends TestBase {
	
	@BeforeMethod()
	public void setUp()
	{
		TestBase.init();
	}

	@DataProvider
	public Object[][] getData() {
		Object testData[][]=TestUtil.getDataFromSheet(TestUtil.sheetName);
		return testData;
	}
	

	@Test(dataProvider="getData")
	public void getWeatherDetails(String weather_state_name, String wind_direction_compass, String humidity) {

		// 1. define the base url
		RestAssured.baseURI = prop.getProperty("serviceurl");

		// 2. define the http request:
		RequestSpecification httpRequest = RestAssured.given();


		// 3. make a request/execute the request:
		Response response = httpRequest.request(Method.GET, "2487956");

		// 4. get the Response body
		String responseBody = response.getBody().asString();
		System.out.println("Response body is:" + responseBody);

		Assert.assertEquals(responseBody.contains("location_type"), true);

		// 5. get the status code and validate it
		int statusCode = response.getStatusCode();
		System.out.println("Status code is: " + statusCode);

		// 6. Assertions
		Assert.assertEquals(statusCode, 200);
		System.out.println("The status line is:" + response.statusLine());

		// 7. Getting headers
		Headers headers = response.getHeaders();
		System.out.println(headers);

		// To Get specific headers
		String content = response.getHeader("Content-Type");
		System.out.println("The content type is: " + content);

		// you can use jason path to get the values
		JsonPath jasonPathVaule = response.jsonPath();
		int repo = jasonPathVaule.get("public_repos");
		System.out.println(repo);
	}

}
