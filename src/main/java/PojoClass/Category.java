package PojoClass;

/**
 * Represents the category information returned by the API.
 *
 * <p>This POJO is used for mapping category-related JSON data
 * to Java objects during API response processing.</p>
 *
 * <p>The class contains the associated user type and category name,
 * along with their corresponding getters and setters.</p>
 */
public class Category {

    // Stores the user type associated with the category
    private UserType usertype;

    // Stores the category name
    private String category;

    /**
     * Returns the user type associated with the category.
     *
     * @return the associated user type
     */
    public UserType getUsertype() {
        return usertype;
    }

    /**
     * Sets the user type associated with the category.
     *
     * @param usertype the user type to associate with the category
     */
    public void setUsertype(UserType usertype) {
        this.usertype = usertype;
    }

    /**
     * Returns the category name.
     *
     * @return the category name
     */
    public String getCategory() {
        return category;
    }

    /**
     * Sets the category name.
     *
     * @param category the category name to assign
     */
    public void setCategory(String category) {
        this.category = category;
    }
}