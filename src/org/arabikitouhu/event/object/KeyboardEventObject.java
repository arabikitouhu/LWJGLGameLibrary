package org.arabikitouhu.event.object;



public class KeyboardEventObject {

	final boolean[] prev, now;

	public KeyboardEventObject(boolean[] prevstate, boolean[] state) {
		this.prev = prevstate;
		this.now = state;
	}

	public boolean isKeyDown(int keyID) { return !prev[keyID] && now[keyID]; }
	public boolean isKeyPress(int keyID) { return prev[keyID] && now[keyID]; }
	public boolean isKeyUp(int keyID) { return prev[keyID] && !now[keyID]; }


}
