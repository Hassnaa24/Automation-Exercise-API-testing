package TestCases;

import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ResponseValidator;
import java.util.UUID;

import static io.restassured.RestAssured.given;

/**
 * Registration
 *
 * Contains the end-to-end user account lifecycle API tests:
 * - Generate dynamic registration data
 * - Create a new user account
 * - Update the user account
 * - Retrieve the updated user details
 * - Delete the user account
 */
public class Registration extends BaseTest {

    // Generate unique test data before executing the registration tests
    @Description("Generate dynamic test data for user registration")
    @BeforeClass
    public void registrationSetUp_BeforeTest()
    {
        // Generate a unique email and predefined password for the test user
        Allure.step("Generate unique email and password for test", () -> {

            email = "test" + UUID.randomUUID() + "@test.com";
            password = "123456789";

            // Add generated test data to the Allure report
            Allure.parameter("Generated Email", email);
            Allure.parameter("Password", password);
        });
    }


    /**
     * Create a new user account using valid registration data.
     */
    @Epic("User APIs")
    @Feature("Create User")
    @Story("Register new user")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Verify user account can be created successfully with valid data")
    @Test
    public void API11_POST_To_Create_Register_User_Account()
    {
        // Display the generated email used for account creation
        System.out.println(email);

        // Send POST request with valid user registration details
        Response res = Allure.step("Send create user request with email: " + email, () ->
                given()
                        .baseUri(BaseURL)
                        .header("Content-Type", "application/x-www-form-urlencoded")

                        .formParam("name", "Test User")
                        .formParam("email", email)
                        .formParam("password", password)
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

        // Display the API response for execution visibility
        System.out.println(res.asPrettyString());

        // Validate the user creation response
        Allure.step("Validate user creation response", () ->
        {
            // Verify the HTTP response status
            Allure.step("Verify the HTTP response status", () -> {
                ResponseValidator.validateStatus(res, 200);
            });

            // Verify that the API response code is 201
            Allure.step("Verify API response code is 201", () -> {
                ResponseValidator.validateJsonField(res, "responseCode", 201);
            });

            // Verify that the response confirms successful user creation
            Allure.step("Verify user creation success message", () -> {
                ResponseValidator.validateJsonField(res, "message", "User created!");
            });
        });
    }


    /**
     * Delete the existing user account created during the test flow.
     */
    @Epic("User APIs")
    @Feature("Delete User")
    @Story("Delete existing user")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify user account can be deleted successfully")
    @Test(dependsOnMethods ="API14_GET_user_account_detail_by_email")
    public void API12_DELETE_METHOD_To_Delete_User_Account()
    {
        // Display the email of the account to be deleted
        System.out.println(email);

        // Send DELETE request using the registered user's credentials
        Response res = Allure.step("Send delete user request for email: " + email, () ->
                given()
                        .baseUri(BaseURL)
                        .formParam("email",email)
                        .formParam("password",password)

                        .when()
                        .delete(UserDeleteEndPoint)

                        .then()
                        .extract()
                        .response()
        );

        // Display the API response for execution visibility
        System.out.println(res.asPrettyString());

        // Validate the account deletion response
        Allure.step("Validate delete user response", () ->
        {
            // Verify the HTTP response status
            Allure.step("Verify the HTTP response status", () -> {
                ResponseValidator.validateStatus(res, 200);
            });

            // Verify that the API response code is 200
            Allure.step("Verify API response code is 200", () -> {
                ResponseValidator.validateJsonField(res, "responseCode", 200);
            });

            // Verify that the response confirms successful account deletion
            Allure.step("Verify account deletion success message", () -> {
                ResponseValidator.validateJsonField(res, "message", "Account deleted!");
            });
        });
    }


    /**
     * Update the existing user account with modified user details.
     */
    @Epic("User APIs")
    @Feature("Update User")
    @Story("Update user details")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify user account details can be updated successfully")
    @Test(dependsOnMethods ="API11_POST_To_Create_Register_User_Account")
    public void API13_PUT_METHOD_To_Update_User_Account()
    {
        // Display the email of the account being updated
        System.out.println(email);

        // Send PUT request with updated user account details
        Response res = Allure.step("Send update user request for email: " + email, () ->
                given()
                        .baseUri(BaseURL)
                        .header("Content-Type", "application/x-www-form-urlencoded")

                        .formParam("name", "updated_user")
                        .formParam("email", email)
                        .formParam("password", password)
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

        // Display the API response for execution visibility
        System.out.println(res.asPrettyString());

        // Validate the user update response
        Allure.step("Validate update user response", () ->
        {
            // Verify the HTTP response status
            Allure.step("Verify the HTTP response status", () -> {
                ResponseValidator.validateStatus(res, 200);
            });

            // Verify that the API response code is 200
            Allure.step("Verify API response code is 200", () -> {
                ResponseValidator.validateJsonField(res, "responseCode", 200);
            });

            // Verify that the response confirms successful user update
            Allure.step("Verify user update success message", () -> {
                ResponseValidator.validateJsonField(res, "message", "User updated!");
            });
        });
    }


    /**
     * Retrieve the updated user account details using the registered email.
     */
    @Epic("User APIs")
    @Feature("Get User Details")
    @Story("Retrieve user by email")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify user details can be retrieved using email")
    @Test(dependsOnMethods ="API13_PUT_METHOD_To_Update_User_Account")
    public void API14_GET_user_account_detail_by_email()
    {
        // Display the email used to retrieve the user details
        System.out.println(email);

        // Send GET request using the registered user's email
        Response res = Allure.step("Send GET user request with email: " + email, () ->
                given()
                        .baseUri(BaseURL)
                        .queryParam("email", email)

                        .when()
                        .get(UserDetailsEndPoint)

                        .then()
                        .extract()
                        .response()
        );

        // Display the API response for execution visibility
        System.out.println(res.asPrettyString());

        // Validate the retrieved user details
        Allure.step("Validate user response", () ->
        {
            // Verify the HTTP response status
            Allure.step("Verify the HTTP response status", () -> {
                ResponseValidator.validateStatus(res, 200);
            });

            // Verify that the API response code is 200
            Allure.step("Verify API response code is 200", () -> {
                ResponseValidator.validateJsonField(res, "responseCode", 200);
            });

            // Verify that the user object exists
            Allure.step("Verify user object exists", () -> {
                ResponseValidator.validateFieldExists(res, "user");
            });

            // Verify basic user information
            Allure.step("Verify basic user information", () -> {
                ResponseValidator.validateJsonField(res, "user.name", "updated_user");
                ResponseValidator.validateJsonField(res, "user.email", email);
                ResponseValidator.validateJsonField(res, "user.title", "Mr");
            });

            // Verify user birth information
            Allure.step("Verify user birth information", () -> {
                ResponseValidator.validateJsonField(res, "user.birth_day", "10");
                ResponseValidator.validateJsonField(res, "user.birth_month", "5");
                ResponseValidator.validateJsonField(res, "user.birth_year", "1995");
            });

            // Verify user name details
            Allure.step("Verify user name details", () -> {
                ResponseValidator.validateJsonField(res, "user.first_name", "Test");
                ResponseValidator.validateJsonField(res, "user.last_name", "User");
            });

            // Verify company information
            Allure.step("Verify user company information", () -> {
                ResponseValidator.validateJsonField(res, "user.company", "QA");
            });

            // Verify address information
            Allure.step("Verify user address information", () -> {
                ResponseValidator.validateJsonField(res, "user.address1", "Street 1");
                ResponseValidator.validateJsonField(res, "user.address2", "Apt 1");
            });

            // Verify location information
            Allure.step("Verify user location information", () -> {
                ResponseValidator.validateJsonField(res, "user.country", "Egypt");
                ResponseValidator.validateJsonField(res, "user.state", "Cairo");
                ResponseValidator.validateJsonField(res, "user.city", "Cairo");
                ResponseValidator.validateJsonField(res, "user.zipcode", "12345");
            });
        });
    }
}