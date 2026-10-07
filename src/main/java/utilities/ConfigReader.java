package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Utility class for reading configuration properties
 * from the test resources properties file.
 *
 * <p>This class provides a reusable method for retrieving
 * configuration values such as API base URLs and endpoint
 * paths used across the API test framework.</p>
 */
public class ConfigReader {

    /**
     * Retrieves a property value from the specified properties file.
     *
     * @param fileName the name of the properties file without the .properties extension
     * @param key the property key whose value needs to be retrieved
     * @return the value associated with the specified key
     * @throws IOException if the properties file cannot be accessed or loaded
     */
    public static String GetProperties(String fileName , String key) throws IOException {

        // Create a Properties object to load and access configuration values
        Properties property = new Properties();

        // Load the properties file from the test resources directory
        property.load(new FileInputStream("src\\test\\resources\\" + fileName+ ".properties") );

        // Return the value associated with the requested property key
        return property.getProperty(key);
    }
}