package org.arabikitouhu.event.update;

import org.arabikitouhu.component.Component;
import org.arabikitouhu.event.object.UpdateEventObject;


/**
 * 更新イベント リスナークラス
 * @author arabikitouhu
 * @version 0.0
 */
public class UpdateEventListener {

	public static final UpdateEventListener DummyListener = new UpdateEventListener();


	public static final int EVENT_Update = 0;

	public void OnUpdate(Component sender, UpdateEventObject e){ }
}
