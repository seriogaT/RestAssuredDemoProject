import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SumValidation {

    @Test
    public void SumOfCourses() {
        int sum = 0;
        JsonPath js = new JsonPath(Files.coursePrice());
        int count = js.getInt("courses.size()");
        for (int i = 0; i < count; i++) {
            int price = js.getInt("courses[" + i + "].price");
            int copies = js.getInt("courses[" + i + "].copies");
            int amount = price * copies;
            sum += amount;
            System.out.println("Amount = " + amount);
        }
        System.out.println("Sum = " + sum);
        int purchaseAmount = js.getInt("dashboard.purchaseAmount");
        Assert.assertEquals(purchaseAmount,789);
    }
}
