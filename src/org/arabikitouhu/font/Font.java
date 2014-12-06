package org.arabikitouhu.font;

import org.arabikitouhu.event.object.RenderingEventObject;
import org.arabikitouhu.texture.Texture;
import org.lwjgl.opengl.GL11;

/**
 * フォントクラス
 * @author arabikitouhu
 * @version 0.0
 */
public class Font {

	protected String m_Name;
	protected Texture m_Texture;

	/**
	 * コンストラクタ(protected)
	 * @param name 管理名
	 * @param texture フォントスプライト群
	 */
	public Font(String name, Texture texture) {
		m_Name = name;
		m_Texture = texture;
	}

	/** メモリクリア */
	public void Clear() {
		m_Texture.Clear();
		m_Name = null;
	}

	/** [GET]管理名 */
	public String GetName() { return m_Name; }

	/**
	 * 描画処理
	 * @param x 左上X座標
	 * @param y 左上Y座標
	 * @param message 表示文字列
	 * @param state フォント属性クラス
	 */
	public void OnRender(float x, float y, String message, FontState state) {

		float dx = x, dy = y;
		int cntx = 1, cnty = 1;
//		// αブレンド設定
//		GL11.glEnable(GL11.GL_BLEND);
//		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

		m_Texture.Bind();
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);


		for(int i = 0; i < message.length(); i++) {
			char ww = message.charAt(i);

			// 必要な計算
			float w = (float)m_Texture.GetWidth_2Fold();
			float h = (float)m_Texture.GetHeight_2Fold();
			float s = Math.max(w, h) / 256f ;	//1文字のサイズ
			float ux = ((int)ww & 0xff) * s;		// 座標x軸
			float uy = ((int)ww / 256) * s;	// 座標y軸
			float u1 = ux / w;
			float u2 = (ux + s) / w;
			float v1 = uy / h;
			float v2 = (uy + s) / h;


			GL11.glPushMatrix();

//			GL11.glTranslatef(dx, dy, 0);

			state.GetColor().Using();

			if(state.GetStyle().GetBold()) {	//大文字表示
				RenderingEventObject.DrawTextureFace2D(
						new float[] { dx, dy, dx + state.m_FontSize * 1.75f, dy + state.m_FontSize * 1.75f }, new float[] { u1, v1, u2, v2 });
			} else {
				RenderingEventObject.DrawTextureFace2D(
						new float[] { dx, dy, dx + state.m_FontSize, dy + state.m_FontSize }, new float[] { u1, v1, u2, v2 });
			}

			GL11.glColor3f(1f, 1f, 1f);

			//位置調整
			if(state.GetStyle().GetDirection() == 0) {		//横書きの場合
				cntx++; dx += state.m_FontSize;
				if(state.m_WordLimit > 0 && cntx > state.m_WordLimit) {	//行替え判定
					cntx = 1; cnty++;
					dx = x + state.m_FontSize; dy += state.m_FontSize;
				}
			} else if(state.GetStyle().GetDirection() == 1) {	//縦書きの場合
				cnty++; dy += state.m_FontSize;
				if(state.m_WordLimit > 0 && cnty > state.m_WordLimit) {	//行替え判定
					cnty = 1; cntx++;
					dy = y + state.m_FontSize; dx += state.m_FontSize;
				}
			}

			GL11.glPopMatrix();


//			RenderingEventObject.DrawTextureFace2D(
//					new float[] { dx, dy, dx + state.m_FontSize, dy + state.m_FontSize }, new float[] { u1, v1, u2, v2 });
		}
		GL11.glDisable(GL11.GL_BLEND);
		m_Texture.UnBind();



//			Sprite sprite = m_SpriteArray[ww];
//
//			sprite.OnPreRender(dx, dy, state.m_FontSize, state.m_FontSize);
//
//			state.GetColor().Using();
//
//			if(state.GetStyle().GetBold()) {
//				sprite.OnRender(dx, dy, state.m_FontSize * 1.5f, state.m_FontSize * 1.5f);		//太文字表示？
//			} else {
//				sprite.OnRender(dx, dy, state.m_FontSize, state.m_FontSize);	//通常表示
//			}
//
//			sprite.OnPostRender(dx, dy, state.m_FontSize, state.m_FontSize);
//
//
//			//位置調整
//			if(state.GetStyle().GetDirection() == 0) {		//横書きの場合
//				cntx++; dx += state.m_FontSize * 2;
//				if(state.m_WordLimit > 0 && cntx > state.m_WordLimit) {	//行替え判定
//					cntx = 1; cnty++;
//					dx = x + state.m_FontSize; dy += state.m_FontSize * 2;
//				}
//			} else if(state.GetStyle().GetDirection() == 1) {	//縦書きの場合
//				cnty++; dy += state.m_FontSize * 2;
//				if(state.m_WordLimit > 0 && cnty > state.m_WordLimit) {	//行替え判定
//					cnty = 1; cntx++;
//					dy = y + state.m_FontSize; dx += state.m_FontSize * 2;
//				}
//			}
//		}
//		GL11.glDisable(GL11.GL_BLEND);
//		GL11.glColor3f(1f, 1f, 1f);

	}

	/** 複製 */
	public Font ToCopy() {
		Font font = new Font(this.m_Name, this.m_Texture);
		return font;
	}

}
