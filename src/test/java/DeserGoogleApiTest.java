import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import model.Location;
import model.LocationDetails;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class DeserGoogleApiTest {

    @Test
    public void verifyGoogleApiTest() {

        RequestSpecBuilder specBuilder = new RequestSpecBuilder();
        RequestSpecification reqSpecBuild = specBuilder.setBaseUri("https://rahulshettyacademy.com")
                .addQueryParam("key", "qaclick123")
                .setContentType(ContentType.JSON)
                .build();

        LocationDetails locationDetails = new LocationDetails();

        locationDetails.setAccuracy(50);
        locationDetails.setName("Frontline house");
        locationDetails.setPhoneNumber("(+91) 983 893 3937");
        locationDetails.setAddress("29, side layout, cohen 09");
        locationDetails.setWebsite("https://rahulshettyacademy.com");

        List<String> myList = new ArrayList<>();
        myList.add("shoe park");
        myList.add("shoe");
        locationDetails.setTypes(myList);

        Location loc = new Location();
        loc.setLatitude(-38.383494);
        loc.setLongitude(33.427362);

        locationDetails.setLocation(loc);


        ResponseSpecification respSpec = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType(ContentType.JSON)
                .build();

        RequestSpecification reqSpec = given()
                .spec(reqSpecBuild)
                .body(locationDetails);

        Response response = reqSpec.when().post("/maps/api/place/add/json")
                .then().spec(respSpec).extract().response();

        String responseString = response.asString();
        System.out.println("Result = " + responseString);

    }
}
