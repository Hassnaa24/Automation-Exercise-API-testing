package TestCases;

import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import utilities.ResponseValidator;
import utilities.TestData;

import static io.restassured.RestAssured.given;
/**
 * API11 - Create User Account
 *
 * Verifies that the Create Account API successfully creates a new user
 * account when valid user information is provided.
 *
 * Endpoint: POST /api/createAccount
 * Request: User registration details
 * Expected API Response Code: 201
 * Expected Message: "User created!"
 *
 * Validations:
 * - HTTP response status is processed successfully.
 * - API responseCode is 201.
 * - API message confirms that the user account was created successfully.
 */
public class API11 extends BaseTest {

    @Epic("User API")
    @Feature("User Account Creation")
    @Story("Create New User Account With Valid Data")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Verify that a new user account can be created successfully using valid registration data.")

    @Test
    public void API11_POST_To_Create_Register_User_Account()  {

        // Generate a unique email address to avoid duplicate account conflicts
        TestData.em = "test" + System.currentTimeMillis() + "@test.com";
        TestData.pass = "dfgesr8743728";

        // Display the generated email for execution visibility
        Allure.step("Display generated user email", () -> {
            System.out.println(TestData.em);
        });

        // Send POST request with valid user registration details
        Response res = Allure.step(
                "Send create user request with email: " + TestData.em,
                () ->
                        given()
                                .baseUri(BaseURL)
                                .header("Content-Type", "application/x-www-form-urlencoded")

                                .formParam("name", "Test User")
                                .formParam("email", TestData.em)
                                .formParam("password", TestData.pass)
                                .formParam("title", "Mr")
                                .formParam("birth_date", "10")
                                .formParam("birth_month", "5")
                                .formParam("birth_year", "1995")
                                .formParam("firstname", "Test")
                                .formParam("lastname", "User")
                                .formParam("company", "QA")
                                .formParam("address1", "Street 1")
                                .formParam("address2", "Apt 1")
                                .formParam("country", "Egypt")
                                .formParam("zipcode", "12345")
                                .formParam("state", "Cairo")
                                .formParam("city", "Cairo")
                                .formParam("mobile_number", "01000000000")

                                .when()
                                .post(UserCreateEndPoint)

                                .then()
                                .extract()
                                .response()
        );

        // Display the API response for execution visibility and troubleshooting
        Allure.step("Display the Create User API response", () -> {
            System.out.println(res.asPrettyString());
        });

        // Validate the HTTP response status
        Allure.step("Verify the HTTP response status", () -> {
            ResponseValidator.validateStatus(res, 200);
        });

        // Verify that the API returns response code 201
        Allure.step("Verify that the API response code is 201", () -> {
            ResponseValidator.validateJsonField(res, "responseCode", 201);
        });

        // Verify that the response message confirms successful user creation
        Allure.step("Verify that the response message confirms the user was created", () -> {
            ResponseValidator.validateJsonField(
                    res,
                    "message",
                    "User created!"
            );
        });
    }
}