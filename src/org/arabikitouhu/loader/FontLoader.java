package org.arabikitouhu.loader;

import org.arabikitouhu.common.LogStream;
import org.arabikitouhu.font.Font;
import org.arabikitouhu.font.FontState;

/**
 * フォント読み込みクラス
 * @author arabikitouhu
 * @version 0.0
 */
public final class FontLoader {

	private static final LogStream logger = new LogStream("FontLoader");


	public static Font Import(String filename) {
//		"./data/texture/font.png"
		Font font = new Font(filename, TextureLoader.Import(filename));
		logger.GetStream().info(String.format("フォント「%s」を取り込みました。", filename));

		if(defFont == null) {
			defFont = font;
		}

		return font;
	}


	private static Font defFont = null;

	public static Font GetDefaultFont() { return defFont; }

	public static void SetDefaultFont(Font font) { defFont = font; }

	private static FontState defFontState = new FontState();

	public static FontState GetDefaultFontState() { return defFontState; }

	public static void SetDefaultFontState(FontState value) { defFontState = value; }
}
