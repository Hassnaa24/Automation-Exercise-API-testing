package utilities;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;

/**
 * Utility class for parsing API responses into Java objects.
 *
 * <p>This class uses Jackson's ObjectMapper to deserialize
 * JSON response data into the specified Java class.</p>
 */
public class ResponseParser {

    // ObjectMapper instance used to deserialize JSON response data
    private static final ObjectMapper mapper = new ObjectMapper();

    /**
     * Parses the JSON content of an API response into an object
     * of the specified class type.
     *
     * @param response the API response containing the JSON data
     * @param clazz the class type to which the response will be converted
     * @param <T> the expected object type
     * @return an object populated with the data from the API response
     * @throws RuntimeException if the response cannot be parsed into the specified class
     */
    public static <T> T parse(Response response, Class<T> clazz) {
        try {

            // Extract the response body as a JSON string
            String json = response.asString();

            // Deserialize the JSON response into the requested Java object type
            return mapper.readValue(json, clazz);

        } catch (Exception e) {

            // Wrap the parsing error with a meaningful runtime exception
            throw new RuntimeException("Failed to parse response to " + clazz.getSimpleName(), e);
        }
    }
}