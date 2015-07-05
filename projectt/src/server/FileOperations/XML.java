package server.FileOperations;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XML {

/* to make any method here , please remember to *save* the modified xml
 * i caught for thousand times for this.
 */



 public void create( String file_name , String root_element_name  ) throws ParserConfigurationException, TransformerConfigurationException, TransformerException, IOException {

     	  DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
	  DocumentBuilder docBuilder;

            docBuilder = docFactory.newDocumentBuilder();
              Document doc = docBuilder.newDocument();

         Element rootElement = doc.createElement(root_element_name);
         doc.appendChild(rootElement);

              	  TransformerFactory transformerFactory = TransformerFactory.newInstance();
	  Transformer transformer = transformerFactory.newTransformer();
	  DOMSource source = new DOMSource(doc);
	  StreamResult result =  new StreamResult(new File(file_name));
          //  StreamResult result =  new StreamResult(File.createTempFile(file_name, "rtft"));
	  transformer.transform(source, result);

 }


 public void add_element ( String file_name,  String element_name ) throws ParserConfigurationException, SAXException, IOException, TransformerConfigurationException, TransformerException {

	 DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
	 DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
	 Document doc = docBuilder.parse(file_name);

      	 Node parent = doc.getFirstChild();

	 Element e = doc.createElement(element_name);

	 parent.appendChild(e);

     TransformerFactory transformerFactory = TransformerFactory.newInstance();
     Transformer transformer = transformerFactory.newTransformer();
     DOMSource source = new DOMSource(doc);
     StreamResult result =  new StreamResult(new File(file_name));
     transformer.transform(source, result);

 }

  public void add_sub_element ( String file_name,  String main_element_name, String sub_element_name , String data ) throws ParserConfigurationException, SAXException, IOException, TransformerConfigurationException, TransformerException {

	 DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
	 DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
	 Document doc = docBuilder.parse(file_name);

      	 Node parent = doc.getElementsByTagName( main_element_name ).item(0);

	 Element sub = doc.createElement(sub_element_name);
         sub.setTextContent(data);

	 parent.appendChild(sub);

     TransformerFactory transformerFactory = TransformerFactory.newInstance();
     Transformer transformer = transformerFactory.newTransformer();
     DOMSource source = new DOMSource(doc);
     StreamResult result =  new StreamResult(new File(file_name));
     transformer.transform(source, result);

 }

  public void add_sub_element_no_repeat ( String file_name,  String main_element_name, String sub_element_name , String data ) throws ParserConfigurationException, SAXException, IOException, TransformerConfigurationException, TransformerException {

	 DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
	 DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
	 Document doc = docBuilder.parse(file_name);

      	 Node parent = doc.getElementsByTagName( main_element_name ).item(0);
         String[] children = get_sub_element_child ( file_name , main_element_name , sub_element_name );
	 boolean already = false ;
         for ( int i = 0 ; i < children.length ; i ++ ) {

             if ( data.equals(children[i]))  {

                 already = true;
             }
         }

         if ( already == false )  {
         Element sub = doc.createElement(sub_element_name);
         sub.setTextContent(data);
	 parent.appendChild(sub);

      }

     TransformerFactory transformerFactory = TransformerFactory.newInstance();
     Transformer transformer = transformerFactory.newTransformer();
     DOMSource source = new DOMSource(doc);
     StreamResult result =  new StreamResult(new File(file_name));
     transformer.transform(source, result);

 }


public void modify_sub_element ( String file_name, String main_element_name, String sub_element_name, String mod_data ) throws ParserConfigurationException, SAXException, IOException, TransformerConfigurationException, TransformerException {

         DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
	 DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
	 org.w3c.dom.Document doc = docBuilder.parse(file_name);
          Node parent = doc.getElementsByTagName( main_element_name ).item(0);
          System.out.println(sub_element_name);
         NodeList list = doc.getElementsByTagName(sub_element_name);
         System.out.println (list);
            for (int i=0; i<list.getLength(); i++) {
           // Get element
            Node node = list.item(i);

           if ( node.getParentNode() == parent ) {

               System.out.println(node.getTextContent());
               node.setTextContent(mod_data);
         

                }

         }

           TransformerFactory transformerFactory = TransformerFactory.newInstance();
     Transformer transformer = transformerFactory.newTransformer();
     DOMSource source = new DOMSource(doc);
     StreamResult result =  new StreamResult(new File(file_name));
     transformer.transform(source, result);




}

public void delete_sub_element ( String file_name, String sub_element ) throws ParserConfigurationException, SAXException, IOException, TransformerConfigurationException, TransformerException {

         DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
	 DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
	 org.w3c.dom.Document doc = docBuilder.parse(file_name);

        Element element = (Element)doc.getElementsByTagName(sub_element).item(0);
        element.getParentNode().removeChild(element);

      TransformerFactory transformerFactory = TransformerFactory.newInstance();
     Transformer transformer = transformerFactory.newTransformer();
     DOMSource source = new DOMSource(doc);
     StreamResult result =  new StreamResult(new File(file_name));
     transformer.transform(source, result);


}

 public void delete_sub_element_data ( String file_name, String main_element_name, String sub_element_name , String data ) throws ParserConfigurationException, SAXException, IOException, TransformerConfigurationException, TransformerException {


     /* this fnc gave me hard time.
      *
      * thanks to http://www.roseindia.net/xml/dom/DOMElements.shtml
      */
         DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
	 DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
	 org.w3c.dom.Document doc = docBuilder.parse(file_name);
          Node parent = doc.getElementsByTagName( main_element_name ).item(0);
          System.out.println(sub_element_name);
         NodeList list = doc.getElementsByTagName(sub_element_name);
         System.out.println (list);
         String[] children = new String[list.getLength()];
            for (int i=0; i<list.getLength(); i++) {
           // Get element
           Element element = (Element)list.item(i);
           if ( element.getParentNode() == parent )
         
           if ( element.getTextContent().equals(data) ) {
              
               parent.removeChild(list.item(i));
               

                }

          }

     TransformerFactory transformerFactory = TransformerFactory.newInstance();
     Transformer transformer = transformerFactory.newTransformer();
     DOMSource source = new DOMSource(doc);
     StreamResult result =  new StreamResult(new File(file_name));
     transformer.transform(source, result);



 }

 public String read_element(String file_name, String element_name  ) throws ParserConfigurationException, SAXException, IOException {

         DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
	 DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
	 org.w3c.dom.Document doc = docBuilder.parse(file_name);

         org.w3c.dom.Node n = doc.getElementsByTagName(element_name).item(0);

         String data = n.getTextContent();

         return data;

 }

 public String read_sub_element ( String file_name, String main_element_name, String sub_element_name ) throws ParserConfigurationException, SAXException, IOException {


     /* this fnc gave me hard time.
      *
      * thanks to http://www.roseindia.net/xml/dom/DOMElements.shtml
      */
         DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
	 DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
	 org.w3c.dom.Document doc = docBuilder.parse(file_name);
          Node parent = doc.getElementsByTagName( main_element_name ).item(0);
          System.out.println(sub_element_name);
         NodeList list = doc.getElementsByTagName(sub_element_name);
         System.out.println (list);
            for (int i=0; i<list.getLength(); i++) {
           // Get element
           Element element = (Element)list.item(i);
       
           if ( element.getParentNode() == parent ) {
           String data = element.getTextContent();
           return data;

                }

         }

         return null;
 }

 public String[] get_child ( String file_name , String element_name ) throws ParserConfigurationException, SAXException, IOException {

            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            org.w3c.dom.Document doc = docBuilder.parse(file_name);
             Node parent = doc.getElementsByTagName( element_name ).item(0);
        NodeList childNodes = parent.getChildNodes();
        String children[] = new String[childNodes.getLength()];
        for (int i=0; i<childNodes.getLength(); i++) {
             Element element = (Element)childNodes.item(i);

            children[i] = element.getNodeName();
        }

return children;

 }

  public String[] get_sub_element_child ( String file_name , String main_element_name, String sub_element_name ) throws ParserConfigurationException, SAXException, IOException {

     /* this fnc gave me hard time.
      *
      * thanks to http://www.roseindia.net/xml/dom/DOMElements.shtml
      */
         DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
	 DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
	 org.w3c.dom.Document doc = docBuilder.parse(file_name);
          Node parent = doc.getElementsByTagName( main_element_name ).item(0);
          System.out.println(sub_element_name);
         NodeList list = doc.getElementsByTagName(sub_element_name);
         System.out.println (list);
         String[] children = new String[list.getLength()];
            for (int i=0; i<list.getLength(); i++) {
           // Get element
           Element element = (Element)list.item(i);
           if ( element.getParentNode() == parent ) {
               System.out.println("get " +element.getTextContent());
           children[i] = element.getTextContent();
           System.out.println(children[i]);

                }
            }

         return children;

     }

}