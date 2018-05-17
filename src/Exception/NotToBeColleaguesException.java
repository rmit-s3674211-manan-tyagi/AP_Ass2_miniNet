package Exception;

import javax.swing.JOptionPane;

public class NotToBeColleaguesException extends Exception {

    public NotToBeColleaguesException()
    {
    	JOptionPane.showMessageDialog(null,"Invalid operation. Children cannot have colleagues");
    }
}
