package server;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import org.xml.sax.SAXException;
import server.FileOperations.XML;
import server.FileOperations.encrypt_and_decrypt;
import wizards.server_wizard;
import server.core.frame.core_main;
import server.core.frame.core_main_temp;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author taifur
 */
public class main {

    public static void main ( String[] args ) throws ParserConfigurationException, SAXException, IOException, NoSuchAlgorithmException, FileNotFoundException, ClassNotFoundException, TransformerConfigurationException, TransformerException {


      
 
        XML x = new XML();
    encrypt_and_decrypt e = new encrypt_and_decrypt();
    e.decrypt("dbase.rtft", "dbase.xml");
    String run_once = x.read_element("dbase.xml", "run_once");
    System.out.println(run_once);

     if ( run_once.equals("true") ) {

         server_wizard y = new server_wizard();
         y.setBounds(100, 100, 710, 500);
         y.setVisible(true);
        
         e.encrypt("dbase.xml", "dbase.rtft"); // whenever decrypt,please encrypt

        }

 else {

       core_main_temp z = new core_main_temp();
       z.setBounds(100,100,730,430);
       z.setVisible(true);
       e.encrypt("dbase.xml", "dbase.rtft");

 }

   

    }

}
