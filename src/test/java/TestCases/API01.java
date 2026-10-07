package TestCases;

import io.restassured.response.Response;
import org.testng.annotations.Test;
import utilities.ResponseValidator;
import static io.restassured.RestAssured.given;
import io.qameta.allure.*;

/**
 * API01 - Get All Products List
 *
 * Verifies that the Products List API successfully retrieves
 * the complete list of available products.
 *
 * Endpoint: GET /api/productsList
 * Expected HTTP Status: 200
 * Expected API Response Code: 200
 *
 * Validations:
 * - HTTP response status code is 200.
 * - API responseCode field is 200.
 * - Products list is returned and is not empty.
 */
public class API01 extends BaseTest {

    @Epic("Products API")
    @Feature("Product Listing")
    @Story("Retrieve All Available Products")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify that the Products List API successfully returns all available products with a valid response.")
    @Test
    public void API01_Get_All_Products_List() {

        // Send GET request to retrieve the complete products list
        Response res = Allure.step("Send GET request to retrieve all products", () ->

                given()
                        .baseUri(BaseURL)

                        .when()
                        .get(ProductsEndPoint)

                        .then()
                        .extract()
                        .response()

        );

        // Display the API response for execution visibility and troubleshooting
        Allure.step("Display the products API response", () -> {
            System.out.println(res.asPrettyString());
        });

        // Validate the HTTP response status
        Allure.step("Verify that the HTTP response status is 200", () -> {
            ResponseValidator.validateStatus(res, 200);
        });

        // Validate the API response code returned in the JSON response
        Allure.step("Verify that the API response code is 200", () -> {
            ResponseValidator.validateJsonField(res, "responseCode", 200);
        });

        // Verify that the products list contains data
        Allure.step("Verify that the products list is not empty", () -> {
            ResponseValidator.validateListNotEmpty(res, "products");
        });

    }
}