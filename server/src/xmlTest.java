
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import server.FileOperations.XML;
import server.FileOperations.encrypt_and_decrypt;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author taifur
 */
public class xmlTest {
    
    public static void main (String[] args )  {
        try {
            XML x = new XML();
            encrypt_and_decrypt y = new encrypt_and_decrypt();
            y.decrypt("dbase.rtft", "dbase.xml");
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(xmlTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(xmlTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(xmlTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(xmlTest.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
