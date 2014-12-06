package org.arabikitouhu.sprite;

import java.util.HashMap;
import java.util.Map;

import org.arabikitouhu.point.Point;
import org.arabikitouhu.point.PointF;
import org.arabikitouhu.rectangle.Rectangle;
import org.arabikitouhu.rectangle.RectangleF;
import org.arabikitouhu.size.Size;
import org.arabikitouhu.size.SizeF;
import org.arabikitouhu.texture.Texture;
import org.lwjgl.opengl.GL11;

//MOMO

//x軸は通常
//y軸は↑が＋
//z軸は手前が＋




/**
 * スプライトクラス
 * @author arabikitouhu
 * @version 0.0
 */
public class Sprite {

	private Texture m_Texture;
	private float mw;
	private float mh;

	class StractData {
		public float w;
		public float h;
		public float x1;
		public float y1;
		public float x2;
		public float y2;
	}

	private Map<String, StractData> sheetMap = new HashMap<String, StractData>();

	/** コンストラクタ
	 * @param texture テクスチャクラス
	 */
	public Sprite(Texture texture) {
		m_Texture = texture;
		mw = m_Texture.GetWidth_2Fold();
		mh = m_Texture.GetHeight_2Fold();

		Add("@", 0f, 0f, mw, mh);
	}

	public void Add(String key, float x, float y, float w, float h) {

		StractData data = new StractData();
		data.x1 = x / mw;
		data.y1 = y / mh;
		data.x2 = (x + w) / mw;
		data.y2 = (y + h) / mh;

		data.w = w;
		data.h = h;

		this.sheetMap.put(key, data);
	}

	public void OnRendering(String key, float x, float y, float w, float h) {
		OnPreRender();
		OnRender(key, x, y, w, h);
		OnPostRender();
	}
	public void OnRendering(String key, float x, float y) {
		StractData data = sheetMap.get(key);
		if(data != null) {
			OnRendering(key, x, y, data.w, data.h);
		}

	}
	public void OnRendering(String key, Point point) {
		OnRendering(key, point.GetX(), point.GetY());
	}
	public void OnRendering(String key, PointF point) {
		OnRendering(key, point.GetX(), point.GetY());
	}
	public void OnRendering(String key, Point point, Size size) {
		OnRendering(key, point.GetX(), point.GetY(), size.GetWidth(), size.GetHeight());
	}
	public void OnRendering(String key, PointF point, SizeF size) {
		OnRendering(key, point.GetX(), point.GetY(), size.GetWidth(), size.GetHeight());
	}
	public void OnRendering(String key, Rectangle rect) {
		OnRendering(key, rect.GetPoint(), rect.GetSize());
	}
	public void OnRendering(String key, RectangleF rect) {
		OnRendering(key, rect.GetPointF(), rect.GetSizeF());
	}

	public void OnRender(String key, float x, float y, float w, float h) {

		StractData data = sheetMap.get(key);
		if(data != null) {
			GL11.glTexCoord2f(data.x1, data.y1);
			GL11.glVertex2f(x, y);
			GL11.glTexCoord2f(data.x2, data.y1);
			GL11.glVertex2f(x + w, y);
			GL11.glTexCoord2f(data.x2, data.y2);
			GL11.glVertex2f(x + w, y + h);
			GL11.glTexCoord2f(data.x1, data.y2);
			GL11.glVertex2f(x, y + h);
		}

	}

	public void OnPreRender() {

		GL11.glPushMatrix();

		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		m_Texture.Bind();

		GL11.glBegin(GL11.GL_QUADS);

	}

	public void OnPostRender() {

		GL11.glEnd();

		m_Texture.UnBind();
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glPopMatrix();
	}

}
