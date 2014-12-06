package org.arabikitouhu.event.mouse;

import org.arabikitouhu.component.Component;
import org.arabikitouhu.event.object.MouseEventObject;

/**
 * マウスイベント リスナークラス
 * @author arabikitouhu
 * @version 0.0
 */
public class MouseEventListener {

	public static final MouseEventListener DummyListener = new MouseEventListener();


	public static final int EVENT_Enter = 0;
	public static final int EVENT_Leave = 1;
	public static final int EVENT_Hover = 2;
	public static final int EVENT_Down = 3;
	public static final int EVENT_Click = 4;
	public static final int EVENT_DoubleClick = 5;
	public static final int EVENT_Up = 6;


	public void OnMouseEnter(Component sender, MouseEventObject e){ }

	public void OnMouseLeave(Component sender, MouseEventObject e){ }

	public void OnMouseHover(Component sender, MouseEventObject e){ }

	public void OnMouseDown(Component sender, MouseEventObject e){ }

	public void OnMouseUp(Component sender, MouseEventObject e){ }

	public void OnClick(Component sender, MouseEventObject e){ }

	public void OnDoubleClick(Component sender, MouseEventObject e){ }

}
