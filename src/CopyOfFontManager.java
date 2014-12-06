//
//
//import java.awt.Color;
//import java.awt.FontFormatException;
//import java.awt.Graphics2D;
//import java.awt.RenderingHints;
//import java.awt.image.BufferedImage;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.UnsupportedEncodingException;
//import java.util.HashMap;
//import java.util.Map.Entry;
//import java.util.Queue;
//import java.util.concurrent.ArrayBlockingQueue;
//
//import javax.imageio.ImageIO;
//
//import org.arabikitouhu.common.LogStream;
//import org.arabikitouhu.common.Manager;
//import org.arabikitouhu.component.Component;
//import org.arabikitouhu.event.object.UpdateEventObject;
//import org.arabikitouhu.event.update.UpdateEventListener;
//import org.arabikitouhu.font.Font;
//import org.arabikitouhu.font.FontState;
//import org.arabikitouhu.texture.TextureAccessor;
//import org.arabikitouhu.texture.TextureLoader;
//import org.arabikitouhu.texture.TextureManager;
//
///**
// * フォント管理クラス
// * @author arabikitouhu
// * @version 0.0
// * @extends Manager
// */
//public class CopyOfFontManager extends Manager {
//
//	private static CopyOfFontManager instance;
//
//	protected HashMap<String, Font> m_MapFont;
//
//	protected Queue<FontQueue> m_Queue;
//
//	public String m_CurrentFontName;
//
//	public FontState m_CurrentFontState;
//
//
//
//
//
//	public static FontAccessor Accessor() { return new FontAccessor(instance); }
//	public static FontLoader Loader() { return new FontLoader(instance); }
//
//	public static int GetAsyncQueueCount() { return instance.m_Queue.size(); }
//	public static CopyOfFontManager SetLogger(LogStream logger) { instance = new CopyOfFontManager(logger); return instance; }
//
//	private CopyOfFontManager(LogStream logger) {
//		super(logger);
//
//		m_MapFont = new HashMap<String, Font>();
//
//		m_Queue = new ArrayBlockingQueue<FontQueue>(256);
//
//		m_CurrentFontState = new FontState();
//
//		updateListener_Update = new UpdateEventListener() {
//			@Override public void OnUpdate(Component sender, UpdateEventObject e) {
//				if(m_Queue.isEmpty()) { return; }
//				else {
//					FontQueue queue = m_Queue.poll();
//					ImportFontAsync(queue);
//				}
//			}
//		};
//	}
//
//
//
//
//	/**
//	 * [内部]フォントの登録(非同期)
//	 * @param queue フォントキュー
//	 */
//	protected void ImportFontAsync(FontQueue queue) {
//		CreateFont(queue);
//
//		TextureAccessor texAccessor = TextureManager.Accessor();
//		TextureLoader texLoader = TextureManager.Loader();
//
//		if(!texAccessor.Has(String.format("%s-%d", queue.key, queue.number))) {	// 未登録の場合
//			texLoader.Import(String.format("%s-%d", queue.key, queue.number),
//				String.format("./data/texture/font/%1$s/%1$s-%2$d.png", queue.key, queue.number));	// テクスチャの登録
//		}
//
//		if(queue.finished) {
//			Font font = new Font(queue.key);
//			m_MapFont.put(queue.key, font);
//			m_CurrentFontName = queue.key;
//			m_Logger.Info(String.format("フォント「%s」をメモリへ登録しました。", queue.key));
//		}
//	}
//
//	/**
//	 * [内部]フォントテクスチャの生成<br>
//	 * 「./data/texture/font/$key$/」フォルダ配下に生成する。
//	 * @param key 管理用フォント名
//	 * @param filename フォントファイル名
//	 */
//	protected boolean CreateFont(String key, String filename) {
//
//		File file = new File("data/texture/font/" + key + "/");
//		if (file.exists()) {
//			return true;
//		}
//
//		file.mkdirs();	// フォルダを作成
//
//		java.awt.Font fontF = null;
//
//		try {
//			fontF = java.awt.Font.createFont(java.awt.Font.TRUETYPE_FONT, new FileInputStream(filename)).deriveFont(32f);
//		} catch (FontFormatException | IOException e) {
//			m_Logger.Warning(String.format("フォントファイルが間違っています。FileName : %s", filename));
//			e.printStackTrace();
//		}
//
//		for(int i = 0; i < 256; i++) {
//			BufferedImage image = TextureManager.CreateImage(512, 512);
//			Graphics2D g = image.createGraphics();
//
//			g.setColor(new Color(0f, 0f, 0f, 0f));
//			g.fillRect(0, 0, 512, 512);
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
//					g.drawString(message, 32 * k, 24 + 32 * j);
//				}
//			}
//
//			try {
//				ImageIO.write(image, "PNG", new FileOutputStream(String.format("./data/texture/font/%s/%s-%d.png", key, key, i)));
//				m_Logger.Info(String.format("フォントテクスチャを作成しました。FileName : %s", String.format("./data/texture/font/%s/%s-%d.png", key, key, i)));
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//	    }
//
//		return true;
//	}
//
//	/**
//	 * [内部]フォントテクスチャの生成(非同期)
//	 * @param queue フォントキュー
//	 */
//	protected void CreateFont(FontQueue queue) {
//
//		// ディレクトリの検索・・・ない場合は、作成
//		File file = new File("data/texture/font/" + queue.key + "/");
//		if (! file.exists()) { file.mkdirs(); }
//
//		java.awt.Font fontF = null;
//
//		try {
//			fontF = java.awt.Font.createFont(java.awt.Font.TRUETYPE_FONT, new FileInputStream(queue.filename)).deriveFont(32f);
//		} catch (FontFormatException | IOException e) {
//			m_Logger.Warning(String.format("フォントファイルが間違っています。FileName : %s", queue.filename));
//			e.printStackTrace();
//		}
//
//		BufferedImage image = TextureManager.CreateImage(512, 512);
//		Graphics2D g = image.createGraphics();
//
//		g.setColor(new Color(0f, 0f, 0f, 0f));
//		g.fillRect(0, 0, 512, 512);
//
//		g.setFont(fontF);
//		g.setColor(Color.WHITE);
//		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//
//		for(int j = 0; j < 16; j++) {
//			for(int k = 0; k < 16; k++) {
//				String message = null;
//				try {
//					message = new String(new byte[] {(byte)queue.number, (byte)(j * 16 + k)}, "UNICODE");
//				} catch (UnsupportedEncodingException e) {
//					e.printStackTrace();
//				}
//				g.drawString(message, 32 * k, 24 + 32 * j);
//			}
//		}
//
//		try {
//			ImageIO.write(image, "PNG", new FileOutputStream(String.format("./data/texture/font/%s/%s-%d.png", queue.key, queue.key, queue.number)));
//			m_Logger.Info(String.format("フォントテクスチャを作成しました。FileName : %s", String.format("./data/texture/font/%s/%s-%d.png", queue.key, queue.key, queue.number)));
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//
//
//
//	@Override public void Clear() {
//		for(Entry<String, Font> entry : m_MapFont.entrySet()) {
//			entry.getValue().Clear();
//			m_Logger.Info(String.format("フォント「%s」をメモリから削除しました。", entry.getKey()));
//		}
//		m_MapFont.clear();
//	}
//
//	@Override public void Clear(String key) {
//		if(Has(key)) {
//			m_MapFont.get(key).Clear();
//			m_MapFont.remove(key);
//			m_Logger.Info(String.format("フォント「%s」をメモリから削除しました。", key));
//		} else {
//			m_Logger.Warning(String.format("フォント「%s」がメモリにありません。", key));
//		}
//
//	}
//
//	@Override public Font Get(String key) { return Ref(key).ToCopy(); }
//
//	@Override public Font Ref(String key) { return m_MapFont.get(key); }
//
//	@Override public void Import(String key, String filename) {
//		if(Has(key)) {
//			m_Logger.Warning(String.format("フォント「%s」は、既に登録されています。 FileName : %s", key, filename));
//			return; }
//
//		CreateFont(key, filename);
//
//		// 登録
//		TextureAccessor texAccessor = TextureManager.Accessor();
//		TextureLoader texLoader = TextureManager.Loader();
//
//		for(int i = 0; i < 256; i++) {
//			if(!texAccessor.Has(String.format("%s-%d", key, i))) {	// 未登録の場合
//				texLoader.Import(String.format("%s-%d", key, i), String.format("./data/texture/font/%1$s/%1$s-%2$d.png", key, i));	// テクスチャの登録
//			}
//		}
//
//		Font font = new Font(key);
//		m_MapFont.put(key, font);
//		m_CurrentFontName = key;
//		m_Logger.Info(String.format("フォント「%s」をメモリへ登録しました。", key));
//	}
//
//
//	@Override public void ImportAsync(String key, String filename) {
//		if(Has(key)) {
//			m_Logger.Warning(String.format("フォント「%s」は、既に登録されています。 FileName : %s", key, filename));
//			return; }
//
//		for(int i = 0; i < 256; i++) {
//			FontQueue q = new FontQueue(key, filename, i, i == 255);
//			this.m_Queue.add(q);
//			m_Logger.Info(String.format("フォントキュー「%s」を登録しました。", q.toString()));
//		}
//	}
//
//
//	@Override public boolean Has(String key) { return m_MapFont.containsKey(key); }
//
//
//
//
//
//
//}
//
///**
// * 内部で使用するキューオブジェクト(Font)
// * @author arabikitouhu
// * @version 0.0
// */
//class FontQueue {
//	public boolean finished;
//	public String key;
//	public String filename;
//	public int number;
//
//	public FontQueue(String key, String filename, int number, boolean finished) {
//		this.key = key;
//		this.filename = filename;
//		this.number = number;
//		this.finished = finished;
//	}
//
//	@Override public String toString() {
//		return String.format("%s, %s, %d", key, filename, number);
//	}
//}
