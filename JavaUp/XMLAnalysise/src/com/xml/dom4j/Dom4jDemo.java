package com.xml.dom4j;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.Test;

import java.util.List;

public class Dom4jDemo {

    @Test
    public void test() throws DocumentException {
        //创建核心对象
        SAXReader saxReader = new SAXReader();
        //获取dom树
        Document document = saxReader.read("./xml/web.xml");
        //获取根节点
        Element root = document.getRootElement();
        //获取其他节点
        List<Element> elements = root.elements();
        //遍历集合
        for (Element ele:elements) {
            //获取servlet-name的标签体
            String text = ele.elementText("servlet-name");
            System.out.println(text);

            String text2 = ele.elementText("url-pattern");
            System.out.println(text2);
        }

        //获取root的version属性值
        String attribute = root.attributeValue("version");
        System.out.println(attribute);
    }
}
