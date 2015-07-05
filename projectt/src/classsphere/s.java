/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package classsphere;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import server.FileOperations.XML;
import server.FileOperations.encrypt_and_decrypt;

/**
 *
 * @author Anindo
 */
public class s {
    public static void main(String[] args){
        try {
            String id;
                id="id98765";
                 XML x=new XML();
                 encrypt_and_decrypt e=new encrypt_and_decrypt();
                
                 String fathersname=x.read_element(id+".xml","fathersname");
                 System.out.print(fathersname);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(s.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(s.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(s.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(s.class.getName()).log(Level.SEVERE, null, ex);
        } 
}
}
