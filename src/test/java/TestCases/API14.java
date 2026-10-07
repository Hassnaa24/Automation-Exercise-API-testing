package TestCases;

import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import utilities.ResponseValidator;
import utilities.TestData;

import static io.restassured.RestAssured.given;
/**
 * API14 - Get User Account Details By Email
 *
 * Verifies that the User Details API successfully retrieves an existing
 * user's account information using the user's email address.
 *
 * Endpoint: GET /api/getUserDetailByEmail
 * Request Parameter: email
 *
 * Expected API Response Code: 200
 *
 * Validations:
 * - HTTP response status is processed successfully.
 * - API responseCode is 200.
 * - User object exists in the response.
 * - User name and email are returned correctly.
 * - Personal details are returned correctly.
 * - Address and location details are returned correctly.
 */
public class API14 extends BaseTest {

    @Epic("User API")
    @Feature("User Account Details")
    @Story("Retrieve User Details By Email")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify that the User Details API successfully retrieves the account details of an existing user using the user's email address.")

    @Test
    //(dependsOnMethods ="API11_POST_To_Create_Register_User_Account")
    public void API14_GET_user_account_detail_by_email() {

        // Display the email used to retrieve the user account details
        Allure.step("Display user email used to retrieve account details", () -> {
            System.out.println(TestData.em);
        });

        // Send GET request using the user's email address
        Response res = Allure.step(
                "Send GET user details request with email: " + TestData.em,
                () ->
                        given()
                                .baseUri(BaseURL)
                                .queryParam("email", TestData.em)

                                .when()
                                .get(UserDetailsEndPoint)

                                .then()
                                .extract()
                                .response()
        );

        // Display the API response for execution visibility and troubleshooting
        Allure.step("Display the User Details API response", () -> {
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

        // Verify that the user object exists in the response
        Allure.step("Verify that the user object exists in the response", () -> {
            ResponseValidator.validateFieldExists(res, "user");
        });

        // Validate basic user information
        Allure.step("Verify basic user information", () -> {
            ResponseValidator.validateJsonField(res, "user.name", "Test User");
            ResponseValidator.validateJsonField(res, "user.email", TestData.em);
            ResponseValidator.validateJsonField(res, "user.title", "Mr");
        });

        // Validate user's birth information
        Allure.step("Verify user birth information", () -> {
            ResponseValidator.validateJsonField(res, "user.birth_day", "10");
            ResponseValidator.validateJsonField(res, "user.birth_month", "5");
            ResponseValidator.validateJsonField(res, "user.birth_year", "1995");
        });

        // Validate user's name details
        Allure.step("Verify user name details", () -> {
            ResponseValidator.validateJsonField(res, "user.first_name", "Test");
            ResponseValidator.validateJsonField(res, "user.last_name", "User");
        });

        // Validate company information
        Allure.step("Verify user company information", () -> {
            ResponseValidator.validateJsonField(res, "user.company", "QA");
        });

        // Validate user's address details
        Allure.step("Verify user address details", () -> {
            ResponseValidator.validateJsonField(res, "user.address1", "Street 1");
            ResponseValidator.validateJsonField(res, "user.address2", "Apt 1");
        });

        // Validate user's location details
        Allure.step("Verify user location details", () -> {
            ResponseValidator.validateJsonField(res, "user.country", "Egypt");
            ResponseValidator.validateJsonField(res, "user.state", "Cairo");
            ResponseValidator.validateJsonField(res, "user.city", "Cairo");
            ResponseValidator.validateJsonField(res, "user.zipcode", "12345");
        });
    }
}