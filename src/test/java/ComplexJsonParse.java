import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {

    public static void main(String[] args) {

        JsonPath js = new JsonPath(Files.coursePrice());

        //Print number of courses
        int count = js.getInt("courses.size()");
        System.out.println(count);

        //Print purchase amount
        int purchaseAmount = js.getInt("dashboard.purchaseAmount");
        System.out.println(purchaseAmount);

        //Print title of the first course
        String firstTitle = js.getString("courses.get(0).title");
        System.out.println(firstTitle);

        //Print all the courses and its' prices
        for (int i = 0; i < count; i++) {
            String title = js.getString("courses[" + i + "].title");
            int price = js.getInt("courses[" + i + "].price");
            System.out.println("Title = " + title + " it's price is = " + price);
        }

        //Print nr of copies sold by RPA course
        System.out.println("======Print nr of copies sold by RPA course=====");

        for (int i = 0; i < count; i++) {
            String title = js.getString("courses[" + i + "].title");
            if (title.equalsIgnoreCase("RPA")) {
                int copies = js.getInt("courses[" + i + "].copies");
                System.out.println(copies);
                break;
            }
        }
    }
}
