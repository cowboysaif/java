
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
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
public class startup_temp {

    public static void main ( String[] args ) throws ParserConfigurationException, TransformerConfigurationException, TransformerException, IOException, SAXException, NoSuchAlgorithmException {

        /*this part is created only to make the start-up
         * dbase.xml
         *
         * it should not be run at normal times !
         */



         XML x = new XML();
         encrypt_and_decrypt e = new encrypt_and_decrypt();
         x.create("dbase.xml", "information");
       x.add_sub_element("dbase.xml","information","first_run","run_once");
        

         x.add_sub_element("dbase.xml","first_run","run_once","true");
         x.add_element("dbase.xml", "fac_acc");
         x.add_element("dbase.xml" , "file_profile");
      
        
         e.encrypt("dbase.xml", "dbase.rtft");

        }

}
