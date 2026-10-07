package PojoClass;

import java.util.List;

/**
 * Represents the response returned by the Products List API.
 *
 * <p>This POJO is used to map the Products List API JSON response
 * to a Java object for response processing and validation.</p>
 *
 * <p>The response contains the API response code and a list
 * of products returned by the API.</p>
 */
public class ProductsResponse {

    // Stores the API response code
    private int responseCode;

    // Stores the list of products returned by the API
    private List<Product> products;

    /**
     * Returns the API response code.
     *
     * @return the API response code
     */
    public int getResponseCode() {
        return responseCode;
    }

    /**
     * Sets the API response code.
     *
     * @param responseCode the API response code
     */
    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    /**
     * Returns the list of products returned by the API.
     *
     * @return the list of products
     */
    public List<Product> getProducts() {
        return products;
    }

    /**
     * Sets the list of products returned by the API.
     *
     * @param products the list of products
     */
    public void setProducts(List<Product> products) {
        this.products = products;
    }
}