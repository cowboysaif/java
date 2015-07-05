package server.FileOperations;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.crypto.SecretKey;

/**
 *
 * @author saif
 */
public class file {


public void save_Key ( SecretKey key ) {

            try {
                File f = new File("seck.rtft");
                FileOutputStream fileOutput = new FileOutputStream(f);
                ObjectOutputStream objectOutput = new
ObjectOutputStream(fileOutput);
                objectOutput.writeObject(key);
                objectOutput.flush();
                objectOutput.close();
            }catch(Exception e) {
            }
        }


public SecretKey get_Key ( ) throws FileNotFoundException, IOException, ClassNotFoundException {


                File f = new File("seck.rtft");
                FileInputStream fileInput = new FileInputStream(f);
                ObjectInputStream objectInput = new
ObjectInputStream(fileInput);
               SecretKey key =  (SecretKey) objectInput.readObject();

                objectInput.close();

            return key;
        }



}




