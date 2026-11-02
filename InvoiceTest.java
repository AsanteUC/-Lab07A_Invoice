import org.junit.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class InvoiceTest
{
    private Invoice sampleInvoice()
    {
        Invoice invoice = new Invoice(new Address("Sam's Small Appliances", "100 Main Street", "Anytown", "CA", "98765"));
        invoice.addLineItem(new Product("Toaster", 29.95), 3);
        invoice.addLineItem(new Product("Hair Dryer", 24.95), 1);
        invoice.addLineItem(new Product("Car Vacuum", 19.99), 2);
        return invoice;
    }

    @Test
    void emptyInvoiceHasZeroAmountDue()
    {
        assertEquals(0.0, new Invoice().getAmountDue(), 0.0001);
    }

    @Test
    void amountDueIsSumOfLineTotals()
    {
        assertEquals(154.78, sampleInvoice().getAmountDue(), 0.0001);
    }

    @Test
    void countsLineItems()
    {
        assertEquals(3, sampleInvoice().getItemCount());
    }

    @Test
    void formattedInvoiceContainsKeyFields()
    {
        String text = sampleInvoice().format();
        assertTrue(text.contains("I N V O I C E"));
        assertTrue(text.contains("100 Main Street"));
        assertTrue(text.contains("Anytown, CA 98765"));
        assertTrue(text.contains("Toaster"));
        assertTrue(text.contains("$89.85"));
        assertTrue(text.contains("AMOUNT DUE:"));
        assertTrue(text.contains("$154.78"));
    }
}
