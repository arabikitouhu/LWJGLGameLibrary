package org.arabikitouhu.window;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

import org.arabikitouhu.common.StringHelper;
import org.arabikitouhu.common.XmlReader;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * ウィンドウ情報クラス
 * @author arabikitouhu
 * @version 0.0
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public final class WindowState {

	private static String title = "サンプル";
	private static ByteBuffer[] icon = null;
	private static boolean fullScreen = false;
	private static boolean debug = true;

	private static int displayIndex = 0;
	private static List displayList = new ArrayList();

//	static {
//		SetDisplayIndex(640, 480);
//		SetDisplayIndex(800, 600);
//		SetDisplayIndex(1024, 768);
//		SetDisplayIndex(1280, 720);
//		SetDisplayIndex(1280, 960);
//		SetDisplayIndex(1920, 1080);
//	}

	static class Disp {
		public int width;
		public int height;
		public DisplayMode mode;
	}
	private static void SetDisplayIndex(int width, int height) {
		Disp disp = GetDisplayIndex(width, height);
		if(disp != null) {
			disp.width = width;
			disp.height = height;
			displayList.add(disp);
		}
	}
	private static Disp GetDisplayIndex(int width, int height) {
		try {
			DisplayMode[] modes = Display.getAvailableDisplayModes();
			for(DisplayMode mode : modes) {
				if(mode.getWidth() == width && mode.getHeight() == height && mode.getBitsPerPixel() == 32 && mode.getFrequency() == 60 && mode.isFullscreenCapable()) {
					Disp disp = new Disp();
					disp.mode = mode;
					return disp;
				}
			}
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
		return null;
	}
	/** width x height (windowmode) Vsync状態のディスプレイ */
	private static void DisplayInitialize() {
		try {
			Display.setTitle(title);
			Display.setDisplayMode(((Disp)displayList.get(displayIndex)).mode);
			Display.setFullscreen(fullScreen);

			if(icon != null) {
				Display.setIcon(icon);
			}
			Display.setVSyncEnabled(true);	//垂直同期？
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
	}

	/** OpenGL初期化 */
	private static void OpenGLInitialize() {
		GL11.glEnable(GL11.GL_CULL_FACE);
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glCullFace(GL11.GL_BACK);

		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
//		GL11.glFrustum(-(width / 2.0d), width / 2.0d, -(height / 2.0d), height / 2.0d, 0.1d, 100.0d);
		Disp disp = (Disp)displayList.get(displayIndex);
		float w = disp.width;
		float h = disp.height;
		float r = (float)Math.sqrt(w * w + h * h);
		GLU.gluPerspective(45.0f, w / h, 0.1f, r);
		GL11.glViewport(0, 0, disp.width, disp.height);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glLoadIdentity();
	}

	public static boolean IsFullScreen() { return fullScreen; }

	public static String GetTitle() { return title; }

	public static ByteBuffer[] GetIcon() { return icon; }

	public static boolean IsDebug() { return debug; }

	public static void SetFullScreen(boolean value) { fullScreen = value; }

	public static void SetTitle(String value) { title = value; }

	public static void SetIcon(String filename) {
		Image img = new ImageIcon(filename).getImage();
		BufferedImage iconn = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB_PRE);
		Graphics2D g = iconn.createGraphics();
		g.drawImage(img, 0, 0, img.getWidth(null), img.getHeight(null), null);
		g.dispose();

		byte[] buffer = new byte[iconn.getWidth() * iconn.getHeight() * 4];
		int counter = 0;
		for (int i = 0; i < iconn.getHeight(); i++)
			for (int j = 0; j < iconn.getWidth(); j++) {
				int colorSpace = iconn.getRGB(j, i);
				buffer[counter + 0] = (byte) ((colorSpace << 8) >> 24);
				buffer[counter + 1] = (byte) ((colorSpace << 16) >> 24);
				buffer[counter + 2] = (byte) ((colorSpace << 24) >> 24);
				buffer[counter + 3] = (byte) (colorSpace >> 24);
				counter += 4;
			}

		icon = new ByteBuffer[ ] { ByteBuffer.wrap(buffer) };
	}

	public static void SetDebug(boolean value) { debug = value; }

	/**
	 * ウィンドウ情報の読込(*.xml)
	 * @param filename ファイル名
	 */
	public static void ReadFile(String filename) {
		XmlReader reader = new XmlReader(filename);

		if(reader.GetRootName().equals("game")) {
			for(int i=0; i < reader.GetChildren().getLength(); i++) {
				Node node = reader.GetChildren().item(i);

				if (node.getNodeType() == Node.ELEMENT_NODE && node.getNodeName().equals("window")) {
					Element element = (Element)node;

					if(element.hasAttribute("title")) { SetTitle(element.getAttribute("title")); }
					if(element.hasAttribute("icon")) {
						String name = element.getAttribute("icon");
						SetIcon(name);
					}
					if(element.hasAttribute("display")) { displayIndex = StringHelper.ParseInt(element.getAttribute("display"), 0); }
					if(element.hasAttribute("fullscreen")) { SetFullScreen(StringHelper.ParseBool(element.getAttribute("fullscreen"), false)); }
					if(element.hasAttribute("debug")) { SetDebug(StringHelper.ParseBool(element.getAttribute("debug"), false)); }

				}
			}
		}
	}


	public static void Initialize() {

		SetDisplayIndex(640, 480);
		SetDisplayIndex(800, 600);
		SetDisplayIndex(1024, 768);
		SetDisplayIndex(1280, 720);
		SetDisplayIndex(1280, 960);
		SetDisplayIndex(1920, 1080);


		DisplayInitialize();
		OpenGLInitialize();
	}

	public static float GetAspect(float w, float h) {
		return w / h;
	}

	public static int GetWidth() {
		return ((Disp)displayList.get(displayIndex)).width;
	}

	public static int GetHeight() {
		return ((Disp)displayList.get(displayIndex)).height;
	}

}
