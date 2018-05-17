package Exception;

import javax.swing.JOptionPane;

public class TooYoungException extends Exception{

    public TooYoungException() {
    	JOptionPane.showMessageDialog(null,"The user does not meet the age requirements for forming connections");
    }
}
