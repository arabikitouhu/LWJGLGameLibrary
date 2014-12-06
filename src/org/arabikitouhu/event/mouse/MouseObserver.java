package org.arabikitouhu.event.mouse;

import org.arabikitouhu.common.LogStream;
import org.arabikitouhu.event.object.MouseEventObject;
import org.arabikitouhu.timer.Timer;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

public class MouseObserver implements Runnable {

	private static final LogStream logger = new LogStream("MouseObserver");
	private static MouseObserver instance;

	private static MouseObserver GetInstance() {
		if(instance == null) {
			instance = new MouseObserver();
		}

		return instance;
	}

	public static void SetSender(MouseEventSender sender) {
		GetInstance().sender = sender;
	}


	public static void Start() {
		if(GetInstance().thread == null) {
			GetInstance().thread = new Thread(GetInstance(), "MouseObserver");
			GetInstance().thread.start();
		}
	}

	public static void Stop() {
		GetInstance().isRunning = false;
	}


	Thread thread;
	boolean isRunning;

	private MouseObserver() {
		sender = null;
		thread = null;
		isRunning = false;
	}

	MouseEventSender sender;

	@Override public void run() {

		MouseEventObject obj;

		Timer timer = new Timer();
		timer.Initialize();

		isRunning = true;

		while(isRunning) {
			try {
				obj = new MouseEventObject(Mouse.getX(), Display.getHeight() - Mouse.getY(), Mouse.getDX(), Mouse.getDY(), Mouse.getDWheel(), Mouse.isButtonDown(0), Mouse.isButtonDown(1), Mouse.isButtonDown(2));

				if(sender != null) {
					sender.MouseEventCheck(obj);
				}

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
