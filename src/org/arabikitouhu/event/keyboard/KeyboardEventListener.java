package org.arabikitouhu.event.keyboard;

import org.arabikitouhu.component.Component;
import org.arabikitouhu.event.object.KeyboardEventObject;

/**
 * キーボードイベント リスナークラス
 * @author arabikitouhu
 * @version 0.0
 */
public class KeyboardEventListener {

	public static final KeyboardEventListener DummyListener = new KeyboardEventListener();


	public static final int EVENT_Down = 0;
	public static final int EVENT_Press = 1;
	public static final int EVENT_Up = 2;


	public void OnKeyboardDown(Component sender, KeyboardEventObject e){ }
	public void OnKeyboardPress(Component sender, KeyboardEventObject e){ }
	public void OnKeyboardUp(Component sender, KeyboardEventObject e){ }
}
