import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DynamicJson {

    @Test(dataProvider = "BookData", dataProviderClass = DataProviderUtil.class)
    public void addBook(String isbn, String aisle) {
        RestAssured.baseURI = "http://216.10.245.166";
        String response = given().log().all()
                .header("Content-Type", "application/json")
                .body(Files.addBook(isbn, aisle))
                .when()
                .post("/Library/Addbook.php")
                .then().assertThat().statusCode(200)
                .extract().response().asString();
        JsonPath js = Util.rawToJson(response);
        String id = js.get("ID");
        System.out.println(id);
    }

    @Test(dataProvider = "BookData", dataProviderClass = DataProviderUtil.class)
    public void deleteBook(String isbn, String aisle) {
        RestAssured.baseURI = "http://216.10.245.166";
        String response = given().log().all()
                .header("Content-Type", "application/json")
                .body(Files.addBook(isbn, aisle))
                .when()
                .delete("/Library/Addbook.php")
                .then().assertThat().statusCode(200)
                .extract().response().asString();
        JsonPath js = Util.rawToJson(response);
        String id = js.get("ID");
        System.out.println(id);
    }
}
