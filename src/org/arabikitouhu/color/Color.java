package org.arabikitouhu.color;

import org.lwjgl.opengl.GL11;

public class Color {
	//色16段階 ( COLOR_00・・・暗い COLOR_16・・・明るい )
	protected static final float c00 = 0f;
	protected static final float c01 = 1f / 16f;
	protected static final float c02 = 2f / 16f;
	protected static final float c03 = 3f / 16f;
	protected static final float c04 = 4f / 16f;
	protected static final float c05 = 5f / 16f;
	protected static final float c06 = 6f / 16f;
	protected static final float c07 = 7f / 16f;
	protected static final float c08 = 8f / 16f;
	protected static final float c09 = 9f / 16f;
	protected static final float c10 = 10f / 16f;
	protected static final float c11 = 11f / 16f;
	protected static final float c12 = 12f / 16f;
	protected static final float c13 = 13f / 16f;
	protected static final float c14 = 14f / 16f;
	protected static final float c15 = 15f / 16f;
	protected static final float c16 = 1f;




	public static final Color BLACK		= new Color(c00, c00, c00);
	public static final Color WHITE		= new Color(c16, c16, c16);
	public static final Color GLAY		= new Color(c08, c08, c08);
	public static final Color LIGHTGLAY	= new Color(c12, c12, c12);
	public static final Color DARKGLAY	= new Color(c04, c04, c04);
	public static final Color SILVER	= new Color(c14, c14, c14);

	private float r, g, b, a;

	public Color() {
		a = r = g = b = 1f;
	}

	public Color(float r, float g, float b) {
		this.r = r;
		this.g = g;
		this.b = b;
		a = 1f;
	}

	public void Using() {
		GL11.glColor4f(r, g, b, a);
	}

	public Color SetAlpha(float a) {
		this.a = a;
		return this;
	}

	public Color ToCopy() {
		Color color = new Color();
		color.r = this.r;
		color.g = this.g;
		color.b = this.b;
		color.a = this.a;
		return color;
	}
}
