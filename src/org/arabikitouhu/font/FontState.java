package org.arabikitouhu.font;

import org.arabikitouhu.color.Color;

/**
 * フォント属性クラス
 * @author arabikitouhu
 * @version 0.0
 */
public class FontState {

	//フォントサイズ
	public static final float FONTSIZE_16 = 16f;
	public static final float FONTSIZE_24 = 24f;
	public static final float FONTSIZE_32 = 32f;

	public float m_FontSize;	//フォントサイズ

	protected Color m_Color;
	public FontState SetColor(Color color) { m_Color = color; return this; }
	public Color GetColor() { return m_Color; }

	protected FontStyle m_FontStyle;
	public FontState SetStyle(FontStyle style) { m_FontStyle = style; return this; }
	public FontStyle GetStyle() { return m_FontStyle; }

	public int m_WordLimit;		//１行あたりの制限文字数


	public FontState() {
		m_FontSize = 16f;
		m_Color = Color.WHITE;
		m_FontStyle = new FontStyle();
		m_WordLimit = 0;

	}

	public FontState ToCopy() {
		FontState state = new FontState();
		state.m_FontSize = this.m_FontSize;
		state.m_Color = this.m_Color.ToCopy();
		state.m_FontStyle = this.m_FontStyle.ToCopy();
		state.m_WordLimit = this.m_WordLimit;
		return state;
	}

}
