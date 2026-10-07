package TestCases;

import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import utilities.ResponseValidator;
import utilities.TestData;

import static io.restassured.RestAssured.given;

/**
 * API13 - Update User Account
 *
 * Verifies that an existing user account can be successfully updated
 * using valid user information.
 *
 * Endpoint: PUT /api/updateAccount
 * Request: Updated user account details
 *
 * Expected API Response Code: 200
 * Expected Message: "User updated!"
 *
 * Validations:
 * - HTTP response status is processed successfully.
 * - API responseCode is 200.
 * - API message confirms that the user account was successfully updated.
 */
public class API13 extends BaseTest {

    @Epic("User API")
    @Feature("User Account Update")
    @Story("Update Existing User Account")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Verify that an existing user account can be successfully updated using valid user information.")

    @Test
    //(dependsOnMethods ="API14_GET_user_account_detail_by_email")
    public void API13_PUT_METHOD_To_Update_User_Account() {

        // Display the email of the user account being updated
        Allure.step("Display user email used for account update", () -> {
            System.out.println(email);
        });

        // Send PUT request with updated user account details
        Response res = Allure.step(
                "Send update user request for email: " + TestData.em,
                () ->
                        given()
                                .baseUri(BaseURL)
                                .header("Content-Type", "application/x-www-form-urlencoded")

                                .formParam("name", "updated User")
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
                                .put(UserUpdateEndPoint)

                                .then()
                                .extract()
                                .response()
        );

        // Display the API response for execution visibility and troubleshooting
        Allure.step("Display the Update User API response", () -> {
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

        // Verify that the response message confirms successful account update
        Allure.step("Verify that the response message confirms the user was updated", () -> {
            ResponseValidator.validateJsonField(
                    res,
                    "message",
                    "User updated!"
            );
        });
    }
}