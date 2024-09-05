package cz.pollib.entity.filter;

/**
 * Represents the criteria for filtering invoices.
 * <p>
 * Attributes:
 * - buyerId: The ID of the buyer to filter invoices by.
 * - sellerId: The ID of the seller to filter invoices by.
 * - product: The product name to filter invoices by.
 * - minPrice: The minimum price to filter invoices by.
 * - maxPrice: The maximum price to filter invoices by.
 * - limit: The maximum number of invoices to return (default is 10).
 */
public class InvoiceFilter {

    private Integer buyerId;
    private Integer sellerId;
    private String product;
    private Long minPrice;
    private Long maxPrice;
    private int limit = 10;

    public InvoiceFilter(Integer buyerId, Integer sellerId, String product, Long minPrice, Long maxPrice, int limit) {
        this.buyerId = buyerId;
        this.sellerId = sellerId;
        this.product = product;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.limit = limit;
    }

    public InvoiceFilter() {
    }

    // GETTERs and SETTERs block

    public Integer getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(Integer buyerId) {
        this.buyerId = buyerId;
    }

    public Integer getSellerId() {
        return sellerId;
    }

    public void setSellerId(Integer sellerId) {
        this.sellerId = sellerId;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public Long getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Long minPrice) {
        this.minPrice = minPrice;
    }

    public Long getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(Long maxPrice) {
        this.maxPrice = maxPrice;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}
