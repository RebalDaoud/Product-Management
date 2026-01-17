package erpsystem;

import Music.Music;
import erpsystem.swing.LoginFrame;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

public class ERPSystem {
    
    public static void main(String[] args) throws InterruptedException, AllException.InvalidQuantityException,
            AllException.ItemNotFoundException, AllException.InvalidNumberException, IOException, AllException.InvalidDateException {
        
         new Music();
         
        javax.swing.SwingUtilities.invokeLater(() -> {
            new LoginFrame().setVisible(true);
        });
        
    }

}
