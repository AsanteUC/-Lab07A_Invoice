import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

/**
 * The invoice entry window. The user enters the customer address block, adds
 * line items (product name, unit price, quantity) and then displays the
 * finished invoice in a text area.
 *
 * @author Asante
 * @version 1.0
 */
public class InvoiceFrame extends JFrame
{
    private Invoice invoice = new Invoice();

    private JTextField nameField;
    private JTextField streetField;
    private JTextField cityField;
    private JTextField stateField;
    private JTextField zipField;

    private JTextField productField;
    private JTextField priceField;
    private JTextField quantityField;

    private JLabel statusLabel;
    private JTextArea displayArea;

    private final Font labelFont = new Font("SansSerif", Font.PLAIN, 14);
    private final Font displayFont = new Font("Monospaced", Font.PLAIN, 14);

    /**
     * Builds the invoice window.
     */
    public InvoiceFrame()
    {
        super("Invoice Entry");
        setLayout(new BorderLayout());

        JPanel entry = new JPanel(new GridLayout(2, 1));
        entry.add(createCustomerPanel());
        entry.add(createLineItemPanel());

        add(entry, BorderLayout.NORTH);
        add(createDisplayPanel(), BorderLayout.CENTER);
        add(createButtonPanel(), BorderLayout.SOUTH);

        setSize(620, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private JPanel createCustomerPanel()
    {
        JPanel panel = new JPanel(new GridLayout(5, 2, 5, 5));
        panel.setBorder(BorderFactory.createTitledBorder("Customer Address"));

        nameField = new JTextField();
        streetField = new JTextField();
        cityField = new JTextField();
        stateField = new JTextField();
        zipField = new JTextField();

        addRow(panel, "Name:", nameField);
        addRow(panel, "Street:", streetField);
        addRow(panel, "City:", cityField);
        addRow(panel, "State:", stateField);
        addRow(panel, "ZIP:", zipField);
        return panel;
    }

    private JPanel createLineItemPanel()
    {
        JPanel panel = new JPanel(new GridLayout(4, 2, 5, 5));
        panel.setBorder(BorderFactory.createTitledBorder("Line Item"));

        productField = new JTextField();
        priceField = new JTextField();
        quantityField = new JTextField();
        statusLabel = new JLabel("No line items added yet");
        statusLabel.setFont(labelFont);

        addRow(panel, "Product name:", productField);
        addRow(panel, "Unit price:", priceField);
        addRow(panel, "Quantity:", quantityField);

        JButton addButton = new JButton("Add Line Item");
        addButton.addActionListener(ae -> addLineItem());
        panel.add(addButton);
        panel.add(statusLabel);
        return panel;
    }

    private void addRow(JPanel panel, String text, JTextField field)
    {
        JLabel label = new JLabel(text);
        label.setFont(labelFont);
        field.setFont(labelFont);
        panel.add(label);
        panel.add(field);
    }

    private JPanel createDisplayPanel()
    {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        displayArea = new JTextArea();
        displayArea.setFont(displayFont);
        displayArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(displayArea);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Invoice"));
        panel.add(scrollPane, BorderLayout.CENTER);
        return panel;
    }

    private JPanel createButtonPanel()
    {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));

        JButton displayButton = new JButton("Display Invoice");
        displayButton.addActionListener(ae -> displayInvoice());

        JButton newButton = new JButton("New Invoice");
        newButton.addActionListener(ae -> newInvoice());

        JButton quitButton = new JButton("Quit");
        quitButton.addActionListener(ae -> quit());

        panel.add(displayButton);
        panel.add(newButton);
        panel.add(quitButton);
        return panel;
    }

    /**
     * Validates the line item fields and adds the item to the invoice.
     */
    private void addLineItem()
    {
        String productName = productField.getText().trim();
        if (productName.isEmpty())
        {
            showError("Please enter a product name.");
            return;
        }

        double price;
        try
        {
            price = Double.parseDouble(priceField.getText().trim());
        }
        catch (NumberFormatException e)
        {
            showError("Unit price must be a number, for example 29.95.");
            return;
        }
        if (price < 0)
        {
            showError("Unit price cannot be negative.");
            return;
        }

        int quantity;
        try
        {
            quantity = Integer.parseInt(quantityField.getText().trim());
        }
        catch (NumberFormatException e)
        {
            showError("Quantity must be a whole number, for example 3.");
            return;
        }
        if (quantity < 1)
        {
            showError("Quantity must be at least 1.");
            return;
        }

        invoice.addLineItem(new Product(productName, price), quantity);
        statusLabel.setText("Added " + productName + ". Items: " + invoice.getItemCount());

        productField.setText("");
        priceField.setText("");
        quantityField.setText("");
        productField.requestFocusInWindow();
    }

    /**
     * Checks the address and line items, then shows the invoice in the text area.
     */
    private void displayInvoice()
    {
        if (nameField.getText().trim().isEmpty() || streetField.getText().trim().isEmpty()
                || cityField.getText().trim().isEmpty() || stateField.getText().trim().isEmpty()
                || zipField.getText().trim().isEmpty())
        {
            showError("Please fill in every customer address field.");
            return;
        }
        if (invoice.getItemCount() == 0)
        {
            showError("Please add at least one line item.");
            return;
        }

        invoice.setBillingAddress(new Address(
                nameField.getText().trim(),
                streetField.getText().trim(),
                cityField.getText().trim(),
                stateField.getText().trim(),
                zipField.getText().trim()));
        displayArea.setText(invoice.format());
    }

    /**
     * Clears the form and starts a new, empty invoice.
     */
    private void newInvoice()
    {
        invoice = new Invoice();
        nameField.setText("");
        streetField.setText("");
        cityField.setText("");
        stateField.setText("");
        zipField.setText("");
        productField.setText("");
        priceField.setText("");
        quantityField.setText("");
        displayArea.setText("");
        statusLabel.setText("No line items added yet");
    }

    private void quit()
    {
        int choice = JOptionPane.showConfirmDialog(this, "Are you sure you want to quit?",
                "Quit", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (choice == JOptionPane.YES_OPTION)
        {
            System.exit(0);
        }
    }

    private void showError(String message)
    {
        JOptionPane.showMessageDialog(this, message, "Input Error", JOptionPane.ERROR_MESSAGE);
    }
}
