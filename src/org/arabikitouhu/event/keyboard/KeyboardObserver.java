package org.arabikitouhu.event.keyboard;

import java.util.Arrays;

import org.arabikitouhu.common.LogStream;
import org.arabikitouhu.event.object.KeyboardEventObject;
import org.arabikitouhu.timer.Timer;
import org.lwjgl.input.Keyboard;

public class KeyboardObserver implements Runnable {

	private static final LogStream logger = new LogStream("KeyboardObserver");
	private static KeyboardObserver instance;

	private static KeyboardObserver GetInstance() {
		if(instance == null) {
			instance = new KeyboardObserver();
		}

		return instance;
	}

	public static void SetSender(KeyboardEventSender sender) {
		GetInstance().sender = sender;
	}


	public static void Start() {
		if(GetInstance().thread == null) {
			GetInstance().thread = new Thread(GetInstance(), "KeyboardObserver");
			GetInstance().thread.start();
		}
	}

	public static void Stop() {
		GetInstance().isRunning = false;
	}


	Thread thread;
	boolean isRunning;

	private KeyboardObserver() {
		sender = null;
		thread = null;
		isRunning = false;
	}

	KeyboardEventSender sender;

	@Override public void run() {

		KeyboardEventObject obj;

		Timer timer = new Timer();
		timer.Initialize();

		isRunning = true;

		boolean[] prevstate = new boolean[Keyboard.KEYBOARD_SIZE];
		boolean[] state = new boolean[Keyboard.KEYBOARD_SIZE];

		while(isRunning) {
			try {

				for(int i = 0; i < 256; i++) {
					state[i] = Keyboard.isKeyDown(i);
				}
				obj = new KeyboardEventObject(prevstate, state);
				if(sender != null) {
					sender.KeyboardEventCheck(obj);
				}
				prevstate = Arrays.copyOf(state, state.length);

			} catch (Exception e){
				logger.GetStream().warning("エラーが発生しています。");
			}
			int delta = timer.Score();

			//待機時間の計測
			int sleepTime = 25 - delta;
			if(sleepTime > 0) {
				try {
					Thread.sleep(sleepTime);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			timer.Score();
			timer.Update();
		}
	}

}
