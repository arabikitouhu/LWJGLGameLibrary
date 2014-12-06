package org.arabikitouhu.timer;

import org.lwjgl.Sys;

public class Timer {

	private int nowFrame;
	private int procTime;
	private long oldTime;
	private int deltaTime;
	private int accumuTime;

	public long GetTime() {
	    return (Sys.getTime() * 1000) / Sys.getTimerResolution();
	}

	public void Initialize() {
		nowFrame = 0;
		procTime = 0;
		oldTime = GetTime();
		deltaTime = 0;
		accumuTime = 0;
	}

	public int Score() {
		long newTime = GetTime();
		deltaTime = (int)(newTime - oldTime);
		oldTime = newTime;
		accumuTime += deltaTime;
		return deltaTime;
	}

	public void Update(int process) {
		// 時間の更新
		procTime += process;
		if(procTime > 1000) {
			nowFrame = 0;
			procTime = 0;
		} else {
			nowFrame++;
		}
	}

	public void Update() {
		Update(accumuTime);
		accumuTime = 0;
	}

	public int GetFrame() { return nowFrame; }
	public int GetProcTime() { return procTime; }
	public int GetDeltaTime() { return deltaTime; }
	public int GetAccumuTime() { return accumuTime; }
}
