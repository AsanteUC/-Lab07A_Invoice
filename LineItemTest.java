import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LineItemTest
{
    @Test
    void totalIsUnitPriceTimesQuantity()
    {
        LineItem item = new LineItem(new Product("Toaster", 29.95), 3);
        assertEquals(89.85, item.getTotal(), 0.0001);
    }

    @Test
    void storesProductAndQuantity()
    {
        Product p = new Product("Hair Dryer", 24.95);
        LineItem item = new LineItem(p, 1);
        assertEquals(p, item.getProduct());
        assertEquals(1, item.getQuantity());
    }
}
