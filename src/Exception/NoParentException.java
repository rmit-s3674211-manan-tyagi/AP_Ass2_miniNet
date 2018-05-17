package Exception;

import javax.swing.JOptionPane;

public class NoParentException extends Exception {
    public NoParentException()
    {
    	JOptionPane.showMessageDialog(null,"Invalid operation.Both parents are required for a child.");
    }
}