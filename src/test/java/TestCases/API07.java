package TestCases;

import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import utilities.ResponseValidator;

import static io.restassured.RestAssured.given;
/**
 * API07 - Verify Login With Valid Details
 *
 * Verifies that the Verify Login API successfully authenticates a user
 * when valid email and password credentials are provided.
 *
 * Endpoint: POST /api/verifyLogin
 * Request Parameters:
 * - email
 * - password
 *
 * Expected API Response Code: 200
 * Expected Message: "User exists!"
 *
 * Validations:
 * - HTTP response status is processed successfully.
 * - API responseCode is 200.
 * - API message confirms that the user exists.
 */
public class API07 extends BaseTest {

    @Epic("Authentication API")
    @Feature("User Login")
    @Story("Login With Valid Credentials")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Verify that the Verify Login API successfully authenticates a user with valid email and password credentials.")

    @Parameters({"User_email", "User_password"})
    @Test
    public void API07_POST_To_Verify_Login_with_valid_details(String User_email, String User_password) {

        // Send POST request using valid login credentials
        Response res = Allure.step("Send login request with valid credentials", () ->
                given()
                        .baseUri(BaseURL)
                        .header("Content-Type", ContentType)
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

        // Verify that the API response code is 200
        Allure.step("Verify that the API response code is 200", () -> {
            ResponseValidator.validateJsonField(res, "responseCode", 200);
        });

        // Verify that the response message confirms the user exists
        Allure.step("Verify that the response message confirms the user exists", () -> {
            ResponseValidator.validateJsonField(res, "message", "User exists!");
        });
    }
}