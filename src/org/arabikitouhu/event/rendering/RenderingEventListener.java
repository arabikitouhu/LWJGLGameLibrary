package org.arabikitouhu.event.rendering;

import org.arabikitouhu.component.Component;
import org.arabikitouhu.event.object.RenderingEventObject;

/**
 * 描画イベント リスナークラス
 * @author arabikitouhu
 * @version 0.0
 */
public class RenderingEventListener {

	public static final RenderingEventListener DummyListener = new RenderingEventListener();


	public static final int EVENT_Paint = 0;

	public void OnPaint(Component sender, RenderingEventObject e){ }
}
