import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class OAuthTest {
    @Test
    public void verifyOAuthTest() {

        //Get Access Token
//        given()
//                .queryParams("code","")
//                .queryParams("clientId","")




        String response = given()
                .queryParam("access_token", "")
                .when()
                .get("https://rahulshettyacademy.com/getCourse.php").asString();
        System.out.println("Response = " + response);
    }
}
