package org.arabikitouhu.texture;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;

/**
 * テクスチャクラス
 * @author arabikitouhu
 * @version 0.0
 */
public class Texture {




	private static IntBuffer m_IDBuffer = BufferUtils.createIntBuffer(1);

	/** [内部]テクスチャIDの生成 */
	private static int createTextureID() {
		GL11.glGenTextures(m_IDBuffer);
		return m_IDBuffer.get(0);
	}

	public static void TextureGenerate(Texture texture) {
		int textureID = createTextureID();
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, textureID);

		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);

		GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA, texture.GetWidth_2Fold(), texture.GetHeight_2Fold(), 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, texture.m_buffer);

		GL11.glBindTexture(GL11.GL_TEXTURE, 0);

		texture.m_TextureID = textureID;
	}

	protected String m_Name;
	protected int m_TextureID;

	protected int m_Width;
	protected int m_Height;

	protected ByteBuffer m_buffer;

	/**
	 * コンストラクタ
	 * @param key 管理名
	 * @param textureID 割り振られたテクスチャID
	 * @param width 幅
	 * @param height 高さ
	 */
	public Texture(String key, ByteBuffer buffer, int width, int height) {
		m_TextureID = 0;
		m_Name = key;
		m_buffer = buffer;
		m_Width = width;
		m_Height = height;
	}

	private Texture(String key, int textureID, int width, int height) {
		m_TextureID = textureID;
		m_Name = key;
		m_buffer = null;
		m_Width = width;
		m_Height = height;
	}

	public Texture(Texture texture, ByteBuffer imageBuffer) {
		this(texture.m_Name, imageBuffer, texture.m_Width, texture.m_Height);
	}

	/** メモリクリア */
	public void Clear() {
		GL11.glDeleteTextures(m_TextureID);
	}

	/** 指定した数値の2乗数近似値を取得 */
	private static int Get2Fold(int fold) {
		int ret = 2;
		while (ret < fold) {
		ret *= 2;
		}
		return ret;
	}

	/** [GET]テクスチャID */
	public int GetTextureID() { return m_TextureID; }
	/** [GET]幅 */
	public int GetWidth() { return m_Width; }
	/** [GET]高さ */
	public int GetHeight() { return m_Height; }
	/** [GET]2乗数近似値の幅 */
	public int GetWidth_2Fold() { return Get2Fold(m_Width); }
	/** [GET]2乗数近似値の高さ */
	public int GetHeight_2Fold() { return Get2Fold(m_Height); }


	/** テクスチャのバインド */
	public void Bind() {
		if(m_TextureID == 0) {
			TextureGenerate(this);
		} else {
			GL11.glEnable(GL11.GL_TEXTURE_2D);
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, m_TextureID);
		}
	}

	/** テクスチャのバインド解放 */
	public void UnBind() {
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
		GL11.glDisable(GL11.GL_TEXTURE_2D);
	}

	/** 複製 */
	public Texture ToCopy() {
		Texture texture = new Texture(m_Name, m_TextureID, m_Width, m_Height);

		return texture;
	}

}
