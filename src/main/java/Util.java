import io.restassured.path.json.JsonPath;

public class Util {

    public static JsonPath rawToJson(String response){
        return new JsonPath(response);
    }
}
