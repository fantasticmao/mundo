package cn.fantasticmao.mundo.web.support.wechat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;

/**
 * WeChatXmlUtil
 *
 * @author maodh
 * @since 2018/12/5
 */
class WeChatXmlUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(WeChatXmlUtil.class);

    static Element getRootElement(String xml) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = factory.newDocumentBuilder();
            InputSource source = new InputSource(new StringReader(xml));
            Document document = docBuilder.parse(source);
            return document.getDocumentElement();
        } catch (ParserConfigurationException | SAXException | IOException e) {
            LOGGER.error(e.getMessage(), e);
            LOGGER.error("读取微信消息失败 xml={}", xml);
            throw new IllegalArgumentException("读取微信 xml 消息失败");
        }
    }

    static String getFirstNodeTextContentByTagName(Element rootElement, String tagName) {
        NodeList nodeList = rootElement.getElementsByTagName(tagName);
        if (nodeList == null || nodeList.getLength() == 0) {
            LOGGER.error("读取微信消息失败 tagName={}", tagName);
            throw new IllegalArgumentException("读取微信 xml 消息失败");
        }
        return nodeList.item(0).getTextContent();
    }

    static Element appendElement(Document document, Element rootElement, String tagName, String data) {
        Element element = document.createElement(tagName);
        Text text = document.createTextNode(data);
        element.appendChild(text);
        rootElement.appendChild(element);
        return rootElement;
    }
}
