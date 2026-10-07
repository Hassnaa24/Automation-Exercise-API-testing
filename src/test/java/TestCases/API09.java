package TestCases;

import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import utilities.ResponseValidator;

import static io.restassured.RestAssured.given;
/**
 * API09 - DELETE To Verify Login
 *
 * Verifies the API behavior when an unsupported DELETE method is used
 * on the Verify Login endpoint.
 *
 * Endpoint: DELETE /api/verifyLogin
 * Expected API Response Code: 405
 * Expected Message: "This request method is not supported."
 *
 * Validations:
 * - HTTP response status is processed successfully.
 * - API responseCode is 405.
 * - API message indicates that the requested method is not supported.
 */
public class API09 extends BaseTest {

    @Epic("Authentication API")
    @Feature("User Login - Unsupported Method")
    @Story("Verify DELETE Request Is Not Supported")
    @Severity(SeverityLevel.MINOR)
    @Description("Verify that sending a DELETE request to the Verify Login endpoint returns a 405 response code indicating that the requested method is not supported.")

    @Test
    public void API09_DELETE_To_Verify_Login() {

        // Send an unsupported DELETE request to the Verify Login endpoint
        Response res = Allure.step("Send DELETE request to Verify Login endpoint", () ->
                given()
                        .baseUri(BaseURL)

                        .when()
                        .delete(LoginEndPoint)

                        .then()
                        .extract()
                        .response()
        );

        // Display the API response for execution visibility and troubleshooting
        Allure.step("Display the Verify Login API response", () -> {
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

        // Verify that the response message confirms the DELETE method is not supported
        Allure.step("Verify that the response message indicates the DELETE method is not supported", () -> {
            ResponseValidator.validateJsonField(
                    res,
                    "message",
                    "This request method is not supported."
            );
        });
    }
}