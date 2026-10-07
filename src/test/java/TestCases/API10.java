package TestCases;

import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import utilities.ResponseValidator;

import static io.restassured.RestAssured.given;
/**
 * API10 - Verify Login With Invalid Details
 *
 * Verifies that the Verify Login API returns an appropriate error
 * when invalid email and password credentials are provided.
 *
 * Endpoint: POST /api/verifyLogin
 * Request Parameters:
 * - email
 * - password
 *
 * Expected API Response Code: 404
 * Expected Message: "User not found!"
 *
 * Validations:
 * - HTTP response status is processed successfully.
 * - API responseCode is 404.
 * - API message confirms that the user was not found.
 */
public class API10 extends BaseTest {

    @Epic("Authentication API")
    @Feature("User Login - Negative Scenarios")
    @Story("Login With Invalid Credentials")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify that the Verify Login API returns a 404 error when invalid email and password credentials are provided.")

    @Parameters({"User_email", "User_password"})
    @Test
    public void API10_POST_To_Verify_Login_with_invalid_details(String User_email, String User_password) {

        // Send POST request using invalid login credentials
        Response res = Allure.step("Send login request with invalid credentials", () ->
                given()
                        .baseUri(BaseURL)
                        .formParam("email", User_email)
                        .formParam("password", User_password)

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

        // Verify that the API returns response code 404
        Allure.step("Verify that the API response code is 404", () -> {
            ResponseValidator.validateJsonField(res, "responseCode", 404);
        });

        // Verify that the response message confirms the user was not found
        Allure.step("Verify that the response message indicates the user was not found", () -> {
            ResponseValidator.validateJsonField(
                    res,
                    "message",
                    "User not found!"
            );
        });
    }
}