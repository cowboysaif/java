package hospitalitymanagement;


import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import org.xml.sax.SAXException;

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
         * it should not be opened for any reason !
         */



         XML x = new XML();
         encrypt_and_decrypt e = new encrypt_and_decrypt();
         x.create("dbase.xml", "information");
       x.add_sub_element("dbase.xml","information","first_run","run_once");
        

         x.add_sub_element("dbase.xml","first_run","run_once","true");
        
         e.encrypt("dbase.xml", "dbase.rtft");

        }

}
