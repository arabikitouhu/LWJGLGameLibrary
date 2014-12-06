package org.arabikitouhu.loader;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.ComponentColorModel;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferByte;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Hashtable;

import javax.swing.ImageIcon;

import org.arabikitouhu.common.LogStream;
import org.arabikitouhu.texture.Texture;


/**
 * テクスチャ読み込み
 * @author arabikitouhu
 * @version 0.0
 * @extends Manager
 */
@SuppressWarnings("rawtypes")
public final class TextureLoader {

	private static final LogStream logger = new LogStream("TextureLoader");

	private static ColorModel m_ColorModel_Alpha
		= new ComponentColorModel(ColorSpace.getInstance(ColorSpace.CS_sRGB), new int[] {8,8,8,8}, true, false, ComponentColorModel.TRANSLUCENT, DataBuffer.TYPE_BYTE);
	private static ColorModel m_ColorModel_NoneAlpha
		= new ComponentColorModel(ColorSpace.getInstance(ColorSpace.CS_sRGB), new int[] {8,8,8,0}, false, false, ComponentColorModel.OPAQUE, DataBuffer.TYPE_BYTE);
//	private static IntBuffer m_IDBuffer = BufferUtils.createIntBuffer(1);

//	/** [内部]テクスチャIDの生成 */
//	private static int createTextureID() {
//		GL11.glGenTextures(m_IDBuffer);
//		return m_IDBuffer.get(0);
//	}

	/**
	 * [内部]イメージデータの読込
	 * @param filename ファイル名
	 * @return BufferedImage
	 */
	protected static BufferedImage loadImage(String filename) {
		Image img = new ImageIcon(filename).getImage();
		BufferedImage bufferedImage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);
		Graphics g = bufferedImage.getGraphics();
		g.drawImage(img, 0, 0, null);
		g.dispose();
		return bufferedImage;
	}

	/**
	 * [内部]イメージデータを2の乗数サイズに整形する
	 * @param bufferedImage 整形元のイメージデータ
	 * @param texture テクスチャクラス
	 * @return 成型後イメージデータ
	 */
	protected static Texture convertImage(BufferedImage bufferedImage, Texture texture) {
		ByteBuffer imageBuffer;
		WritableRaster raster;
		BufferedImage texImage;
		int texWidth = texture.GetWidth_2Fold();
		int texHeight = texture.GetHeight_2Fold();

		if (bufferedImage.getColorModel().hasAlpha()) {
			raster = Raster.createInterleavedRaster(DataBuffer.TYPE_BYTE, texWidth, texHeight, 4, null);
			texImage = new BufferedImage(m_ColorModel_Alpha, raster, false, new Hashtable());
		} else {
			raster = Raster.createInterleavedRaster(DataBuffer.TYPE_BYTE, texWidth, texHeight, 3, null);
			texImage = new BufferedImage(m_ColorModel_NoneAlpha, raster, false, new Hashtable());
		}

		Graphics g = texImage.getGraphics();
		g.setColor(new Color(0f,0f,0f,0f));
		g.fillRect(0,0,texWidth,texHeight);
		g.drawImage(bufferedImage,0,0,null);

		byte[] data = ((DataBufferByte) texImage.getRaster().getDataBuffer()).getData();
		imageBuffer = ByteBuffer.allocateDirect(data.length);
		imageBuffer.order(ByteOrder.nativeOrder());
		imageBuffer.put(data, 0, data.length);
		imageBuffer.flip();

		return new Texture(texture, imageBuffer);
	}

	public static Texture Import(String filename) {
		// 無い場合・・・読み込む

		//画像読み込み
		BufferedImage bufferedImage = loadImage(filename);

		Texture texture = convertImage(bufferedImage, new Texture(filename, null, bufferedImage.getWidth(), bufferedImage.getHeight()));

		logger.GetStream().info(String.format("テクスチャ「%s」を取り込みました。", filename));

		return texture;
	}

}
