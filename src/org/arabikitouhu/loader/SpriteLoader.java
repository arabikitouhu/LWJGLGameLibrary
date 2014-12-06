package org.arabikitouhu.loader;

import org.arabikitouhu.common.LogStream;
import org.arabikitouhu.common.StringHelper;
import org.arabikitouhu.common.XmlReader;
import org.arabikitouhu.sprite.Sprite;
import org.arabikitouhu.texture.Texture;
import org.w3c.dom.Element;
import org.w3c.dom.Node;


/**
 * スプライト取り込みクラス
 * @author arabikitouhu
 * @version 0.0
 * @extends Manager
 */
public final class SpriteLoader {

	private static final LogStream logger = new LogStream("SpriteLoader");

//	@Deprecated
	public static Sprite Import(String filename) {
		XmlReader reader = new XmlReader(filename);
		String texName = reader.GetRootAttribute("texture");
		Texture texture = TextureLoader.Import(texName);

		Sprite sprite = new Sprite(texture);

		for(int i=0; i < reader.GetChildren().getLength(); i++) {
			Node node = reader.GetChildren().item(i);

			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element element = (Element)node;

				String name = element.getAttribute("name");
				float x = StringHelper.ParseFloat(element.getAttribute("x"), 0f);
				float y = StringHelper.ParseFloat(element.getAttribute("y"), 0f);
				float w = StringHelper.ParseFloat(element.getAttribute("w"), 0f);
				float h = StringHelper.ParseFloat(element.getAttribute("h"), 0f);

				sprite.Add(name, x, y, w, h);
				logger.GetStream().info("スプライト「" + name + "」を取り込みました。");
			}
		}

		return sprite;
/*
		TextureAccessor texAccessor = TextureManager.Accessor();

		try {

			String texName = reader.GetRootAttribute("texture");

			Texture texture = TextureLoader.Import(texName);
					if(texAccessor.Has(texName)) {
						String[] attrStr = new String[] { "name", "x", "y", "w", "h" };

						for(int i=0; i < reader.GetChildren().getLength(); i++) {
							Node node = reader.GetChildren().item(i);

							if (node.getNodeType() == Node.ELEMENT_NODE) {
								Element element = (Element)node;

								for(String str : attrStr) {
									if(!element.hasAttribute(str)) {
										throwMSG = "<Sprite>に「name」「x」「y」「w」「h」の内、何れかのアトリビュートが含まれていません。";
										throw new DataFormatException();
									}
								}

								String name = element.getAttribute("name");
								float x = StringHelper.ParseFloat(element.getAttribute("x"), 0f);
								float y = StringHelper.ParseFloat(element.getAttribute("y"), 0f);
								float w = StringHelper.ParseFloat(element.getAttribute("w"), 0f);
								float h = StringHelper.ParseFloat(element.getAttribute("h"), 0f);

								if(texAccessor.Has(texName)) {
									m_MapSprite.put(String.format("%s.%s", texName, name), new Sprite(texAccessor.Get(texName), x, y, w, h));	//スプライトの登録
									m_Logger.Info(String.format("スプライト「%s.%s」を登録しました。", texName, name));
								} else {
									throwMSG = "テクスチャ「" + texName + "」が存在しません。";
									throw new IllegalArgumentException();
								}
							}
						}

					} else {
						throwMSG = "テクスチャ「" + texName + "」が存在しません。";
						throw new IllegalArgumentException();
					}

				} else {
					throwMSG = "<Sprites>に「texture」のアトリビュートが含まれていません。";
					throw new DataFormatException();
				}
			} else {
				throwMSG = "ルートが<Sprites>で始まっていません。";
				throw new DataFormatException();
			}
		} catch (DataFormatException e) {
			m_Logger.Warning(String.format("スプライトファイルの形式不一致が発生。FileName : %s 原因：%s", filename, throwMSG));
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			m_Logger.Warning(String.format("スプライトファイルのデータミス。FileName : %s 原因：%s", filename, throwMSG));
			e.printStackTrace();
		}*/
	}
}