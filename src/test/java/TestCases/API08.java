package TestCases;

import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import utilities.ResponseValidator;

import static io.restassured.RestAssured.given;
/**
 * API08 - Verify Login Without Email Parameter
 *
 * Verifies that the Verify Login API returns an appropriate error
 * when the required email parameter is not provided.
 *
 * Endpoint: POST /api/verifyLogin
 * Request Parameter: email - Missing
 *
 * Expected API Response Code: 400
 * Expected Message:
 * "Bad request, email or password parameter is missing in POST request."
 *
 * Validations:
 * - HTTP response status is processed successfully.
 * - API responseCode is 400.
 * - API message confirms that the required email or password parameter is missing.
 */
public class API08 extends BaseTest {

    @Epic("Authentication API")
    @Feature("User Login - Negative Scenarios")
    @Story("Login Without Email Parameter")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify that the Verify Login API returns a 400 error when the required email parameter is missing.")

    @Test
    public void API08_POST_To_Verify_Login_without_email_parameter() {

        // Send POST request without providing the required email parameter
        Response res = Allure.step("Send login request without the email parameter", () ->
                given()
                        .baseUri(BaseURL)

                        .when()
                        .post(LoginEndPoint)

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

        // Verify that the API returns response code 400
        Allure.step("Verify that the API response code is 400 Bad Request", () -> {
            ResponseValidator.validateJsonField(res, "responseCode", 400);
        });

        // Verify that the response message indicates a required parameter is missing
        Allure.step("Verify that the response message indicates the email or password parameter is missing", () -> {
            ResponseValidator.validateJsonField(
                    res,
                    "message",
                    "Bad request, email or password parameter is missing in POST request."
            );
        });
    }
}