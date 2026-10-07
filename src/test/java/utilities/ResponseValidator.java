package utilities;
import io.restassured.response.Response;
import org.testng.Assert;

public class ResponseValidator {

    //  Validate HTTP status code
    public static void validateStatus(Response response, int expectedStatus) {
        Assert.assertEquals(response.statusCode(), expectedStatus,
                " Status code mismatch");
    }

    //  Validate JSON field (int)
    public static void validateJsonField(Response response, String key, int expectedValue) {
        int actual = response.jsonPath().getInt(key);
        Assert.assertEquals(actual, expectedValue,
                " Mismatch in field: " + key);
    }

    //  Validate JSON field (String)
    public static void validateJsonField(Response response, String key, String expectedValue) {
        String actual = response.jsonPath().getString(key);
        Assert.assertEquals(actual, expectedValue,
                " Mismatch in field: " + key);
    }

    //  Validate field exists
    public static void validateFieldExists(Response response, String key) {
        Object value = response.jsonPath().get(key);
        Assert.assertNotNull(value, " Field not found: " + key);
    }

    //  Validate list not empty
    public static void validateListNotEmpty(Response response, String key) {
        int size = response.jsonPath().getList(key).size();
        Assert.assertTrue(size > 0, " List is empty: " + key);
    }

    //  Validate nested field not null
    public static void validateNestedFieldNotNull(Response response, String path) {
        Object value = response.jsonPath().get(path);
        Assert.assertNotNull(value, " Field is null: " + path);
    }

    //  Validate nested field
    public static void validateNestedField(Response response, String path, String expectedValue) {
        String actual = response.jsonPath().getString(path);
        Assert.assertEquals(actual, expectedValue,
                " Mismatch in nested field: " + path);
    }
}
