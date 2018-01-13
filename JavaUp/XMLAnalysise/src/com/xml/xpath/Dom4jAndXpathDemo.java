package com.xml.xpath;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.Test;

import java.util.List;


public class Dom4jAndXpathDemo {

    @Test
    public void test() throws DocumentException {
        Document document = new SAXReader().read("./xml/web.xml");

        //获取节点
        List<Element> list = document.selectNodes("/web-app/servlet/servlet-name");
        Element ele = list.get(0);
        System.out.println(ele.getText());

        Element node = (Element) document.selectSingleNode("//servlet/servlet-name");
        System.out.println(node.getText());
    }
}
