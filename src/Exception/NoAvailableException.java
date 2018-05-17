package Exception;

import javax.swing.JOptionPane;

public class NoAvailableException extends Exception{
    public NoAvailableException()
    {
       JOptionPane.showMessageDialog(null,"Cannot form connection. One or more of the users are already part of a couple");
    }
}
