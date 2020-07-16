import org.testng.annotations.DataProvider;

public class DataProviderUtil {

    @DataProvider(name = "BookData")
    public Object[][] getBookData() {
        return new Object[][]{

                {"123", "abc"},
                {"456", "def"},
                {"789", "ghi"}

        };
    }

}
