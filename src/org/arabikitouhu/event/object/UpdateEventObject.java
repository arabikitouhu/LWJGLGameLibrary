package org.arabikitouhu.event.object;


public class UpdateEventObject {

	protected int m_NowFrame;
	protected float m_DeltaTime;

	public UpdateEventObject(int frame, float deltaTime) {
		this.m_NowFrame = frame;
		this.m_DeltaTime = deltaTime;
	}

	public int GetNowFrame() { return this.m_NowFrame; }

	public float GetDeltaTime() { return this.m_DeltaTime; }

}
