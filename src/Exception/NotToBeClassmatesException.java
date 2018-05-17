package Exception;

import javax.swing.JOptionPane;

public class NotToBeClassmatesException extends Exception {


        public NotToBeClassmatesException()
        {
        	JOptionPane.showMessageDialog(null,"Invalid operation. Young Children cannot have classmates");
        }

}
