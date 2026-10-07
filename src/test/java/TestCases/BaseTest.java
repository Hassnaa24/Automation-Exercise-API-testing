package TestCases;

import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import org.testng.annotations.BeforeClass;
import utilities.ConfigReader;
import java.io.IOException;

/**
 * BaseTest
 *
 * Provides the common API configuration and endpoint setup
 * shared across all API test classes.
 *
 * Responsibilities:
 * - Load API configuration from the properties file.
 * - Initialize the base URL.
 * - Initialize all API endpoint paths.
 * - Provide the common Content-Type used by the API requests.
 * - Add configuration details to the Allure report.
 */
public class BaseTest {

    protected String BaseURL;
    protected String ProductsEndPoint;
    protected String BrandsEndPoint;
    protected String SearchEndPoint;
    protected String LoginEndPoint;
    protected String UserCreateEndPoint;
    protected String UserDeleteEndPoint;
    protected String UserUpdateEndPoint;
    protected String UserDetailsEndPoint;

    protected String ContentType = "application/x-www-form-urlencoded";

    protected static String email;
    protected static String password;

    /**
     * Loads API configuration and endpoint information
     * before executing the test class.
     *
     * @throws IOException if the configuration file cannot be read
     */
    @Description("Load API configuration from properties file")
    @BeforeClass
    public void setUp() throws IOException {

        Allure.step("Initialize API configuration", () -> {

            // Load base URL and API endpoints from the configuration file
            BaseURL = ConfigReader.GetProperties("config", "BaseURL");
            ProductsEndPoint = ConfigReader.GetProperties("config", "Products");
            BrandsEndPoint = ConfigReader.GetProperties("config", "Brands");
            SearchEndPoint = ConfigReader.GetProperties("config", "Search");
            LoginEndPoint = ConfigReader.GetProperties("config", "Login");
            UserCreateEndPoint = ConfigReader.GetProperties("config", "UserCreate");
            UserDeleteEndPoint = ConfigReader.GetProperties("config", "UserDelete");
            UserUpdateEndPoint = ConfigReader.GetProperties("config", "UserUpdate");
            UserDetailsEndPoint = ConfigReader.GetProperties("config", "UserDetails");

            // Add API configuration details to the Allure report
            Allure.parameter("Base URL", BaseURL);
            Allure.parameter("Products Endpoint", ProductsEndPoint);
            Allure.parameter("Brands Endpoint", BrandsEndPoint);
            Allure.parameter("Search Endpoint", SearchEndPoint);
            Allure.parameter("Login Endpoint", LoginEndPoint);
            Allure.parameter("User Create Endpoint", UserCreateEndPoint);
            Allure.parameter("User Delete Endpoint", UserDeleteEndPoint);
            Allure.parameter("User Update Endpoint", UserUpdateEndPoint);
            Allure.parameter("User Details Endpoint", UserDetailsEndPoint);
        });
    }
}






//API05
// protected String search_prod="top";

//API07
//protected  String User_email = "beta_signup_user89@example.com";
//protected  String User_password = "Secure#Pass89";

//API10
//protected  String invalid_email = "admin@admin";
//protected  String invalid_password = "admin";