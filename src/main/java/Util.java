import io.restassured.path.json.JsonPath;

import java.io.IOException;
import java.nio.file.Paths;
import java.nio.file.Files;

public class Util {

    public static JsonPath rawToJson(String response){
        return new JsonPath(response);
    }

    public static String generateStringFromResource(String path) throws IOException {
        return new String(Files.readAllBytes(Paths.get(path)));
    }
}
