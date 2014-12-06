//package org.arabikitouhu.sound;
//
//import java.io.IOException;
//import java.util.HashMap;
//
//import org.arabikitouhu.common.LogStream;
//import org.arabikitouhu.common.Manager;
//import org.newdawn.slick.openal.Audio;
//import org.newdawn.slick.openal.AudioLoader;
//import org.newdawn.slick.util.ResourceLoader;
//
//
///**
// * サウンド管理クラス
// * @author arabikitouhu
// * @version 0.0
// * @extends Manager
// */
//public class CopyOfSoundManager extends Manager {
//
//	private static CopyOfSoundManager instance;
//
//	protected HashMap<String, Sound> m_MapSound;
//
//
//	public static SoundAccessor Accessor() { return new SoundAccessor(instance); }
//	public static SoundLoader Loader() { return new SoundLoader(instance); }
//
//
//	public static CopyOfSoundManager SetLogger(LogStream logger) { instance = new CopyOfSoundManager(logger); return instance; }
//
//	private CopyOfSoundManager(LogStream logger) {
//		super(logger);
//		m_MapSound = new HashMap<String, Sound>();
//	}
//
//	/**
//	 * サウンドの取込
//	 * @param key 管理用サウンド名
//	 * @param filename ファイル名
//	 * @param soundType サウンドの種類 ( Sound.SOUNDTYPE_OGG | Sound.SOUNDTYPE_WAV | Sound.SOUNDTYPE_OGGSTREAM )
//	 */
//	public void ImportSound(String key, String filename, int soundType) {
//
//		if(soundType == Sound.SOUNDTYPE_OGG) {
//			ImportOGG(key, filename);
//		} else if(soundType == Sound.SOUNDTYPE_WAV) {
//			ImportWAV(key, filename);
//		} else if(soundType == Sound.SOUNDTYPE_OGGSTREAM) {
//			ImportOGGStream(key, filename);
//		}
//	}
//
//	/**
//	 * サウンドの取込(*.ogg)
//	 * @param key 管理用サウンド名
//	 * @param filename ファイル名
//	 */
//	public void ImportOGG(String key, String filename) {
//
//		if (Has(key)) {
//			m_Logger.Warning(String.format("サウンド「%s」は、既に登録済みです。", key));
//		} else {
//			try {
//				Audio ogg = AudioLoader.getAudio("OGG", ResourceLoader.getResourceAsStream(filename));
//
//				Sound sound = new Sound(key, ogg);
//				m_MapSound.put(key, sound);
//				m_Logger.Info(String.format("サウンド「%s」を登録しました。", key));
//
//			} catch (IOException e) {
//				m_Logger.Warning(String.format("サウンドファイルの読込に失敗しました。 FileName : %s", filename));
//				e.printStackTrace();
//			}
//		}
//
//	}
//
//	/**
//	 * サウンドの取込(*.wav)
//	 * @param key 管理用サウンド名
//	 * @param filename ファイル名
//	 */
//	public void ImportWAV(String key, String filename) {
//
//		if (Has(key)) {
//			m_Logger.Warning(String.format("サウンド「%s」は、既に登録済みです。", key));
//		} else {
//			try {
//				Audio wav = AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream(filename));
//
//				Sound sound = new Sound(key, wav);
//				m_MapSound.put(key, sound);
//				m_Logger.Info(String.format("サウンド「%s」を登録しました。", key));
//
//			} catch (IOException e) {
//				m_Logger.Warning(String.format("サウンドファイルの読込に失敗しました。 FileName : %s", filename));
//				e.printStackTrace();
//			}
//		}
//
//	}
//
//	/**
//	 * サウンド(BGM)の取込(*.ogg)
//	 * @param key 管理用サウンド名
//	 * @param filename ファイル名
//	 */
//	public void ImportOGGStream(String key, String filename) {
//
//		if (Has(key)) {
//			m_Logger.Warning(String.format("サウンド「%s」は、既に登録済みです。", key));
//		} else {
//			try {
//				Audio ogg = AudioLoader.getAudio("OGG", ResourceLoader.getResourceAsStream(filename));
//
//				Sound sound = (new Sound(key, ogg)).SetLooping(true);
//				m_MapSound.put(key, sound);
//				m_Logger.Info(String.format("サウンド「%s」を登録しました。", key));
//
//			} catch (IOException e) {
//				m_Logger.Warning(String.format("サウンドファイルの読込に失敗しました。 FileName : %s", filename));
//				e.printStackTrace();
//			}
//		}
//
//	}
//
//
//
//
//	@Override public void Clear() {
//		m_MapSound.clear();
//		m_Logger.Info("サウンドすべてをメモリから削除しました。");
//	}
//
//	@Override public void Clear(String key) {
//		if(Has(key)) {
//		if(m_MapSound.get(key).isPlaying()) {
//			m_MapSound.get(key).Stop();
//			m_MapSound.remove(key);
//			m_Logger.Info(String.format("サウンド「%s」をメモリから削除しました。", key));
//		} else {
//			m_Logger.Warning(String.format("サウンド「%s」がメモリにありません。", key));
//		}
//
//	}
//
//	}
//
//	@Override public Sound Get(String key) { return Ref(key).ToCopy(); }
//
//	@Override public Sound Ref(String key) { return m_MapSound.get(key); }
//
//	@Override public void Import(String key, String filename) {
//		String[] str = filename.split(".");
//		String ext = str[str.length - 1];
//		if(ext == "ogg") {
//			ImportOGG(key, filename);
//		} else if(ext == "wav") {
//			ImportWAV(key, filename);
//		}
//	}
//
//	@Override public void ImportAsync(String key, String filename) { }
//
//	@Override public boolean Has(String key) { return m_MapSound.containsKey(key); }
//
//
//}
