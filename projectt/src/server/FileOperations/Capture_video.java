/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package server.FileOperations;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.media.Buffer;

import javax.media.CaptureDeviceInfo;
import javax.media.CaptureDeviceManager;
import javax.media.Format;
import javax.media.Manager;
import javax.media.MediaLocator;
import javax.media.Player;
import javax.media.control.FormatControl;
import javax.media.control.FrameGrabbingControl;
import javax.media.format.RGBFormat;
import javax.media.format.VideoFormat;
import javax.media.util.BufferToImage;
import javax.swing.JFrame;

/**
 *
 * @author Family pc
 */
public class Capture_video {

    /**
     * @param args the command line arguments
     */
    
    CaptureDeviceInfo cam;
    Format format = new RGBFormat();

	MediaLocator locator;
	public static Player player;
	FormatControl formatControl;
        public static JFrame f = new JFrame();
    
	public Capture_video(){
		try{
			// List out available Devices to Capture Video. 
			Vector<CaptureDeviceInfo> list = CaptureDeviceManager.getDeviceList(format);
                        System.out.println(CaptureDeviceManager.getDeviceList(null).toString());
			
			// Iterating list 
			for(CaptureDeviceInfo temp : list){
				// Checking whether the current device supports VfW
				// VfW = Video for Windows
				if(temp.getName().startsWith("vfw:")){

					System.out.println("Found : "+temp.getName().substring(4));
					// Selecting the very first device that supports VfW
					cam = temp;
					System.out.println("Selected : "+cam.getName().substring(4));
					break;
				}
			}

			System.out.println("Put it on work!...");
			// Getting the MediaLocator for Selected device.
			// MediaLocator describes the location of media content
			locator = cam.getLocator();

			if(locator != null){

				// Create a Player for Media Located by MediaLocator
				player = Manager.createRealizedPlayer(locator);

				if(player != null){
					
					// Starting the player
					player.start();

        
 
					
					// Creating a Frame to display Video
                                        
				        
					f.setTitle("Test Webcam");
					f.setLayout(new BorderLayout());
					// Adding the Visual Component to display Video captured by Player 
					// from URL provided by MediaLocator
					f.add(player.getVisualComponent(), BorderLayout.CENTER);
					f.pack(); 
                                        f.setAlwaysOnTop(true);
					f.setVisible(true);
                                         

				}

			}

		}catch(Exception e){
			e.printStackTrace();
		}
                
               
	}
        
        
        
    public static void stop () {
        try {
            
            Thread.sleep(5000);    
                                     FrameGrabbingControl fgc  = (FrameGrabbingControl) player.getControl("javax.media.control.FrameGrabbingControl");  

            Buffer               buf  = fgc.grabFrame();

            BufferToImage        btoi = new BufferToImage((VideoFormat)buf.getFormat());    
            player.stop();
            Image                img  = btoi.createImage(buf); 
            
            int           w = img.getWidth(null);

            int           h = img.getHeight(null);

            BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);

            Graphics2D    g2 = bi.createGraphics();

                          g2.drawImage(img, 0, 0, null);

                          g2.dispose();
            try {
                ImageIO.write(bi, "jpg", new File("C:/ii.jpg"));
                
            } catch (IOException ex) {
                Logger.getLogger(Capture_video.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(Capture_video.class.getName()).log(Level.SEVERE, null, ex);
        }
    }  
        
    public static void main(String[] args) {
        Capture_video c = new Capture_video();
        c.stop();
        
        // TODO code application logic here
    }
}
