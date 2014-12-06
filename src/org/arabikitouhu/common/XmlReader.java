package org.arabikitouhu.common;

import java.io.FileInputStream;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * XMLリーダークラス
 * @author arabikitouhu
 * @version 0.0
 */
public class XmlReader {

	private Document m_Document;
	private Element m_Root;
	private NodeList m_Children;

	/**
	 * コンストラクト
	 * @param filename ファイル名(*.xml)
	 */
	public XmlReader(String filename) {

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder;
		try {
			documentBuilder = factory.newDocumentBuilder();
			m_Document = documentBuilder.parse(new FileInputStream(filename));


			m_Root = m_Document.getDocumentElement();
			m_Children = m_Root.getChildNodes();
		} catch (ParserConfigurationException e1) {
			e1.printStackTrace();
		} catch (SAXException | IOException e) {
			e.printStackTrace();
		}

	}

	/** [GET]ルートノード */
	public Element GetRoot() { return m_Root; }
	/** [GET]ルートノード名 */
	public String GetRootName() { return GetRoot().getNodeName(); }
	/** [GET]ルートノード アトリビュート値 */
	public String GetRootAttribute(String attribute) { return GetRoot().getAttribute(attribute); }

	/** [GET]子ノード */
	public NodeList GetChildren() { return m_Children; }
}
