/**
 * One line of an invoice: a product and the quantity purchased.
 * The line total is the unit price multiplied by the quantity.
 *
 * @author Asante
 * @version 1.0
 */
public class LineItem
{
    private final Product product;
    private final int quantity;

    /**
     * Creates a line item.
     *
     * @param product  the product purchased
     * @param quantity the number of units purchased
     */
    public LineItem(Product product, int quantity)
    {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct()
    {
        return product;
    }

    public int getQuantity()
    {
        return quantity;
    }

    /**
     * Calculates the total for this line.
     *
     * @return unit price times quantity
     */
    public double getTotal()
    {
        return product.getUnitPrice() * quantity;
    }
}
