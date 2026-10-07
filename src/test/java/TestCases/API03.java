package TestCases;

import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import utilities.ResponseValidator;

import static io.restassured.RestAssured.given;

/**
 * API03 - Get All Brands List
 *
 * Verifies that the Brands List API successfully retrieves all available brands.
 *
 * Endpoint: GET /api/brandsList
 * Expected API Response Code: 200
 *
 * Validations:
 * - HTTP response status is processed successfully.
 * - API responseCode is 200.
 * - Brands list is not empty.
 */
public class API03 extends BaseTest {

    @Epic("Brands API")
    @Feature("Brands List")
    @Story("Retrieve All Available Brands")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify that the Brands List API successfully returns all available brands with a valid response.")

    @Test
    public void API03_Get_All_Brands_List() {

        // Send GET request to retrieve all brands
        Response res = Allure.step("Send GET request to retrieve all brands", () ->
                given()
                        .baseUri(BaseURL)

                        .when()
                        .get(BrandsEndPoint)

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

        // Verify that the API response code is 200
        Allure.step("Verify that the API response code is 200", () -> {
            ResponseValidator.validateJsonField(res, "responseCode", 200);
        });

        // Verify that the brands list contains data
        Allure.step("Verify that the brands list is not empty", () -> {
            ResponseValidator.validateListNotEmpty(res, "brands");
        });
    }
}
