package org.arabikitouhu.resource;

import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.openal.SoundStore;

/**
 * サウンドクラス
 * @author arabikitouhu
 * @version 0.0
 */
public class Sound {

	//音の種類
	public static final int SOUNDTYPE_OGG = 0;
	public static final int SOUNDTYPE_WAV = 1;
	public static final int SOUNDTYPE_OGGSTREAM = 2;

	//ステータス
	public static final int PROPERTY_PITCH = 0;
	public static final int PROPERTY_GAIN = 1;

	protected String m_Name;
	protected Audio m_Audio;

	protected float m_Pitch;
	protected float m_Gain;

	protected boolean m_Looping;

	/**
	 * コンストラクタ
	 * @param key 管理名
	 * @param audio Audio
	 */
	public Sound(String key, Audio audio) {
		m_Name = key;
		m_Audio = audio;

		m_Pitch = 1f;
		m_Gain = 1f;
		m_Looping = false;
	}

	/** ループ設定の有無 */
	public boolean isPlaying() { return m_Audio.isPlaying(); }

	/** 再生 */
	public void Play() {
		if(!m_Audio.isPlaying()) {
			m_Audio.playAsSoundEffect(m_Pitch, m_Gain, m_Looping);
			SoundStore.get().poll(0);
		}
	}

	/** 停止 */
	public void Stop() {
		if(isPlaying()) {
			m_Audio.stop();
			SoundStore.get().poll(0);
		}
	}

	/** [SET]ピッチ (チェインメソッド) */
	public Sound SetPitch(float value) {
		m_Pitch = value;
		return this;
	}

	/** [SET]ゲイン (チェインメソッド) */
	public Sound SetGain(float value) {
		m_Gain = value;
		return this;
	}

	/** [SET]ループ (チェインメソッド) */
	public Sound SetLooping(boolean value) {
		m_Looping = value;
		return this;
	}

	/** 複製 */
	public Sound ToCopy() {
		Sound sound = new Sound(m_Name, m_Audio).SetPitch(m_Pitch).SetGain(m_Gain).SetLooping(m_Looping);
		return sound;
	}

}