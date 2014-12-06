package org.arabikitouhu.common;

import java.util.HashMap;
import java.util.Map;

/**
 * 文字列操作ヘルパークラス
 * @author arabikitouhu
 * @version 0.0
 */
public class StringHelper {

	/**
	 * 文字列→Int型への変換
	 * @param source 変換対象文字列
	 * @param def 変換失敗時の、戻り値
	 * @return 変換結果 (変換失敗時は、戻り値＝引数def)
	 */
	public static int ParseInt(String source, int def) {
		try {
			return Integer.parseInt(source);
		} catch (NumberFormatException e) {
			return def;
		}
	}

	/**
	 * 文字列→Float型への変換
	 * @param source 変換対象文字列
	 * @param def 変換失敗時の、戻り値
	 * @return 変換結果 (変換失敗時は、戻り値＝引数def)
	 */
	public static float ParseFloat(String source, float def) {
		try {
			return Float.parseFloat(source);
		} catch (NumberFormatException e) {
			return def;
		}
	}

	/**
	 * 文字列→Boolean型への変換
	 * @param source 変換対象文字列
	 * @param def 変換失敗時の、戻り値
	 * @return 変換結果 (変換失敗時は、戻り値＝引数def)
	 */
	public static boolean ParseBool(String source, boolean def) {
		try {
			return Boolean.parseBoolean(source);
		} catch (NullPointerException e) {
			return def;
		}
	}

	private static final Map<String, String> convMap = new HashMap<String, String>();

	static {
		convMap.put("a", "あ");		convMap.put("i", "い");		convMap.put("u", "う");		convMap.put("e", "え");		convMap.put("o", "お");
		convMap.put("xa", "ぁ");	convMap.put("xi", "ぃ");	convMap.put("xu", "ぅ");	convMap.put("xe", "ぇ");	convMap.put("xo", "ぉ");
		convMap.put("ka", "か");	convMap.put("ki", "き");	convMap.put("ku", "く");	convMap.put("ke", "け");	convMap.put("ko", "こ");
		convMap.put("sa", "さ");	convMap.put("si", "し");	convMap.put("su", "す");	convMap.put("se", "せ");	convMap.put("so", "そ");
									convMap.put("shi", "し");
		convMap.put("ta", "た");	convMap.put("ti", "ち");	convMap.put("tu", "つ");	convMap.put("te", "て");	convMap.put("to", "と");
									convMap.put("chi", "ち");	convMap.put("xtu", "っ");
		convMap.put("na", "な");	convMap.put("ni", "に");	convMap.put("nu", "ぬ");	convMap.put("ne", "ね");	convMap.put("no", "の");
		convMap.put("ha", "は");	convMap.put("hi", "ひ");	convMap.put("hu", "ふ");	convMap.put("he", "へ");	convMap.put("ho", "ほ");
																convMap.put("fu", "ふ");
		convMap.put("ma", "ま");	convMap.put("mi", "み");	convMap.put("mu", "む");	convMap.put("me", "め");	convMap.put("mo", "も");
		convMap.put("ya", "や");	convMap.put("yi", "い");	convMap.put("yu", "ゆ");	convMap.put("ye", "いぇ");	convMap.put("yo", "よ");
		convMap.put("xya", "ゃ");	convMap.put("xyi", "ぃ");	convMap.put("xyu", "ゅ");	convMap.put("xye", "ぇ");	convMap.put("xyo", "ょ");
		convMap.put("ra", "ら");	convMap.put("ri", "り");	convMap.put("ru", "る");	convMap.put("re", "れ");	convMap.put("ro", "ろ");
		convMap.put("wa", "わ");	convMap.put("wi", "うぃ");	convMap.put("wu", "う");	convMap.put("we", "うぇ");	convMap.put("wo", "を");

		convMap.put("n", "ん");		convMap.put("nn", "ん");

		convMap.put("ga", "が");	convMap.put("gi", "ぎ");	convMap.put("gu", "ぐ");	convMap.put("ge", "げ");	convMap.put("go", "ご");
		convMap.put("za", "ざ");	convMap.put("zi", "じ");	convMap.put("zu", "ず");	convMap.put("ze", "ぜ");	convMap.put("zo", "ぞ");
		convMap.put("da", "だ");	convMap.put("di", "ぢ");	convMap.put("du", "づ");	convMap.put("de", "で");	convMap.put("do", "ど");
		convMap.put("ba", "ば");	convMap.put("bi", "び");	convMap.put("bu", "ぶ");	convMap.put("be", "べ");	convMap.put("bo", "ぼ");
		convMap.put("pa", "ぱ");	convMap.put("pi", "ぴ");	convMap.put("pu", "ぷ");	convMap.put("pe", "ぺ");	convMap.put("po", "ぽ");

		convMap.put("kya", "きゃ");	convMap.put("kyu", "きゅ");	convMap.put("kyo", "きょ");

		convMap.put("sya", "しゃ");	convMap.put("syu", "しゅ");	convMap.put("syo", "しょ");
		convMap.put("sha", "しゃ");	convMap.put("shu", "しゅ");	convMap.put("sho", "しょ");

		convMap.put("tya", "ちゃ");	convMap.put("tyu", "ちゅ");	convMap.put("tyo", "ちょ");
		convMap.put("cya", "ちゃ");	convMap.put("cyu", "ちゅ");	convMap.put("cyo", "ちょ");
		convMap.put("cha", "ちゃ");	convMap.put("chu", "ちゅ");	convMap.put("cho", "ちょ");

		convMap.put("hya", "ひゃ");	convMap.put("hyu", "ひゅ");	convMap.put("hyo", "ひょ");

		convMap.put("rya", "りゃ");	convMap.put("ryu", "りゅ");	convMap.put("ryo", "りょ");

		convMap.put("gya", "ぎゃ");	convMap.put("gyu", "ぎゅ");	convMap.put("gyo", "ぎょ");

		convMap.put("zya", "じゃ");	convMap.put("zyu", "じゅ");	convMap.put("zyo", "じょ");

		convMap.put("dya", "ぢゃ");	convMap.put("dyu", "ぢゅ");	convMap.put("dyo", "ぢょ");

		convMap.put("bya", "びゃ");	convMap.put("byu", "びゅ");	convMap.put("byo", "びょ");

		convMap.put("pya", "ぴゃ");	convMap.put("pyu", "ぴゅ");	convMap.put("pyo", "ぴょ");

		convMap.put("!", "！");	convMap.put("\"", "”");	convMap.put("#", "＃");	convMap.put("$", "＄");	convMap.put("%", "％");	convMap.put("&", "＆");	convMap.put("'", "’");	convMap.put("(", "（");	convMap.put(")", "）");
								convMap.put("=", "＝");	convMap.put("~", "～");	convMap.put("|", "｜");

		convMap.put("1", "１");	convMap.put("2", "２");	convMap.put("3", "３");	convMap.put("4", "４");	convMap.put("5", "５");	convMap.put("6", "６");	convMap.put("7", "７");	convMap.put("8", "８");	convMap.put("9", "９");
		convMap.put("0", "０");	convMap.put("-", "ー");	convMap.put("^", "＾");	convMap.put("\\", "￥");

		convMap.put("@", "＠");	convMap.put("[", "「");	convMap.put("]", "」");	convMap.put(";", "；");	convMap.put(":", "：");	convMap.put(",", "、");	convMap.put(".", "。");	convMap.put("/", "・");
		convMap.put("`", "‘");	convMap.put("{", "｛");	convMap.put("}", "｝");	convMap.put("+", "＋");	convMap.put("*", "＊");	convMap.put("<", "＜");	convMap.put(">", "＞");	convMap.put("?", "？");	convMap.put("_", "＿");
	}

	/**
	 * ローマ字→ひらがな変換
	 * @return 空文字の場合は未変換
	 */
	 public static String ConvertFromEnglishToJapanese(String s) {
		 StringBuilder source = new StringBuilder(s.toLowerCase());
		 StringBuilder dst = new StringBuilder();
		 for ( int i = 0; i < source.length(); i++ ) {
			 if(i + 2 < source.length() && convMap.containsKey(source.substring(i, i + 3))) {
				 dst.append(convMap.get(source.substring(i, i + 3)));
				 i += 2;
			 } else if(i + 1 < source.length() && convMap.containsKey(source.substring(i, i + 2))) {
				 dst.append(convMap.get(source.substring(i, i + 2)));
				 i += 1;
			 } else if(convMap.containsKey(source.substring(i, i + 1))) {
				 dst.append(convMap.get(source.substring(i, i + 1)));
			 } else {
				 dst.append(source.substring(i, i + 1));
			 }
		 }

		 return dst.toString();
	 }

	 /** ローマ字入力モード */
	 public static final int IME_MODE_ROMA = 0;
	 /** かな字入力モード */
	 public static final int IME_MODE_KANA = 1;

	private static final Map<Integer, String> imeMap = new HashMap<Integer, String>();

	static {	//バックスラッシュはない
		imeMap.put(2, "1");
		imeMap.put(3, "2");
		imeMap.put(4, "3");
		imeMap.put(5, "4");
		imeMap.put(6, "5");
		imeMap.put(7, "6");
		imeMap.put(8, "7");
		imeMap.put(9, "8");
		imeMap.put(10, "9");
		imeMap.put(11, "0");
		imeMap.put(12, "-");
		imeMap.put(40, "^");	//米
		imeMap.put(43, "\\");

		imeMap.put(16, "q");
		imeMap.put(17, "w");
		imeMap.put(18, "e");
		imeMap.put(19, "r");
		imeMap.put(20, "t");
		imeMap.put(21, "y");
		imeMap.put(22, "u");
		imeMap.put(23, "i");
		imeMap.put(24, "o");
		imeMap.put(25, "p");
		imeMap.put(41, "@");	//米
		imeMap.put(26, "[");

		imeMap.put(30, "a");
		imeMap.put(31, "s");
		imeMap.put(32, "d");
		imeMap.put(33, "f");
		imeMap.put(34, "g");
		imeMap.put(35, "h");
		imeMap.put(36, "j");
		imeMap.put(37, "k");
		imeMap.put(38, "l");
		imeMap.put(13, ";");	//米
		imeMap.put(146, ":");
		imeMap.put(27, "]");

		imeMap.put(44, "z");
		imeMap.put(45, "x");
		imeMap.put(46, "c");
		imeMap.put(47, "v");
		imeMap.put(48, "b");
		imeMap.put(49, "n");
		imeMap.put(50, "m");
		imeMap.put(51, ",");
		imeMap.put(52, ".");
		imeMap.put(53, "/");
		imeMap.put(0, "\\");

		//SHIFT中
		imeMap.put(258, "!");
		imeMap.put(259, "\"");
		imeMap.put(260, "#");
		imeMap.put(261, "$");
		imeMap.put(262, "%");
		imeMap.put(263, "&");
		imeMap.put(264, "'");
		imeMap.put(265, "(");
		imeMap.put(266, ")");

		imeMap.put(268, "=");
		imeMap.put(296, "~");
		imeMap.put(299, "|");

		imeMap.put(272, "Q");
		imeMap.put(273, "W");
		imeMap.put(274, "E");
		imeMap.put(275, "R");
		imeMap.put(276, "T");
		imeMap.put(277, "Y");
		imeMap.put(278, "U");
		imeMap.put(279, "I");
		imeMap.put(280, "O");
		imeMap.put(281, "P");
		imeMap.put(297, "`");
		imeMap.put(282, "{");

		imeMap.put(286, "A");
		imeMap.put(287, "S");
		imeMap.put(288, "D");
		imeMap.put(289, "F");
		imeMap.put(290, "G");
		imeMap.put(291, "H");
		imeMap.put(292, "J");
		imeMap.put(293, "K");
		imeMap.put(294, "L");
		imeMap.put(269, "+");
		imeMap.put(402, "*");
		imeMap.put(283, "}");

		imeMap.put(300, "Z");
		imeMap.put(301, "X");
		imeMap.put(302, "C");
		imeMap.put(303, "V");
		imeMap.put(304, "B");
		imeMap.put(305, "N");
		imeMap.put(306, "M");
		imeMap.put(307, "<");
		imeMap.put(308, ">");
		imeMap.put(309, "?");
		imeMap.put(256, "_");
	}


	 public static String GetIMEfromKeyCode(int i) {
		 String word = imeMap.get(i);
		return word;
	}
}
