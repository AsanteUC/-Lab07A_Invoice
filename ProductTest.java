import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProductTest
{
    @Test
    void storesNameAndUnitPrice()
    {
        Product p = new Product("Toaster", 29.95);
        assertEquals("Toaster", p.getName());
        assertEquals(29.95, p.getUnitPrice(), 0.0001);
    }
}
