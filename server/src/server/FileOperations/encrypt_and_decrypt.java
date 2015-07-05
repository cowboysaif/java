/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


package server.FileOperations;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

/**
 *
 * @author saif
 */
public class encrypt_and_decrypt {

    Cipher ecipher;
    Cipher dcipher;

    public void keygen (SecretKey key) {
        // Create an 8-byte initialization vector
        byte[] iv = new byte[]{
            (byte)0x8E, 0x12, 0x39, (byte)0x9C,
            0x07, 0x72, 0x6F, 0x5A
        };
        AlgorithmParameterSpec paramSpec = new IvParameterSpec(iv);
        try {
            ecipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
            dcipher = Cipher.getInstance("DES/CBC/PKCS5Padding");

            // CBC requires an initialization vector
            ecipher.init(Cipher.ENCRYPT_MODE, key, paramSpec);
            dcipher.init(Cipher.DECRYPT_MODE, key, paramSpec);
        } catch (java.security.InvalidAlgorithmParameterException e) {
        } catch (javax.crypto.NoSuchPaddingException e) {
        } catch (java.security.NoSuchAlgorithmException e) {
        } catch (java.security.InvalidKeyException e) {
        }
    }

    // Buffer used to transport the bytes from one stream to another
    byte[] buf = new byte[1024];

    public void encrypt(InputStream in, OutputStream out) {
        try {
            // Bytes written to out will be encrypted
            out = new CipherOutputStream(out, ecipher);

            // Read in the cleartext bytes and write to out to encrypt
            int numRead = 0;
            while ((numRead = in.read(buf)) >= 0) {
                out.write(buf, 0, numRead);
            }
            out.close();
        } catch (java.io.IOException e) {
        }
    }

    public void decrypt(InputStream in, OutputStream out) {
        try {
            // Bytes read from in will be decrypted
            in = new CipherInputStream(in, dcipher);

            // Read in the decrypted bytes and write the cleartext to out
            int numRead = 0;
            while ((numRead = in.read(buf)) >= 0) {
                out.write(buf, 0, numRead);
            }
            out.close();
        } catch (java.io.IOException e) {
        }

    }


public void encrypt( String input_file , String output_file ) throws NoSuchAlgorithmException, FileNotFoundException {
    
    
    // Generate a temporary key. In practice, you would save this key.
    // See also <a href="/egs/javax.crypto/PassKey.html">Encrypting with DES Using a Pass Phrase</a>.
    
   SecretKey key = KeyGenerator.getInstance("DES").generateKey();

    // Create encrypter/decrypter class
    keygen(key);
    file x = new file();
    x.save_Key(key);

            
    // Encrypt
    
    

 encrypt(new FileInputStream(input_file), new FileOutputStream(output_file));

File f = new File(input_file);


f.delete();

}


public void decrypt ( String input_file , String output_file ) throws NoSuchAlgorithmException, FileNotFoundException, IOException, ClassNotFoundException {
    
    
    // Generate a temporary key. In practice, you would save this key.
    // See also <a href="/egs/javax.crypto/PassKey.html">Encrypting with DES Using a Pass Phrase</a>.
 

    file x = new file();
    SecretKey key = x.get_Key();
    keygen(key);

 decrypt(new FileInputStream(input_file), new FileOutputStream(output_file));


}


}


