package TestCases;

import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import utilities.ResponseValidator;

import static io.restassured.RestAssured.given;
/**
 * API02 - POST To All Products List
 *
 * Verifies the API behavior when an unsupported POST method is used
 * on the Products List endpoint.
 *
 * Endpoint: POST /api/productsList
 * Expected API Response Code: 405
 * Expected Message: "This request method is not supported."
 *
 * Validations:
 * - HTTP response is processed successfully.
 * - API responseCode is 405.
 * - API message indicates that the requested method is not supported.
 */
public class API02 extends BaseTest {

    @Epic("Products API")
    @Feature("Products List - Unsupported Method")
    @Story("Verify POST request is not supported for Products List")
    @Severity(SeverityLevel.MINOR)
    @Description("Verify that sending a POST request to the Products List endpoint returns a 405 response code indicating that the requested method is not supported.")
    @Test
    public void API02_POST_To_All_Products_List() {

        // Send an unsupported POST request to the Products List endpoint
        Response res = Allure.step("Send POST request to Products List endpoint", () ->
                given()
                        .baseUri(BaseURL)

                        .when()
                        .post(ProductsEndPoint)

                        .then()
                        .extract()
                        .response()
        );

        // Display the API response for execution visibility and troubleshooting
        Allure.step("Display the Products List API response", () -> {
            System.out.println(res.asPrettyString());
        });

        // Validate the HTTP response status
        Allure.step("Verify the HTTP response status", () -> {
            ResponseValidator.validateStatus(res, 200);
        });

        // Verify that the API returns response code 405
        Allure.step("Verify that the API response code is 405 Method Not Allowed", () -> {
            ResponseValidator.validateJsonField(res, "responseCode", 405);
        });

        // Verify that the response message confirms the POST method is not supported
        Allure.step("Verify that the response message indicates the POST method is not supported", () -> {
            ResponseValidator.validateJsonField(
                    res,
                    "message",
                    "This request method is not supported."
            );
        });
    }

}