package org.arabikitouhu.font;

public class FontStyle {

//	/** [OPTION]縦書き */
//	public static final int DIRECTION_VERTICAL = 1;	//縦書き
//	/** [OPTION]横書き */
//	public static final int DIRECTION_HORIZONTAL = 0;	//横書き

	protected int m_Flag_DIRECTION;
	protected boolean m_Bold;


	public FontStyle() {
		m_Flag_DIRECTION = 0;
		m_Bold = false;
	}

	public FontStyle SetVertical() { m_Flag_DIRECTION = 1; return this; }
	public FontStyle SetHorizontal() { m_Flag_DIRECTION = 0; return this; }
	public int GetDirection() { return m_Flag_DIRECTION; }
	public FontStyle SetBold(boolean value) { m_Bold = value; return this; }
	public boolean GetBold() { return m_Bold; }

	public FontStyle ToCopy() {
		FontStyle style = new FontStyle();
		style.m_Flag_DIRECTION = this.m_Flag_DIRECTION;
		style.m_Bold = this.m_Bold;
		return style;
	}
}
