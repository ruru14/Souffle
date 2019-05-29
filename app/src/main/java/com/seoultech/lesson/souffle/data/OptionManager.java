package com.seoultech.lesson.souffle.data;

import com.seoultech.lesson.souffle.data.model.Option;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

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

public class OptionManager {

    private Option option;

    private final String filepath = "src/main/res/xml/attribute.xml";

    private DocumentBuilderFactory docFactory;
    private DocumentBuilder docBuilder;
    private Document doc;
    private Transformer former;
    private Node autoLogin;

     public OptionManager(){
         try{
             docFactory = DocumentBuilderFactory.newInstance();
             docBuilder = docFactory.newDocumentBuilder();
             doc = docBuilder.parse(filepath);
             former = TransformerFactory.newInstance().newTransformer();
             doc.getDocumentElement().normalize();

             Element root = doc.getDocumentElement();

             NodeList list = root.getElementsByTagName("login");
             Element elm = (Element) list.item(0);
             autoLogin = elm.getElementsByTagName("autoLogin").item(0);
             option = new Option(Boolean.parseBoolean(autoLogin.getTextContent()));
         } catch (ParserConfigurationException e) {
             e.printStackTrace();
         } catch (SAXException e) {
             e.printStackTrace();
         } catch (IOException e) {
             e.printStackTrace();
         } catch (TransformerConfigurationException e) {
             e.printStackTrace();
         } catch (TransformerException e) {
             e.printStackTrace();
         }
     }

     public void setAutoLogin(boolean attr) throws TransformerException {
         autoLogin.setTextContent(Boolean.toString(attr));
         former.transform(new DOMSource(doc), new StreamResult(new File(filepath)));
         option.setAutoLogin(attr);
     }

     public Option getOption(){
         return this.option;
     }

}
