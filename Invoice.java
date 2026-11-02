import java.util.ArrayList;
import java.util.List;

/**
 * An invoice: a title, a customer address block, a list of line items and
 * the total amount due (the sum of the line totals).
 *
 * @author Asante
 * @version 1.0
 */
public class Invoice
{
    private static final String TITLE = "I N V O I C E";
    private static final int WIDTH = 52;

    private Address billingAddress;
    private final List<LineItem> items = new ArrayList<>();

    /**
     * Creates an empty invoice with no address.
     */
    public Invoice()
    {
        this.billingAddress = null;
    }

    /**
     * Creates an empty invoice for a customer.
     *
     * @param billingAddress the customer's address block
     */
    public Invoice(Address billingAddress)
    {
        this.billingAddress = billingAddress;
    }

    public void setBillingAddress(Address billingAddress)
    {
        this.billingAddress = billingAddress;
    }

    public Address getBillingAddress()
    {
        return billingAddress;
    }

    /**
     * Adds a line item to the invoice.
     *
     * @param product  the product purchased
     * @param quantity the number of units purchased
     */
    public void addLineItem(Product product, int quantity)
    {
        items.add(new LineItem(product, quantity));
    }

    /**
     * Returns the number of line items on the invoice.
     *
     * @return the line item count
     */
    public int getItemCount()
    {
        return items.size();
    }

    /**
     * Calculates the total amount due.
     *
     * @return the sum of all line item totals
     */
    public double getAmountDue()
    {
        double amountDue = 0;
        for (LineItem item : items)
        {
            amountDue += item.getTotal();
        }
        return amountDue;
    }

    /**
     * Builds the text version of the invoice, using text dividers instead of
     * graphics.
     *
     * @return the formatted invoice
     */
    public String format()
    {
        StringBuilder sb = new StringBuilder();
        String heavy = "=".repeat(WIDTH);
        String light = "-".repeat(WIDTH);

        sb.append(heavy).append("\n");
        sb.append(center(TITLE)).append("\n");
        sb.append(heavy).append("\n");
        if (billingAddress != null)
        {
            sb.append(billingAddress.format()).append("\n");
        }
        sb.append(light).append("\n");
        sb.append(String.format("%-24s %5s %10s %10s%n", "Item", "Qty", "Price", "Total"));
        sb.append(light).append("\n");
        for (LineItem item : items)
        {
            sb.append(String.format("%-24s %5d %10s %10s%n",
                    shorten(item.getProduct().getName(), 24),
                    item.getQuantity(),
                    money(item.getProduct().getUnitPrice()),
                    money(item.getTotal())));
        }
        sb.append(light).append("\n");
        sb.append(String.format("%-24s %5s %10s %10s%n", "AMOUNT DUE:", "", "", money(getAmountDue())));
        sb.append(heavy).append("\n");
        return sb.toString();
    }

    private static String money(double amount)
    {
        return String.format("$%.2f", amount);
    }

    private static String center(String text)
    {
        int pad = (WIDTH - text.length()) / 2;
        return " ".repeat(Math.max(pad, 0)) + text;
    }

    private static String shorten(String text, int max)
    {
        return text.length() <= max ? text : text.substring(0, max);
    }
}
