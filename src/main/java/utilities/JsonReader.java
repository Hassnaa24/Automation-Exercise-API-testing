package utilities;

import com.google.gson.Gson;

import java.io.FileReader;
import java.io.IOException;

/**
 * Utility class for reading test data from JSON files.
 *
 * <p>This class uses Gson to deserialize JSON data into
 * Java objects based on the specified class type.</p>
 */
public class JsonReader {

    // Gson instance used to deserialize JSON data into Java objects
    static final Gson gson = new Gson();

    /**
     * Reads a JSON file and converts its content into an object
     * of the specified class type.
     *
     * @param fileName the name of the JSON file without the .json extension
     * @param clazz the class type to which the JSON data will be converted
     * @param <T> the expected object type
     * @return an object populated with the data from the JSON file
     * @throws RuntimeException if the JSON file cannot be read
     */
    public static <T> T read_from_json(String fileName, Class<T> clazz)
    {
        // Define the location of the JSON test data files
        final String test_data_path = "src\\main\\resources\\";

        // Open the JSON file and automatically close the reader after processing
        try (FileReader reader = new FileReader(test_data_path + fileName + ".json"))
        {
            // Deserialize the JSON content into the requested Java object type
            return gson.fromJson(reader, clazz);
        }
        catch (IOException e)
        {
            // Wrap the file reading error with a meaningful runtime exception
            throw new RuntimeException("failed to read Json file: " + fileName, e);
        }
    }
}