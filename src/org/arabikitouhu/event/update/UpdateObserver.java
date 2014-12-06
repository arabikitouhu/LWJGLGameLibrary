package org.arabikitouhu.event.update;

import org.arabikitouhu.common.LogStream;
import org.arabikitouhu.event.object.UpdateEventObject;
import org.arabikitouhu.timer.Timer;

public class UpdateObserver implements Runnable {

	private static final LogStream logger = new LogStream("UpdateObserver");

	private static UpdateObserver instance;

	private static UpdateObserver GetInstance() {
		if(instance == null) {
			instance = new UpdateObserver();
		}

		return instance;
	}

	public static void SetSender(UpdateEventSender sender) {
		GetInstance().sender = sender;
	}


	public static void Start() {
		if(GetInstance().thread == null) {
			GetInstance().thread = new Thread(GetInstance(), "UpdateObserver");
			GetInstance().thread.start();
		}
	}

	public static void Stop() {
		GetInstance().isRunning = false;
	}


	Thread thread;
	boolean isRunning;

	private UpdateObserver() {
		sender = null;
		thread = null;
		isRunning = false;
	}

	UpdateEventSender sender;

	@Override public void run() {

		UpdateEventObject obj;

		Timer timer = new Timer();
		timer.Initialize();

		isRunning = true;

		while(isRunning) {
			try {
				obj = new UpdateEventObject(timer.GetFrame(), timer.Score());

				if(sender != null) {
					sender.UpdateEventCheck(obj);
				}
			} catch (Exception e){
				logger.GetStream().warning("エラーが発生しています。");
			}

			//待機時間の計測
			int sleepTime = 16 - timer.Score();
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
