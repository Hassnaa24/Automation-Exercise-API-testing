package PojoClass;

/**
 * Represents a product returned by the API.
 *
 * <p>This POJO is used to map product-related JSON response data
 * to Java objects during API response processing.</p>
 *
 * <p>The product contains its identifier, name, price, brand,
 * and associated category information.</p>
 */
public class Product {

    // Stores the unique identifier of the product
    private int id;

    // Stores the product name
    private String name;

    // Stores the product price
    private String price;

    // Stores the product brand
    private String brand;

    // Stores the category information associated with the product
    private Category category;

    /**
     * Returns the unique product identifier.
     *
     * @return the product ID
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the unique product identifier.
     *
     * @param id the product ID
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns the product name.
     *
     * @return the product name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the product name.
     *
     * @param name the product name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the product price.
     *
     * @return the product price
     */
    public String getPrice() {
        return price;
    }

    /**
     * Sets the product price.
     *
     * @param price the product price
     */
    public void setPrice(String price) {
        this.price = price;
    }

    /**
     * Returns the product brand.
     *
     * @return the product brand
     */
    public String getBrand() {
        return brand;
    }

    /**
     * Sets the product brand.
     *
     * @param brand the product brand
     */
    public void setBrand(String brand) {
        this.brand = brand;
    }

    /**
     * Returns the category associated with the product.
     *
     * @return the product category
     */
    public Category getCategory() {
        return category;
    }

    /**
     * Sets the category associated with the product.
     *
     * @param category the product category
     */
    public void setCategory(Category category) {
        this.category = category;
    }
}