package Exception;

import javax.swing.JOptionPane;

public class NoSuchAgeException extends Exception {

    public NoSuchAgeException()
    {
    	JOptionPane.showMessageDialog(null,"Invalid age. Please enter an age between 0-150.");
    }
}
