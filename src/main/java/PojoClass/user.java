package PojoClass;

/**
 * Represents user account information returned by the API.
 *
 * <p>This POJO is used to map user-related JSON data to a Java object
 * during API request and response processing.</p>
 *
 * <p>The class contains personal, account, and address information
 * associated with a user.</p>
 */
public class user {

    // Stores the user's full name
    public String name;

    // Stores the user's title
    public String title;

    // Stores the user's birth date
    public String birth_date;

    // Stores the user's birth month
    public String birth_month;

    // Stores the user's birth year
    public String birth_year;

    // Stores the user's first name
    public String firstname;

    // Stores the user's last name
    public String lastname;

    // Stores the user's company name
    public String company;

    // Stores the user's primary address
    public String address1;

    // Stores the user's secondary address information
    public String address2;

    // Stores the user's country
    public String country;

    // Stores the user's ZIP/postal code
    public String zipcode;

    // Stores the user's state
    public String state;

    // Stores the user's city
    public String city;

    // Stores the user's mobile phone number
    public String mobile_number;

    /**
     * Returns the user's full name.
     *
     * @return the user's name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the user's full name.
     *
     * @param name the user's name
     */
    public void setName(String name) {
        this.name = name;
    }
}