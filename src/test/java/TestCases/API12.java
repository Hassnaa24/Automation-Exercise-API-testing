package TestCases;

import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import utilities.ResponseValidator;
import utilities.TestData;

import static io.restassured.RestAssured.given;
/**
 * API12 - Delete User Account
 *
 * Verifies that an existing user account can be successfully deleted
 * using valid email and password credentials.
 *
 * Endpoint: DELETE /api/deleteAccount
 * Request Parameters:
 * - email
 * - password
 *
 * Expected API Response Code: 200
 * Expected Message: "Account deleted!"
 *
 * Validations:
 * - HTTP response status is processed successfully.
 * - API responseCode is 200.
 * - API message confirms that the account was successfully deleted.
 */
public class API12 extends BaseTest {

    @Epic("User API")
    @Feature("User Account Deletion")
    @Story("Delete Existing User Account")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Verify that an existing user account can be successfully deleted using valid email and password credentials.")

    @Test
    //(dependsOnMethods ="API14_GET_user_account_detail_by_email")
    public void API12_DELETE_METHOD_To_Delete_User_Account() {

        // Display the email of the user account to be deleted
        Allure.step("Display user email used for account deletion", () -> {
            System.out.println(TestData.em);
        });

        // Send DELETE request using the user's email and password
        Response res = Allure.step(
                "Send delete user request for email: " + TestData.em,
                () ->
                        given()
                                .baseUri(BaseURL)
                                .formParam("email", TestData.em)
                                .formParam("password", TestData.pass)

                                .when()
                                .delete(UserDeleteEndPoint)

                                .then()
                                .extract()
                                .response()
        );

        // Display the API response for execution visibility and troubleshooting
        Allure.step("Display the Delete User API response", () -> {
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

        // Verify that the response message confirms successful account deletion
        Allure.step("Verify that the response message confirms the account was deleted", () -> {
            ResponseValidator.validateJsonField(
                    res,
                    "message",
                    "Account deleted!"
            );
        });
    }
}
