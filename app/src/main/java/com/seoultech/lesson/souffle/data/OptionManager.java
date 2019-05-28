package com.seoultech.lesson.souffle.data;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class OptionManager {
     public OptionManager(){
         try{
             String filepath = "src/main/res/xml/attribute.xml";
             DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
             DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
             Document doc = docBuilder.parse(filepath);
             doc.getDocumentElement().normalize();

             Element root = doc.getDocumentElement();

             NodeList list = root.getElementsByTagName("login");
             Element elm = (Element) list.item(0);
             Node node = elm.getElementsByTagName("autoLogin").item(0);

             System.out.println(node.getTextContent());
         } catch (ParserConfigurationException e) {
             e.printStackTrace();
         } catch (SAXException e) {
             e.printStackTrace();
         } catch (IOException e) {
             e.printStackTrace();
         }
     }
}
