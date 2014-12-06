//package org.arabikitouhu.window;
//
//import java.nio.ByteBuffer;
//
//import org.lwjgl.LWJGLException;
//import org.lwjgl.opengl.Display;
//import org.lwjgl.opengl.DisplayMode;
//import org.lwjgl.opengl.GL11;
//import org.lwjgl.util.glu.GLU;
//
//public class DisplayState {
//
//	private int index;
//	private int width;
//	private int height;
//	private int bpp;
//	private int fps;
//
//	public DisplayState(int index, DisplayMode mode) {
//		this.index = index;
//
//		width = mode.getWidth();
//		height = mode.getHeight();
//		bpp = mode.getBitsPerPixel();
//		fps = mode.getFrequency();
//	}
//
//	public int GetWidth() { return width; }
//
//	public int GetHeight() { return height; }
//
//	public int GetBPP() { return bpp; }
//
//	public int GetFPS() { return fps; }
//
//
//
//	/** width x height (windowmode) Vsync状態のディスプレイ */
//	public void DisplayInitialize(String title, ByteBuffer[] icons, boolean isFullScreen) {
//		try {
//			Display.setTitle(title);
//			Display.setDisplayMode(Display.getAvailableDisplayModes()[index]);
//			Display.setFullscreen(isFullScreen);
//			if(icons != null) {
//				Display.setIcon(icons);
//			}
//			Display.setVSyncEnabled(true);	//垂直同期？
//			Display.create();
//		} catch (LWJGLException e) {
//			e.printStackTrace();
//		}
//	}
//
//	/** OpenGL初期化 */
//	public void OpenGLInitialize() {
//		GL11.glEnable(GL11.GL_CULL_FACE);
//		GL11.glEnable(GL11.GL_DEPTH_TEST);
//		GL11.glCullFace(GL11.GL_BACK);
//
//		GL11.glMatrixMode(GL11.GL_PROJECTION);
//		GL11.glLoadIdentity();
////		GL11.glFrustum(-(width / 2.0d), width / 2.0d, -(height / 2.0d), height / 2.0d, 0.1d, 100.0d);
//		float w = WindowStateObject.GetDisplayState().GetWidth();
//		float h = WindowStateObject.GetDisplayState().GetHeight();
//		float r = (float)Math.sqrt(w * w + h * h);
//		GLU.gluPerspective(45.0f, WindowStateObject.GetAspect(), 0.1f, r);
//		GL11.glViewport(0, 0, width, height);
//		GL11.glMatrixMode(GL11.GL_MODELVIEW);
//		GL11.glLoadIdentity();
//	}
//
//}
