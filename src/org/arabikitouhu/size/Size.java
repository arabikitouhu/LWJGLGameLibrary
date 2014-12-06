package org.arabikitouhu.size;


public class Size {

	private int w;
	private int h;

	/** [GET]幅 */
	public int GetWidth() { return this.w; }
	/** [SET]幅 */
	public Size SetWidth(int value) { this.w = value; return this; }

	/** [GET]高さ */
	public int GetHeight() { return this.h; }
	/** [SET]高さ */
	public Size SetHeight(int value) { this.h = value; return this; }

	public Size(int width, int height) {
		this.w = width;
		this.h = height;
	}

	public SizeF GetFloat() {
		SizeF s = new SizeF(w, h);

		return s;
	}
}
