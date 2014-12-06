package org.arabikitouhu.size;


public class SizeF {

	private float w;
	private float h;

	/** [GET]幅 */
	public float GetWidth() { return this.w; }
	/** [SET]幅 */
	public SizeF SetWidth(float value) { this.w = value; return this; }

	/** [GET]高さ */
	public float GetHeight() { return this.h; }
	/** [SET]高さ */
	public SizeF SetHeight(float value) { this.h = value; return this; }

	public SizeF(float width, float height) {
		this.w = width;
		this.h = height;
	}

	public Size GetInt() {
		Size s = new Size((int)w, (int)h);

		return s;
	}
}
