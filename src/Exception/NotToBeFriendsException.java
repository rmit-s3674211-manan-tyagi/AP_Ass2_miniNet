package Exception;

import javax.swing.JOptionPane;

public class NotToBeFriendsException extends Exception {
    public NotToBeFriendsException()
    {
    	JOptionPane.showMessageDialog(null,"Users do not satisfy requirements to form connections.");
    }
}
