package org.arabikitouhu.event.rendering;

import org.arabikitouhu.common.LogStream;
import org.arabikitouhu.event.object.RenderingEventObject;
import org.arabikitouhu.timer.Timer;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

public class RenderingObserver {

	private static final LogStream logger = new LogStream("RenderingObserver");

	private static RenderingObserver instance;

	private static RenderingObserver GetInstance() {
		if(instance == null) {
			instance = new RenderingObserver();
		}

		return instance;
	}

	public static void SetSender(RenderingEventSender sender) {
		GetInstance().sender = sender;
	}


	public static void Start(RenderingEventSender sender) {

		Timer timer = new Timer();
		timer.Initialize();

		GetInstance().isRunning = true;
		SetSender(sender);

		while(GetInstance().isRunning) {
			try {
				GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);

				if(GetInstance().sender != null) {
					GetInstance().sender.RenderingEventCheck(new RenderingEventObject(timer.GetFrame(), timer.GetAccumuTime()));;
				}
				timer.Score();
				timer.Update();
				Display.update();
				Display.sync(60);
			} catch (Exception e){
				logger.GetStream().warning("エラーが発生しています。");
			}

		}

	}

	public static void Stop() {
		GetInstance().isRunning = false;
	}


	boolean isRunning;

	private RenderingObserver() {
		sender = null;
		isRunning = false;
	}

	RenderingEventSender sender;

	public void run() {
	}

}
