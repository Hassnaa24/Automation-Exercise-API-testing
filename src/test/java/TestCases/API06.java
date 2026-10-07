package TestCases;

import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import utilities.ResponseValidator;

import static io.restassured.RestAssured.given;
/**
 * API06 - Search Product Without Required Parameter
 *
 * Verifies that the Search Product API returns an appropriate error
 * when the required search_product parameter is not provided.
 *
 * Endpoint: POST /api/searchProduct
 * Request Parameter: search_product - Missing
 * Expected API Response Code: 400
 * Expected Message: "Bad request, search_product parameter is missing in POST request."
 *
 * Validations:
 * - HTTP response status is processed successfully.
 * - API responseCode is 400.
 * - API message confirms that the required parameter is missing.
 */
public class API06 extends BaseTest {

    @Epic("Search API")
    @Feature("Product Search - Negative Scenarios")
    @Story("Search Without Required Parameter")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify that the Search Product API returns a 400 error when the required search_product parameter is missing.")

    @Test
    public void API06_POST_To_Search_Product_without_search_product_parameter() {

        // Send POST request without providing the required search_product parameter
        Response res = Allure.step("Send search product request without the required search_product parameter", () ->
                given()
                        .baseUri(BaseURL)

                        .when()
                        .post(SearchEndPoint)

                        .then()
                        .extract()
                        .response()
        );

        // Display the API response for execution visibility and troubleshooting
        Allure.step("Display the Search Product API response", () -> {
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

        // Verify that the response message confirms the required parameter is missing
        Allure.step("Verify that the response message indicates the search_product parameter is missing", () -> {
            ResponseValidator.validateJsonField(
                    res,
                    "message",
                    "Bad request, search_product parameter is missing in POST request."
            );
        });
    }
}