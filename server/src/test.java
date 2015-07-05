
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import org.xml.sax.SAXException;
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
public class test {

    public static void main (String[] args)  {

        try {
            XML x = new XML();
            encrypt_and_decrypt y = new encrypt_and_decrypt();
            y.decrypt("dbase.rtft", "dbase.xml");
            x.add_sub_element("dbase.xml", "s1", "s11", "11");
            y.encrypt("dbase.xml","dbase.rtft");
         
        }
        catch (TransformerConfigurationException ex) {
            Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerException ex) {
            Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
        }        catch (ParserConfigurationException ex) {
            Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
        }        catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
        }    }

}
