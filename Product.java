/**
 * A product that can be sold: a name and a unit price.
 *
 * @author Asante
 * @version 1.0
 */
public class Product
{
    private final String name;
    private final double unitPrice;

    /**
     * Creates a product.
     *
     * @param name      the product name
     * @param unitPrice the price of one unit
     */
    public Product(String name, double unitPrice)
    {
        this.name = name;
        this.unitPrice = unitPrice;
    }

    public String getName()
    {
        return name;
    }

    public double getUnitPrice()
    {
        return unitPrice;
    }
}
