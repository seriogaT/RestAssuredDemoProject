import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;

public class StaticJson {
    public static final String ADD_BOOK_EXTERNALLY = "C:\\Users\\stovarnitchi\\OneDrive - ENDAVA\\Desktop\\Training\\AddBookExternally.json";

    @Test
    public void addBookExternally() throws IOException {
        RestAssured.baseURI = "http://216.10.245.166";
        String response = given().log().all()
                .header("Content-Type", "application/json")
                .body(Util.generateStringFromResource(ADD_BOOK_EXTERNALLY))
                .when()
                .post("/Library/Addbook.php")
                .then().assertThat().statusCode(200)
                .extract().response().asString();
        JsonPath js = Util.rawToJson(response);
        String id = js.get("ID");
        System.out.println(id);
    }
}
