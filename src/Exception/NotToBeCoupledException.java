package Exception;

import javax.swing.JOptionPane;

public class NotToBeCoupledException extends Exception {
    public NotToBeCoupledException()
    {
    	JOptionPane.showMessageDialog(null,"Cannot form connection. One or more of the users is underage to become an adult");
    }
}
