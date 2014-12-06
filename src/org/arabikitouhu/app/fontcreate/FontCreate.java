package org.arabikitouhu.app.fontcreate;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.ComponentColorModel;
import java.awt.image.DataBuffer;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Hashtable;

import javax.imageio.ImageIO;

import org.arabikitouhu.common.LogStream;

/**
 * フォントデータ作成
 * @author arabikitouhu
 * @version 0.0
 */
public class FontCreate {

	private static final LogStream logger = new LogStream("FontCreate");

	public static void main(String[] args) {
		String filename = "";
		String dir = "result/";

		float size = 32f;

		for(int i = 0; i < args.length; i++) {
			if(args[i].equals("-s")) { size = Float.parseFloat(args[++i]);
			} else if(args[i].equals("-f")) { filename = args[++i];
			}
		}

		CreateFont(filename, dir, size);
	}

	/**
	 * [内部]フォントテクスチャの生成<br>
	 * 「./data/texture/font/$key$/」フォルダ配下に生成する。
	 * @param key 管理用フォント名
	 * @param filename フォントファイル名
	 */
	public static void CreateFont(String filename, String dir, float size) {

		File file = new File(dir);
		file.mkdirs();	// フォルダを作成

		Font fontF = null;

		try {
			fontF = Font.createFont(java.awt.Font.TRUETYPE_FONT, new FileInputStream(filename)).deriveFont(size);
		} catch (FontFormatException | IOException e) {
			logger.GetStream().warning(String.format("フォントファイルが間違っています。FileName : %s", filename));
			e.printStackTrace();
		}

		float pix = size * 256;
		BufferedImage image = CreateImage((int)pix, (int)pix);
		Graphics2D g = image.createGraphics();

		g.setColor(new Color(0f, 0f, 0f, 0f));
		g.fillRect(0, 0, (int)pix, (int)pix);
		g.setFont(fontF);
		g.setColor(Color.WHITE);
//		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		for(int j = 0; j < 256; j++) {
			for(int k = 0; k < 256; k++) {
				String message = null;
				try {
					message = new String(new byte[] {(byte)j, (byte)k}, "UNICODE");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				g.drawString(message, size * k, size * 0.75f + size * j);
			}
		}
		try {
			ImageIO.write(image, "PNG", new FileOutputStream(String.format("%s/font.png", dir)));
			logger.GetStream().info(String.format("フォントテクスチャを作成しました。FileName : %s", String.format("%s/font.png", dir)));
		} catch (IOException e) {
			e.printStackTrace();
		}

//		for(int i = 0; i < 256; i++) {
//			BufferedImage image = CreateImage((int)size * 16, (int)size * 16);
//			Graphics2D g = image.createGraphics();
//
//			g.setColor(new Color(0f, 0f, 0f, 0f));
//			g.fillRect(0, 0, (int)size * 16, (int)size * 16);
//
//			g.setFont(fontF);
//			g.setColor(Color.WHITE);
//			g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//
//			for(int j = 0; j < 16; j++) {
//				for(int k = 0; k < 16; k++) {
//					String message = null;
//					try {
//						message = new String(new byte[] {(byte)i, (byte)(j * 16 + k)}, "UNICODE");
//					} catch (UnsupportedEncodingException e) {
//						e.printStackTrace();
//					}
//					g.drawString(message, size * k, size * 0.75f + size * j);
//				}
//			}
//
//			try {
//				ImageIO.write(image, "PNG", new FileOutputStream(String.format("%s/%d.png", dir, i)));
//				logger.GetStream().info(String.format("フォントテクスチャを作成しました。FileName : %s", String.format("%s/%d.png", dir, i)));
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
	}

	@SuppressWarnings("rawtypes")
	public static BufferedImage CreateImage(int width, int height) {
		ColorModel m_ColorModel_Gray = new ComponentColorModel(ColorSpace.getInstance(ColorSpace.CS_GRAY), new int[] {8, 8}, true, false, ComponentColorModel.TRANSLUCENT, DataBuffer.TYPE_BYTE);

		WritableRaster  raster = Raster.createInterleavedRaster(DataBuffer.TYPE_BYTE, width, height, 2, null);
		BufferedImage   bufferedImage = new BufferedImage(m_ColorModel_Gray, raster, true, new Hashtable());

		return bufferedImage;
	}
}