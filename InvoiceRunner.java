import javax.swing.SwingUtilities;

/**
 * Starts the invoice application.
 *
 * @author Asante
 * @version 1.0
 */
public class InvoiceRunner
{
    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(() ->
        {
            InvoiceFrame frame = new InvoiceFrame();
            frame.setVisible(true);
        });
    }
}
