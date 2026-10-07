package PojoClass;

/**
 * Represents the user type information returned by the API.
 *
 * <p>This POJO is used to map the usertype field from JSON
 * response data to a Java object during API response processing.</p>
 */
public class UserType {

    // Stores the user type
    private String usertype;

    /**
     * Returns the user type.
     *
     * @return the user type
     */
    public String getUsertype() {
        return usertype;
    }

    /**
     * Sets the user type.
     *
     * @param usertype the user type to assign
     */
    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }
}