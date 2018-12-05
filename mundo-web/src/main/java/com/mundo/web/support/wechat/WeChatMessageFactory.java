package com.mundo.web.support.wechat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;

/**
 * WeChatMessageFactory
 *
 * @author maodh
 * @since 2018/12/5
 */
public class WeChatMessageFactory {
    private static final Logger LOGGER = LoggerFactory.getLogger(WeChatMessageFactory.class);

    public static WeChatMessage newMessage(String xml) {
        final Element rootElement = WeChatXmlUtil.getRootElement(xml);
        final String toUserName = WeChatXmlUtil.getFirstNodeTextContentByTagName(rootElement, WeChatMessage.TO_USER_NAME);
        final String fromUserName = WeChatXmlUtil.getFirstNodeTextContentByTagName(rootElement, WeChatMessage.FROM_USER_NAME);
        final String createTime = WeChatXmlUtil.getFirstNodeTextContentByTagName(rootElement, WeChatMessage.CREATE_TIME);
        final String msgId = WeChatXmlUtil.getFirstNodeTextContentByTagName(rootElement, WeChatMessage.MSG_ID);
        final String msgType = WeChatXmlUtil.getFirstNodeTextContentByTagName(rootElement, WeChatMessage.MSG_TYPE);
        return new WeChatMessage(toUserName, fromUserName, Long.valueOf(createTime), Long.valueOf(msgId), WeChatMessageType.of(msgType));
    }

    public static WeChatTextMessage newTextMessage(String xml) {
        final Element rootElement = WeChatXmlUtil.getRootElement(xml);
        final WeChatMessage weChatMessage = WeChatMessageFactory.newMessage(xml);
        final String content = WeChatXmlUtil.getFirstNodeTextContentByTagName(rootElement, WeChatTextMessage.CONTENT);
        return new WeChatTextMessage(weChatMessage, content);
    }

    public static String newXmlByTextMessage(String toUserName, String fromUserName, String content) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = factory.newDocumentBuilder();
            Document document = docBuilder.newDocument();

            Element rootElement = document.createElement("xml");
            WeChatXmlUtil.appendElement(document, rootElement, WeChatMessage.TO_USER_NAME, toUserName);
            WeChatXmlUtil.appendElement(document, rootElement, WeChatMessage.FROM_USER_NAME, fromUserName);
            WeChatXmlUtil.appendElement(document, rootElement, WeChatMessage.CREATE_TIME, String.valueOf(System.currentTimeMillis() / 1000));
            WeChatXmlUtil.appendElement(document, rootElement, WeChatMessage.MSG_TYPE, WeChatMessageType.TEXT.toString());
            WeChatXmlUtil.appendElement(document, rootElement, WeChatTextMessage.CONTENT, content);
            document.appendChild(rootElement);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.ENCODING, StandardCharsets.UTF_8.name());
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
            StringWriter stringWriter = new StringWriter();
            transformer.transform(new DOMSource(document), new StreamResult(stringWriter));
            return stringWriter.toString();
        } catch (ParserConfigurationException | TransformerException e) {
            LOGGER.error(e.getMessage(), e);
            LOGGER.error("回复微信消息失败 toUserName={} fromUserName={} content={}", toUserName, fromUserName, content);
            return "success";
        }
    }
}
