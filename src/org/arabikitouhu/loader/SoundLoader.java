
/*
 * LWJGLGame
 *
 * Copyright (c) 2014 arabikitouhu
 *
 *
 *
 */
package org.arabikitouhu.loader;

import java.io.IOException;

import org.arabikitouhu.common.LogStream;
import org.arabikitouhu.resource.Sound;
import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.openal.AudioLoader;
import org.newdawn.slick.util.ResourceLoader;


/**
 * サウンド読み込みクラス
 * @author arabikitouhu
 * @version 0.0
 */
public final class SoundLoader {

	private static final LogStream logger = new LogStream("SoundLoader");

	/**
	 * サウンドの取込
	 * @param key 管理用サウンド名
	 * @param filename ファイル名
	 * @param soundType サウンドの種類 ( Sound.SOUNDTYPE_OGG | Sound.SOUNDTYPE_WAV | Sound.SOUNDTYPE_OGGSTREAM )
	 * @return 失敗時は「null」が渡される。
	 */
	public static Sound ImportSound(String key, String filename, int soundType) {

		Sound sound = null;
		if(soundType == Sound.SOUNDTYPE_OGG) {
			sound = ImportOGG(key, filename);
		} else if(soundType == Sound.SOUNDTYPE_WAV) {
			sound = ImportWAV(key, filename);
		} else if(soundType == Sound.SOUNDTYPE_OGGSTREAM) {
			sound = ImportOGGStream(key, filename);
		}
		return sound;
	}

	/**
	 * サウンドの取込(*.ogg)
	 * @param key 管理用サウンド名
	 * @param filename ファイル名
	 * @return 失敗時は「null」が渡される。
	 */
	public static Sound ImportOGG(String key, String filename) {

		Sound sound = null;
		try {
			Audio ogg = AudioLoader.getAudio("OGG", ResourceLoader.getResourceAsStream(filename));

			sound = new Sound(key, ogg);
			logger.GetStream().info(String.format("サウンド「%s」を登録しました。", key));

		} catch (IOException e) {
			logger.GetStream().warning(String.format("サウンドファイルの読込に失敗しました。 FileName : %s", filename));
			e.printStackTrace();
		}

		return sound;
	}

	/**
	 * サウンドの取込(*.wav)
	 * @param key 管理用サウンド名
	 * @param filename ファイル名
	 * @return 失敗時は「null」が渡される。
	 */
	public static Sound ImportWAV(String key, String filename) {

		Sound sound = null;
		try {
			Audio wav = AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream(filename));

			sound = new Sound(key, wav);
			logger.GetStream().info(String.format("サウンド「%s」を登録しました。", key));

		} catch (IOException e) {
			logger.GetStream().warning(String.format("サウンドファイルの読込に失敗しました。 FileName : %s", filename));
			e.printStackTrace();
		}

		return sound;
	}

	/**
	 * サウンド(BGM)の取込(*.ogg)
	 * @param key 管理用サウンド名
	 * @param filename ファイル名
	 * @return 失敗時は「null」が渡される。
	 */
	public static Sound ImportOGGStream(String key, String filename) {


		Sound sound = null;
		try {
			Audio ogg = AudioLoader.getAudio("OGG", ResourceLoader.getResourceAsStream(filename));

			sound = (new Sound(key, ogg)).SetLooping(true);
			logger.GetStream().info(String.format("サウンド「%s」を登録しました。", key));

		} catch (IOException e) {
			logger.GetStream().warning(String.format("サウンドファイルの読込に失敗しました。 FileName : %s", filename));
			e.printStackTrace();
		}

		return sound;
	}




}
