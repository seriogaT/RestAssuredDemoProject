import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;

import java.sql.SQLOutput;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Basics {

    public static void main(String[] args) {
        // Validate Add Place API is working as expected
        //rest assured has given when then structure
        //given - all input details
        //when - submit API - resource, http method
        //them - validate the response


        RestAssured.baseURI = "https://rahulshettyacademy.com";
        // CREATE a place
        String response = given()
                .queryParam("key", "qaclick123")
                .header("Content-Type", "application/json")
                .body(Files.addPlace())
                .when()
                .post("maps/api/place/add/json")
                .then().assertThat().statusCode(200)
                .body("scope", equalTo("APP"))
                .extract()
                .response().asString();

        System.out.println("Response = " + response);
        JsonPath json = Util.rawToJson(response);
        String placeId = json.get("place_id");
        System.out.println("Place_id = " + placeId);

        //UPDATE a place

        String newAdrress = "Summer Walk, Africa";
        given().log().all()
                .queryParam("key", "qaclick123")
                .header("Content-Type", "application/json")
                .body(Files.updatePlace(placeId, newAdrress))
                .when().put("maps/api/place/update/json")
                .then().assertThat().statusCode(200)
                .body("msg", equalTo("Address successfully updated"));

        //GET a place

        String getPlaceResponse = given().log().all()
                .queryParam("key", "qaclick123")
                .queryParam("place_id", placeId)
                .when().get("maps/api/place/get/json")
                .then().assertThat().log().all().statusCode(200)
                .extract().response().asString();
        JsonPath js = Util.rawToJson(getPlaceResponse);
        String actualAddress = js.getString("address");
        System.out.println(actualAddress);
        Assert.assertEquals(newAdrress, actualAddress);

    }
}
