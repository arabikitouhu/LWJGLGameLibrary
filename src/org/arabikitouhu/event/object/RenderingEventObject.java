package org.arabikitouhu.event.object;

import org.arabikitouhu.font.FontState;
import org.arabikitouhu.window.WindowState;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

public class RenderingEventObject {

	private int m_Frame;
	private int m_DeltaTime;

	public RenderingEventObject(int frame, int deltaTime) {
		this.m_Frame = frame;
		this.m_DeltaTime = deltaTime;
	}

	public void Enable2D() {
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glPushMatrix();
		GL11.glLoadIdentity();
		GL11.glOrtho(0, WindowState.GetWidth(), WindowState.GetHeight(), 0, -1, 0);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glPushMatrix();
		GL11.glLoadIdentity();

		GL11.glDisable(GL11.GL_DEPTH_TEST);
		GL11.glDisable(GL11.GL_CULL_FACE);
		GL11.glEnable(GL11.GL_TEXTURE_2D);

	}

	public void Disable2D() {
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glPopMatrix();
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glPopMatrix();

		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glEnable(GL11.GL_CULL_FACE);
		GL11.glDisable(GL11.GL_TEXTURE_2D);


	}

	public void OnDrawFPS(String fontName, FontState fontState) {
//		ChangeDrowMode2D();

//		FontManager.Accessor().Get(fontName).
//			OnRender(0, 0, String.format("%.2f(%d)",1000.0f / this.m_DeltaTime, this.m_Frame), fontState);

		Display.setTitle(WindowState.GetTitle() + String.format("%.2f(%d)",1000.0f / this.m_DeltaTime, this.m_Frame));
	}

	public void DrawGrid1(float size) {

		float h = WindowState.GetHeight();
		GL11.glPushMatrix();
		GL11.glLoadIdentity();

		GL11.glLineWidth(size);
		GL11.glBegin(GL11.GL_LINES);
		//x軸
		GL11.glColor3f(1f, 0f, 0f);
		GL11.glVertex3f(0f, 0f, 0f);
		GL11.glVertex3f(h, 0f, 0f);
		//y軸
		GL11.glColor3f(0f, 1f, 0f);
		GL11.glVertex3f(0f, 0f, 0f);
		GL11.glVertex3f(0f, h, 0f);
		//z軸
		GL11.glColor3f(0f, 0f, 1f);
		GL11.glVertex3f(0f, 0f, 0f);
		GL11.glVertex3f(0f, 0f, h);
		GL11.glEnd();

		GL11.glColor3f(1f, 1f, 1f);
		GL11.glPopMatrix();
	}

	/**
	 * テクスチャ付面の描画(2D)
	 * @param v4 { xMin, yMin, xMax, yMax }
	 * @param t4 { uMin, vMin, uMax, vMax }
	 */
	public static void DrawTextureFace2D(float[] v4, float[] t4) {
		GL11.glBegin(GL11.GL_QUADS);

		GL11.glTexCoord2f(t4[0], t4[1]); GL11.glVertex2f(v4[0], v4[1]);
		GL11.glTexCoord2f(t4[2], t4[1]); GL11.glVertex2f(v4[2], v4[1]);
		GL11.glTexCoord2f(t4[2], t4[3]); GL11.glVertex2f(v4[2], v4[3]);
		GL11.glTexCoord2f(t4[0], t4[3]); GL11.glVertex2f(v4[0], v4[3]);

		GL11.glEnd();
	}
//	public void Draw(float size) {
//
//		float h = WindowStateObject.GetDisplayState().GetHeight();
//		GL11.glPushMatrix();
//		GL11.glLoadIdentity();
//
//		GL11.glLineWidth(size);
//		GL11.glBegin(GL11.GL_LINES);
//		//x軸
//		GL11.glColor3f(1f, 0f, 0f);
//		GL11.glVertex3f(0f, 0f, 0f);
//		GL11.glVertex3f(h, 0f, 0f);
//		//y軸
//		GL11.glColor3f(0f, 1f, 0f);
//		GL11.glVertex3f(0f, 0f, 0f);
//		GL11.glVertex3f(0f, h, 0f);
//		//z軸
//		GL11.glColor3f(0f, 0f, 1f);
//		GL11.glVertex3f(0f, 0f, 0f);
//		GL11.glVertex3f(0f, 0f, h);
//		GL11.glEnd();
//
//		GL11.glColor3f(1f, 1f, 1f);
//		GL11.glPopMatrix();
//	}

}
