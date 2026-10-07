package TestCases;

import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import utilities.ResponseValidator;

import static io.restassured.RestAssured.given;

/**
 * API04 - PUT To All Brands List
 *
 * Verifies the API behavior when an unsupported PUT method is used
 * on the Brands List endpoint.
 *
 * Endpoint: PUT /api/brandsList
 * Expected API Response Code: 405
 * Expected Message: "This request method is not supported."
 *
 * Validations:
 * - HTTP response status is processed successfully.
 * - API responseCode is 405.
 * - API message indicates that the requested method is not supported.
 */
public class API04 extends BaseTest {

    @Epic("Brands API")
    @Feature("Brands List - Unsupported Method")
    @Story("Verify PUT request is not supported for Brands List")
    @Severity(SeverityLevel.MINOR)
    @Description("Verify that sending a PUT request to the Brands List endpoint returns a 405 response code indicating that the requested method is not supported.")

    @Test
    public void API04_PUT_To_All_Brands_List() {

        // Send an unsupported PUT request to the Brands List endpoint
        Response res = Allure.step("Send PUT request to Brands List endpoint", () ->
                given()
                        .baseUri(BaseURL)

                        .when()
                        .put(BrandsEndPoint)

                        .then()
                        .extract()
                        .response()
        );

        // Display the API response for execution visibility and troubleshooting
        Allure.step("Display the Brands List API response", () -> {
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

        // Verify that the response message confirms the PUT method is not supported
        Allure.step("Verify that the response message indicates the PUT method is not supported", () -> {
            ResponseValidator.validateJsonField(
                    res,
                    "message",
                    "This request method is not supported."
            );
        });
    }
}
