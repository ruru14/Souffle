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

    private final String attributePath = "src/main/res/xml/attribute.xml";

    private DocumentBuilderFactory docFactory;
    private DocumentBuilder docBuilder;
    private Document doc;
    private Transformer former;
    private Node autoLogin;
    private Node language;

    public OptionManager() {
        try {
            docFactory = DocumentBuilderFactory.newInstance();
            docBuilder = docFactory.newDocumentBuilder();
            doc = docBuilder.parse(attributePath);
            former = TransformerFactory.newInstance().newTransformer();
            doc.getDocumentElement().normalize();

            Element root = doc.getDocumentElement();

            NodeList login = root.getElementsByTagName("login");
            Element loginElm = (Element) login.item(0);
            autoLogin = loginElm.getElementsByTagName("autoLogin").item(0);
            NodeList setting = root.getElementsByTagName("setting");
            Element settingElm = (Element) setting.item(0);
            language = settingElm.getElementsByTagName("language").item(0);
            option = new Option(Boolean.parseBoolean(autoLogin.getTextContent()), language.getTextContent());
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
        former.transform(new DOMSource(doc), new StreamResult(new File(attributePath)));
        option.setAutoLogin(attr);
    }

    public void setLanguage(String attr) throws TransformerException {
        language.setTextContent(attr);
        former.transform(new DOMSource(doc), new StreamResult(new File(attributePath)));
        option.setLanguage(attr);
    }

    public Option getOption() {
        return this.option;
    }

}
