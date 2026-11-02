/**
 * The customer name and mailing address block shown at the top of an invoice.
 *
 * @author Asante
 * @version 1.0
 */
public class Address
{
    private final String name;
    private final String street;
    private final String city;
    private final String state;
    private final String zip;

    /**
     * Creates an address block.
     *
     * @param name   the customer name
     * @param street the street address
     * @param city   the city
     * @param state  the state abbreviation
     * @param zip    the ZIP code
     */
    public Address(String name, String street, String city, String state, String zip)
    {
        this.name = name;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zip = zip;
    }

    public String getName()
    {
        return name;
    }

    public String getStreet()
    {
        return street;
    }

    public String getCity()
    {
        return city;
    }

    public String getState()
    {
        return state;
    }

    public String getZip()
    {
        return zip;
    }

    /**
     * Formats the address as three lines of text.
     *
     * @return name, street, and "city, state zip" separated by line breaks
     */
    public String format()
    {
        return name + "\n" + street + "\n" + city + ", " + state + " " + zip;
    }
}
