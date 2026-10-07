package TestCases;

import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import utilities.ResponseValidator;

import static io.restassured.RestAssured.given;
/**
 * API05 - Search Product With Valid Keyword
 *
 * Verifies that the Search Product API successfully returns products
 * matching a valid search keyword.
 *
 * Endpoint: POST /api/searchProduct
 * Request Parameter: search_product
 * Expected API Response Code: 200
 *
 * Validations:
 * - HTTP response status is processed successfully.
 * - API responseCode is 200.
 * - Products list is not empty.
 * - The category of the first returned product matches the search keyword.
 * - The category of the fifth returned product matches the search keyword.
 */
public class API05 extends BaseTest {

    @Epic("Search API")
    @Feature("Product Search")
    @Story("Search Products Using a Valid Keyword")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify that the Search Product API successfully returns products matching a valid search keyword.")

    @Parameters("search_prod")
    @Test
    public void API05_POST_To_Search_Product(String search_prod) {

        // Send POST request to search for products using the provided keyword
        Response res = Allure.step(
                "Send search product request with keyword: " + search_prod,
                () ->
                        given()
                                .baseUri(BaseURL)
                                .header("Content-Type", ContentType)
                                .formParam("search_product", search_prod)

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

        // Verify that the API response code is 200
        Allure.step("Verify that the API response code is 200", () -> {
            ResponseValidator.validateJsonField(res, "responseCode", 200);
        });

        // Verify that the search results contain products
        Allure.step("Verify that the products list is not empty", () -> {
            ResponseValidator.validateListNotEmpty(res, "products");
        });

        // Verify that the first returned product matches the search keyword
        Allure.step("Verify that the first product category matches the search keyword", () -> {
            ResponseValidator.validateNestedField(
                    res,
                    "products[0].category.category",
                    search_prod
            );
        });

        // Verify that the fifth returned product matches the search keyword
        Allure.step("Verify that the fifth product category matches the search keyword", () -> {
            ResponseValidator.validateNestedField(
                    res,
                    "products[4].category.category",
                    search_prod
            );
        });
    }
}