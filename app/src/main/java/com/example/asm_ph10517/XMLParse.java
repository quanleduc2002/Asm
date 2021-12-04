package com.example.asm_ph10517;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class XMLParse {
    public Document getDocument(String xml) throws ParserConfigurationException, IOException, SAXException {
        Document document = null;
        DocumentBuilder documentBuilder = null;
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        documentBuilder = factory.newDocumentBuilder();
        InputStream byteStream;
        InputSource inputSource = new InputSource();
        inputSource.setCharacterStream(new StringReader(xml));
        inputSource.setEncoding("UTF-8");
        document = documentBuilder.parse(inputSource);
        return document;
    }

    public String getValue(Element item, String name) {
        NodeList nodeList = item.getElementsByTagName(name);
        return this.getTextvalue(nodeList.item(0));
    }

    public final String getTextvalue(Node node) {
        Node child;
        if (node != null) {
            if (node.hasChildNodes()) {
                for (child = node.getFirstChild(); child != null; child = child.getNextSibling()) {
                    if (child.getNodeType() == node.TEXT_NODE) {
                        return child.getNodeValue();
                    }
                }
            }
        }

        return "";
    }
}
